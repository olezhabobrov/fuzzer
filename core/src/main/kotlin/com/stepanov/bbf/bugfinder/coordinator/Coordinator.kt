package com.stepanov.bbf.bugfinder.coordinator

import com.stepanov.bbf.bugfinder.mutator.transformations.Transformation
import com.stepanov.bbf.bugfinder.mutator.transformations.klib.BinaryCompatibleTransformation
import com.stepanov.bbf.bugfinder.mutator.vertxMessages.MutationRequest
import com.stepanov.bbf.bugfinder.mutator.vertxMessages.MutationResult
import com.stepanov.bbf.bugfinder.server.messages.MutationProblem
import com.stepanov.bbf.bugfinder.util.getRandomVariableName
import com.stepanov.bbf.information.CompilerArgs
import com.stepanov.bbf.information.VertxAddresses
import com.stepanov.bbf.messages.*
import io.vertx.core.AbstractVerticle
import io.vertx.core.eventbus.EventBus
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.apache.log4j.Logger
import java.io.File
import java.util.concurrent.atomic.AtomicInteger
import kotlin.random.Random

class Coordinator(private val mutationProblem: MutationProblem): AbstractVerticle() {

    private lateinit var eb: EventBus
    private val json = Json { prettyPrint = true }

    override fun start() {
        eb = vertx.eventBus()
        establishConsumers()
        log.debug("Coordinator deployed with mutation problem:")
        val projectToCompile = mutationProblem.getProjectMessage()
        log.debug(json.encodeToString(mutationProblem))
//        startWithNewProject()
        successfullyCompiledProjects.add(projectToCompile)
        sendNextTransformation() // TODO: only for debug
//        sendProjectToCompilers(MutationResult(
//            setOf(projectToCompile),
//            MutationStat.emptyStat))
    }

    private fun establishConsumers() {

        // when checking the initial project
        eb.consumer<KotlincInvokeResult>(VertxAddresses.checkCompileResult) { msg ->
            val result = msg.body()
            if (result.isCompileSuccess) {
                successfullyCompiledProjects.add(result.projectMessage.getProjectMessageWithKlib())
                sendNextTransformation()
            } else {
                startWithNewProject()
            }
        }

        eb.consumer<CompilationResult>(VertxAddresses.compileResult) { msg ->
            log.debug("Got compilation result")
            val compileResult = msg.body()
            compileResult.results.all { it.results.all { !it.isCompileSuccess } }
            val descrs = compileResult.results.map { result ->
                processResult(result)
            }
            log.debug("Checked unique projects: ${checkedProjects.size}")
            log.debug("Successfully compiled projects: ${successfullyCompiledProjects.size}")
            sendResultToStatistics(compileResult)
//            if (mutationProblem.isFinished()) {
//                log.debug("MUTATION PROBLEM IS COMPLETED")
//                eb.send(VertxAddresses.mutationProblemCompleted, coordinatorNumber)
//            }
            if (descrs.any { it == CompilationDescription.INVOCATOR_FAIL })
                startWithNewProject()
            else
                sendNextTransformation()
        }

        eb.consumer<MutationResult>(VertxAddresses.mutationResult) { result ->
            val mutationResult = result.body()
            log.debug("Got mutationResult with ${mutationResult.projects.size} results")
            if (mutationResult.projects.isEmpty()) {
                sendNextTransformation()
            } else {
                sendProjectToCompilers(mutationResult)
            }
        }
    }

    private fun processResult(result: KotlincInvokeResult): CompilationDescription {
        checkedProjects.add(result.projectMessage.getProjectMessageWithNewKlib())
        val transformationName = lastTransformation.javaClass.simpleName
        val descr = result.getDescription()
        when (descr) {
            CompilationDescription.KLIB_INVALID ->
                log.debug("Klib was modified incorrectly by $transformationName")
            CompilationDescription.INVOCATOR_FAIL ->
                log.debug("Got invocator error")
            CompilationDescription.COMPATIBLE_NOT_LINKING -> {
//                successfullyCompiledProjects.add(result.projectMessage)
                log.debug(
                    "Transformation $transformationName is binary compatible, " +
                            "but compiler resulted with a linking error"
                )
            }
            CompilationDescription.INCOMPATIBLE_LINKING -> {
//                successfullyCompiledProjects.add(result.projectMessage)
                log.debug(
                    "Transformation $transformationName is binary INcompatible, " +
                            "but compiler didn't result with a linking error"
                )
            }
            CompilationDescription.COMPILER_CRASHED ->
                log.debug("Compiler crashed")
            CompilationDescription.EXPECTED_BEHAVIOUR -> {
                if (result.projectMessage.isBinaryCompatible!!) {
                    log.debug("Binary compatible transformation $transformationName remains ABI")
                } else {
                    log.debug("Binary incompatible transformation $transformationName breaks ABI")
                }
                statistics.resultedProject = result.projectMessage
                statistics.addTransformation(lastTransformation)
                successfullyCompiledProjects.add(result.projectMessage.getProjectMessageWithNewKlib())
            }
            CompilationDescription.UNKOWN_BEHAVIOUR -> {
                log.debug("Fail to understand compilation result")
            }
        }
        if (descr != CompilationDescription.EXPECTED_BEHAVIOUR && descr != CompilationDescription.KLIB_INVALID) {
            sendResultToBugManager(result)
        }
        return descr
    }

