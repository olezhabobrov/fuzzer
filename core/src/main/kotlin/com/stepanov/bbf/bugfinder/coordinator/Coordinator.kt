package com.stepanov.bbf.bugfinder.coordinator

import com.stepanov.bbf.bugfinder.mutator.transformations.Transformation
import com.stepanov.bbf.bugfinder.mutator.vertxMessages.MutationRequest
import com.stepanov.bbf.bugfinder.mutator.vertxMessages.MutationResult
import com.stepanov.bbf.bugfinder.server.messages.MutationProblem
import com.stepanov.bbf.information.MutationStat
import com.stepanov.bbf.information.VertxAddresses
import com.stepanov.bbf.messages.CompilationRequest
import com.stepanov.bbf.messages.CompilationResult
import com.stepanov.bbf.messages.KotlincInvokeResult
import com.stepanov.bbf.messages.ProjectMessage
import io.vertx.core.eventbus.EventBus
import io.vertx.kotlin.coroutines.CoroutineVerticle
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.apache.log4j.Logger

class Coordinator(private val mutationProblem: MutationProblem): CoroutineVerticle() {

    private lateinit var eb: EventBus
    private val json = Json { prettyPrint = true }

    override suspend fun start() {
        eb = vertx.eventBus()
        establishConsumers()
        log.debug("Coordinator deployed with mutation problem:")
        log.debug(json.encodeToString(mutationProblem))
        sendProjectToCompilers(MutationResult(
            setOf(mutationProblem.getProjectMessage()),
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
                }
                if (result.hasCompilerCrash) {
                    log.debug("Found some bug")
                    sendResultToBugManager(compileResult, result)
                }
            }
            log.debug("Got ${projectsToSend.size} projects, successfully compiled")
            sendResultToStatistics(compileResult)
            sendNextTransformation(projectsToSend)
        }

        eb.consumer<MutationResult>(VertxAddresses.mutationResult) { result ->
            val mutationResult = result.body()
            log.debug("Got mutationResult with ${mutationResult.projects.size} results")
            sendProjectToCompilers(mutationResult)
            if (mutationProblem.isFinished()) {
                log.debug("Got completed mutation problem")
            }
        }
    }

    private fun sendNextTransformation(projects: List<ProjectMessage>) {
        if (mutationProblem.isNotFinished()) {
            val transformation = mutationProblem.getNextTransformationAndIncreaseCounter()
            val projectToSend = getProjectsToSend(projects)
            log.debug("Sending ${projectToSend.size} projects to mutator to transform with $transformation")
            eb.send(VertxAddresses.mutate,
                MutationRequest(
                    transformation,
                    projectToSend
                )
            )
        }
    }

    private fun sendProjectToCompilers(mutationResult: MutationResult) {
        log.debug("Sending ${mutationResult.projects.size} projects to compiler")
        mutationProblem.compilers.forEach { address ->
            eb.send(address,
                CompilationRequest(mutationResult.projects.toList(), mutationResult.mutationStat)
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
        val projects = latestProjects.filter { it !in checkedProjects }.take(MAX_PROJECTS).toMutableList()
        projects.addAll(checkedProjects.shuffled().take(MAX_PROJECTS - projects.size))
        return projects
    }


    private val MAX_PROJECTS = 25
    private val checkedProjects = mutableSetOf<ProjectMessage>()

    private val log = Logger.getLogger("coordinatorLogger")
}