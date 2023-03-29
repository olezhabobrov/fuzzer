package com.stepanov.bbf.bugfinder.mutator

import com.stepanov.bbf.bugfinder.mutator.transformations.*
import com.stepanov.bbf.bugfinder.mutator.vertxMessages.MutationRequest
import com.stepanov.bbf.bugfinder.mutator.vertxMessages.MutationResult
import com.stepanov.bbf.bugfinder.project.Project
import com.stepanov.bbf.information.VertxAddresses
import com.stepanov.bbf.messages.ProjectMessage
import io.vertx.core.AbstractVerticle
import org.apache.log4j.Logger
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

class Mutator: AbstractVerticle() {

    override fun start() {
        establishConsumers()
    }

    private fun establishConsumers() {
        vertx.eventBus().consumer<MutationRequest>(VertxAddresses.mutate) { msg ->
            try {
                val request = msg.body()
                log.debug("Got mutation request for strategy#${request!!.strategyNumber}")
                val results = mutate(request)
//                log.debug("Completed mutation for transformation#${request.transformationNumber}")
                vertx.eventBus().send(VertxAddresses.mutationResult,
                    MutationResult(results, request.strategyNumber, request.transformation.javaClass.simpleName)
                )
            } catch(e: Throwable) {
                log.debug("Caught exception while mutating: ${e.stackTraceToString()}")
                msg.fail(1, e.message)
            }
        }
    }

    private fun executeMutation(t: Transformation, projectMessage: ProjectMessage): Set<ProjectMessage> {
        log.debug("Cur transformation ${t::class.simpleName}")
        return t.execTransformations(projectMessage)
    }

    private fun mutate(request: MutationRequest): Set<ProjectMessage> {
        val simpleName = request.transformation.javaClass.simpleName
        println("STARTING $simpleName")
        val results = mutableSetOf<ProjectMessage>()
        val threadPool = Executors.newCachedThreadPool()
        request.targets.forEach { projectMessage ->
            val futureExitCode = threadPool.submit {
                val newResults = executeMutation(request.transformation, projectMessage)
                results.addAll(newResults)
            }
            try {
                futureExitCode.get(TIMEOUT, TimeUnit.SECONDS)
            } catch (e: TimeoutException) {
                futureExitCode.cancel(true)
                log.debug("Timeout of $TIMEOUT seconds in $simpleName")
            } catch (e: Throwable) {
                log.debug("Caught exception in ${simpleName}: ${e.stackTraceToString()}")
            }
        }
        println("FINISHING $simpleName")
        return results
    }

    private val log = Logger.getLogger("mutatorLogger")

    private val TIMEOUT = 120L
}
