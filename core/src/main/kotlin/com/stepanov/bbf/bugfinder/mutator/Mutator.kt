package com.stepanov.bbf.bugfinder.mutator

import com.stepanov.bbf.bugfinder.mutator.transformations.*
import com.stepanov.bbf.bugfinder.mutator.vertxMessages.MutationResult
import com.stepanov.bbf.bugfinder.mutator.vertxMessages.MutationStrategy
import com.stepanov.bbf.information.VertxAddresses
import io.vertx.core.AbstractVerticle
import org.apache.log4j.Logger
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException
import kotlin.random.Random

class Mutator: AbstractVerticle() {

    override fun start() {
        establishConsumers()
    }

    private fun establishConsumers() {
        vertx.eventBus().consumer<MutationStrategy>(VertxAddresses.mutate) { msg ->
            try {
                val strategy = msg.body()
                log.debug("Got mutation strategy#${strategy!!.number}")
                startMutate(strategy)
                sendMutationResult(MutationResult(strategy.project, strategy.number, true))
            } catch(e: Throwable) {
                log.debug("Caught exception while mutating: ${e.stackTraceToString()}")
                msg.fail(1, e.message)
            }
        }
    }

    private fun executeMutation(t: Transformation) {
        if (Random.nextInt(0, 100) < t.probPercentage) {
            //Update ctx
            t.file.updateCtx()
            log.debug("Cur transformation ${t::class.simpleName}")
            t.execTransformations()
        }
    }

    private fun startMutate(strategy: MutationStrategy) {
        log.debug("Starting mutating for strategy #${strategy.number}")
        val threadPool = Executors.newCachedThreadPool()
        strategy.transformations.forEach {
            println("STARTING ${it.javaClass.simpleName}")
            if (Random.nextInt(0, 100) < 30) {
                sendMutationResult(MutationResult(strategy.project, strategy.number))
            }
            val futureExitCode = threadPool.submit {
                executeMutation(it)
            }
            try {
                futureExitCode.get(TIMEOUT, TimeUnit.SECONDS)
            } catch (e: TimeoutException) {
                futureExitCode.cancel(true)
                log.debug("Timeout of $TIMEOUT seconds in ${it.javaClass.simpleName}")
            } catch (e: Throwable) {
                log.debug("Caught exception in ${it.javaClass.simpleName}: ${e.stackTraceToString()}")
            }
            println("FINISHING ${it.javaClass.simpleName}")
        }
    }

    private fun sendMutationResult(mutationResult: MutationResult) {
        log.debug("Sending back project, mutated by mutation strategy #${mutationResult.strategyNumber}")
        vertx.eventBus().send(VertxAddresses.mutationResult, mutationResult)
    }

    private val log = Logger.getLogger("mutatorLogger")

    private val TIMEOUT = 30L
}
