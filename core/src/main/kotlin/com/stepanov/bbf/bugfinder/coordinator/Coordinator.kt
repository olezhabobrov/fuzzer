package com.stepanov.bbf.bugfinder.coordinator

import com.stepanov.bbf.bugfinder.mutator.vertxMessages.MutationRequest
import com.stepanov.bbf.bugfinder.mutator.vertxMessages.MutationResult
import com.stepanov.bbf.bugfinder.server.messages.MutationProblem
import com.stepanov.bbf.information.MutationStat
import com.stepanov.bbf.information.VertxAddresses
import com.stepanov.bbf.messages.CompilationRequest
import com.stepanov.bbf.messages.CompilationResult
import com.stepanov.bbf.messages.KotlincInvokeResult
import com.stepanov.bbf.messages.ProjectMessage
import io.vertx.core.AbstractVerticle
import io.vertx.core.eventbus.EventBus
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.apache.log4j.Logger
import java.util.concurrent.atomic.AtomicInteger

class Coordinator(private val mutationProblem: MutationProblem): AbstractVerticle() {

    private lateinit var eb: EventBus
    private val json = Json { prettyPrint = true }

    override fun start() {
        eb = vertx.eventBus()
        establishConsumers()
        log.debug("Coordinator deployed with mutation problem:")
        val projectToCompile = mutationProblem.getProjectMessage()
        log.debug(json.encodeToString(mutationProblem))
//        sendNextTransformation(listOf(mutationProblem.getProjectMessage())) // TODO: only for debug
        sendProjectToCompilers(MutationResult(
            setOf(projectToCompile),
            MutationStat.emptyStat))
    }

    private fun establishConsumers() {

        eb.consumer<CompilationResult>(VertxAddresses.compileResult) { msg ->
            log.debug("Got compilation result")
            val compileResult = msg.body()
            val projectsToSend = mutableListOf<ProjectMessage>()
            compileResult.results.forEach { result ->
                checkedProjects.add(result.projectMessage)

                if (result.isCompileSuccess) {
                    projectsToSend.add(result.projectMessage)
                    successfullyCompiledProjects.add(result.projectMessage)
                }
                if (result.hasCompilerCrash) {
                    log.debug("Found some bug")
                    sendResultToBugManager(compileResult, result)
                }
            }
            log.debug("Got ${projectsToSend.size} projects, successfully compiled")
            log.debug("Checked unique projects: ${checkedProjects.size}")
            log.debug("Successfully compiled projects: ${successfullyCompiledProjects.size}")
            sendResultToStatistics(compileResult)
            if (mutationProblem.isFinished()) {
                log.debug("MUTATION PROBLEM IS COMPLETED")
                eb.send(VertxAddresses.mutationProblemCompleted, coordinatorNumber)
            }
            sendNextTransformation(projectsToSend)
        }

        eb.consumer<MutationResult>(VertxAddresses.mutationResult) { result ->
            val mutationResult = result.body()
            log.debug("Got mutationResult with ${mutationResult.projects.size} results")
            sendProjectToCompilers(mutationResult)
        }
    }

    private fun sendNextTransformation(projects: List<ProjectMessage>) {
        if (mutationProblem.isNotFinished()) {
            val transformation = mutationProblem.getNextTransformationAndIncreaseCounter()
            val projectToSend = getProjectsToSend(projects)
            eb.send(VertxAddresses.mutate,
                MutationRequest(
                    transformation,
                    projectToSend
                )
            )
            log.debug("Transformation#${mutationProblem.completedMutations}: " +
                    "Sending ${projectToSend.size} projects to mutator to transform with $transformation")
        }
    }

    private fun sendProjectToCompilers(mutationResult: MutationResult) {
        val projects = mutationResult.projects.filter { project ->
            project !in checkedProjects
        }.shuffled().take(MAX_PROJECTS_TO_COMPILERS)
        log.debug("Sending ${projects.size} projects to compiler")
        mutationProblem.compilers.forEach { address ->
            eb.send(address,
                CompilationRequest(projects, mutationResult.mutationStat, true)
            )
        }
    }

    private fun sendResultToBugManager(result: CompilationResult, status: KotlincInvokeResult) {
        eb.send(
            VertxAddresses.bugManager, CompilationResult(
                result.compiler,
                listOf(status),
                result.mutationStat
            )
        )
    }

    private fun sendResultToStatistics(result: CompilationResult) {
        eb.send(VertxAddresses.transformationStatistics, result)
    }

    private fun getProjectsToSend(latestProjects: List<ProjectMessage>): List<ProjectMessage> {
        if (successfullyCompiledProjects.isEmpty() || successfullyCompiledProjects.size > LIMIT_OF_COMPILED_PROJECTS) {
            val newProject = mutationProblem.getProjectMessage()
            log.debug("Created new starting project ${newProject.files.firstOrNull()?.name}")
            successfullyCompiledProjects.clear()
            checkedProjects.clear()
            return listOf(newProject)
        }
        val projects = latestProjects.filter { it !in checkedProjects }.take(MAX_PROJECTS_TO_MUTATE).toMutableList()
        projects.addAll(
            successfullyCompiledProjects.shuffled()
                .take(MAX_PROJECTS_TO_MUTATE - projects.size)
//                .filter { Random.nextInt(100) < 75 }
        )
//        if (checkedProjects.size > LIMIT_OF_CHECKED_PROJECTS) {
//            checkedProjects = mutableSetOf()
//        }
        return projects
    }

    val coordinatorNumber = counter.getAndIncrement()

    companion object {
        private val counter = AtomicInteger(0)
    }

    private val MAX_PROJECTS_TO_MUTATE = 20
    private val MAX_PROJECTS_TO_COMPILERS = 500
    private val LIMIT_OF_COMPILED_PROJECTS = 3500
//    private val LIMIT_OF_CHECKED_PROJECTS = 1_000_000
    private var checkedProjects = mutableSetOf<ProjectMessage>()
    private var successfullyCompiledProjects = mutableSetOf<ProjectMessage>()

    private val log = Logger.getLogger("coordinatorLogger")
}