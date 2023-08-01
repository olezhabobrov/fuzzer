package com.stepanov.bbf.bugfinder.mutator

import com.stepanov.bbf.bugfinder.mutator.transformations.FTarget
import com.stepanov.bbf.bugfinder.mutator.transformations.Transformation
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.generators.RandomFunctionGenerator
import com.stepanov.bbf.bugfinder.mutator.transformations.tce.UsagesSamplesGenerator
import com.stepanov.bbf.bugfinder.mutator.transformations.util.Invocator
import com.stepanov.bbf.bugfinder.mutator.vertxMessages.MutationRequest
import com.stepanov.bbf.bugfinder.mutator.vertxMessages.MutationResult
import com.stepanov.bbf.bugfinder.project.Project
import com.stepanov.bbf.information.MutationStat
import com.stepanov.bbf.information.VertxAddresses
import com.stepanov.bbf.messages.FileData
import com.stepanov.bbf.messages.ProjectMessage
import io.vertx.core.AbstractVerticle
import org.apache.log4j.Logger
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException
import kotlin.system.measureTimeMillis

class Mutator: AbstractVerticle() {

    override fun start() {
        establishConsumers()
    }

    private fun establishConsumers() {
        vertx.eventBus().consumer<MutationRequest>(VertxAddresses.mutate) { msg ->
            try {
                val request = msg.body()
                log.debug("Got mutation request")
                val result = mutate(request)
//                log.debug("Completed mutation for transformation#${request.transformationNumber}")
                vertx.eventBus().send(VertxAddresses.mutationResult, result)
            } catch(e: Throwable) {
                log.debug("Caught exception while mutating: ${e.stackTraceToString()}")
                msg.fail(1, e.message)
            }
        }

        vertx.eventBus().consumer<ProjectMessage>(VertxAddresses.addInvocations) { msg ->
            val projectMessage = msg.body()
            val project = Project(ProjectMessage(mutableListOf(
                projectMessage.klib, FileData("main.kt", """
                fun main() {}
            """.trimIndent())
            ), null))
            val file = project.klib
            file.updateCtx()
            val ftarget = FTarget(project, file)
            Invocator.addInvocationOfAllCallable(ftarget)
            val newProject = project.createProjectMessage()
            project.dispose()
            msg.reply(newProject)
        }
    }

    private fun executeMutation(t: Transformation, projectMessage: ProjectMessage): Set<ProjectMessage> {
        log.debug("Cur transformation ${t::class.simpleName}")
        return t.execTransformations(projectMessage)
    }

    private fun mutate(request: MutationRequest): MutationResult {
        val simpleName = request.transformation.javaClass.simpleName
        println("STARTING $simpleName")
        require(request.targets.isNotEmpty()) {
            log.debug("Got mutation request with empty targets")
        }
        val results = mutableSetOf<ProjectMessage>()
        val threadPool = Executors.newCachedThreadPool()

        val timeList = mutableListOf<Long>()
        var timeouts = 0
        var successfulMutations =  0
        var unSuccessfulMutations =  0
        var newProjectsProduced =  0
        var totalMutations = 0
        var uselessMutations = 0

        request.targets.forEach { projectMessage ->
            totalMutations++
            val timeInMillis = measureTimeMillis {
                val futureExitCode = threadPool.submit {
                    val newResults = executeMutation(request.transformation, projectMessage)
                    if (newResults.isNotEmpty())
                        successfulMutations++
                    else
                        uselessMutations++
                    newProjectsProduced += newResults.size
                    results.addAll(newResults)
                }
                try {
                    futureExitCode.get(TIMEOUT, TimeUnit.SECONDS)
                } catch (e: TimeoutException) {
                    timeouts++
                    futureExitCode.cancel(true)
                    log.debug("Timeout of $TIMEOUT seconds in $simpleName")
                } catch (e: Throwable) {
                    unSuccessfulMutations++
                    log.debug("Caught exception in ${simpleName}: ${e.stackTraceToString()}")
                }
            }
            timeList.add(timeInMillis)
        }
        println("FINISHING $simpleName")
        return MutationResult(results, MutationStat(
            request.transformation.javaClass.simpleName,
            totalMutations,
            successfulMutations,
            uselessMutations,
            unSuccessfulMutations,
            results.size.toDouble() / request.targets.size,
            timeList.average().toLong(),
            timeouts
        )
        )
    }

    private val log = Logger.getLogger("mutatorLogger")

    private val TIMEOUT = 120L
}