    private fun startWithNewProject() {
        log.debug("Restarting with new initial project")
        val newProject = mutationProblem.getProjectMessage()
        statistics = RoundStatistics(newProject)
        successfullyCompiledProjects.clear()
        checkedProjects.clear()
        eb.request<ProjectMessage>(VertxAddresses.addInvocations, newProject) { amsg ->
            if (amsg.succeeded()) {
                val project = amsg.result().body()
                eb.send(VertxAddresses.checkCompile, project)
            } else {
                println(amsg.cause())
                println(newProject)
                if (failedTimes++ > 10)
                    error(amsg.cause())
                else
                    startWithNewProject()
            }
        }
    }

    var failedTimes = 0

    private fun saveStatistics() {
        if (statistics.appliedTransformations.isNotEmpty()) {
            val fileName = CompilerArgs.statDir + Random.getRandomVariableName() + ".kt"
            File(fileName).writeText(statistics.toString())
        }
    }

    private fun sendNextTransformation() {
        if (mutationProblem.isNotFinished()) {
            if (successfullyCompiledProjects.isEmpty() ||
                successfullyCompiledProjects.size > LIMIT_OF_COMPILED_PROJECTS ||
                checkedProjects.size > 1000) {
                saveStatistics()
                startWithNewProject()
                return
            }
            val transformation = mutationProblem.getNextTransformationAndIncreaseCounter()
            lastTransformation = transformation
            val projectToSend = successfullyCompiledProjects.last()//getProjectsToSend(projects)
            eb.send(VertxAddresses.mutate,
                MutationRequest(
                    transformation,
                    listOf(projectToSend)
                )
            )
            log.debug("Transformation#${mutationProblem.completedMutations}: " +
                    "Sending the project to mutator to transform with $transformation")
        } else {
            successfullyCompiledProjects.clear()
            checkedProjects.clear()
            mutationProblem.completedMutations = 0
            startWithNewProject()
        }
    }

    private fun sendProjectToCompilers(mutationResult: MutationResult) {
        val projects = mutationResult.projects.filter { project ->
            project.getProjectMessageWithNewKlib() !in checkedProjects
        }.shuffled().take(MAX_PROJECTS_TO_COMPILERS)
        if (projects.isEmpty()) {
            sendNextTransformation()
            return
        }
        projects.forEach { it.isBinaryCompatible = isBinaryCompatible() }
        log.debug("Sending ${projects.size} projects to compiler")
        mutationProblem.compilers.forEach { address ->
            eb.send(address,
                CompilationRequest(projects, mutationResult.mutationStat)
            )
        }
    }

    private fun sendResultToBugManager(status: KotlincInvokeResult) {
        status.transformation = lastTransformation.javaClass.simpleName
        eb.send(
            VertxAddresses.bugManager, status
        )
    }

    private fun sendResultToStatistics(result: CompilationResult) {
        eb.send(VertxAddresses.transformationStatistics, result)
    }

    private fun getProjectsToSend(latestProjects: List<ProjectMessage>): List<ProjectMessage> {
//        if (successfullyCompiledProjects.isEmpty() || successfullyCompiledProjects.size > LIMIT_OF_COMPILED_PROJECTS) {
//            val newProject = mutationProblem.getProjectMessage()
//            log.debug("Created new starting project ${newProject.files.firstOrNull()?.name}")
//            successfullyCompiledProjects.clear()
//            checkedProjects.clear()
//            return listOf(newProject)
//        }
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

    private fun isBinaryCompatible() = lastTransformation is BinaryCompatibleTransformation

    val coordinatorNumber = counter.getAndIncrement()

    companion object {
        private val counter = AtomicInteger(0)
    }

    private lateinit var lastTransformation: Transformation
    private val MAX_PROJECTS_TO_MUTATE = 1
    private val MAX_PROJECTS_TO_COMPILERS = 500
    private val LIMIT_OF_COMPILED_PROJECTS = 10
//    private val LIMIT_OF_CHECKED_PROJECTS = 1_000_000
    private var checkedProjects = mutableSetOf<ProjectMessage>()
    private var successfullyCompiledProjects = mutableSetOf<ProjectMessage>()
    private lateinit var statistics: RoundStatistics

    private val log = Logger.getLogger("coordinatorLogger")

    private class RoundStatistics(val initialProject: ProjectMessage) {
        lateinit var resultedProject: ProjectMessage

        val appliedTransformations = mutableListOf<String>()

        fun addTransformation(t: Transformation) {
            appliedTransformations.add(t.javaClass.simpleName)
        }

        override fun toString(): String = """
            applied transformations: ${appliedTransformations.joinToString(", ")}
            started with:
            $initialProject
            resulted project:
            $resultedProject
        """.trimIndent()
    }
}