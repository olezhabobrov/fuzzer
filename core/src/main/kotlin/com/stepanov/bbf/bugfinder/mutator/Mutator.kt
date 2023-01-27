package com.stepanov.bbf.bugfinder.mutator

import com.stepanov.bbf.bugfinder.mutator.transformations.*
import com.stepanov.bbf.bugfinder.mutator.vertxMessages.MutationResult
import com.stepanov.bbf.bugfinder.mutator.vertxMessages.MutationStrategy
import com.stepanov.bbf.bugfinder.vertx.information.VertxAddresses
import io.vertx.core.AbstractVerticle
import org.apache.log4j.Logger
import kotlin.RuntimeException
import kotlin.random.Random

class Mutator: AbstractVerticle() {

    private var strategy: MutationStrategy? = null

    override fun start() {
        establishConsumers()
    }

    private fun establishConsumers() {
        vertx.eventBus().consumer<MutationStrategy>(mutateAddress) { newStrategy ->
            strategy = newStrategy.body()
            startMutate()
            sendMutatedProject()
        }
//            .exceptionHandler { throwable ->
//            log.debug("""Caught an exception in mutator#$instanceNumber
//                | While mutating strategy#${strategy?.number}
//                | Exception: ${throwable.stackTraceToString()}
//            """.trimMargin())
//        }
    }

    private fun executeMutation(t: Transformation) {
        if (Random.nextInt(0, 100) < t.probPercentage) {
            //Update ctx
            t.updateCtx()
            t.ctx ?: TODO("Figure out why ctx can be null")
            log.debug("Cur transformation ${t::class.simpleName}")
            t.execTransformations()
            // TODO: send new file text
            t.file.changePsiFile(t.file.text)
        }
    }

    private fun sendMutatedProject() {
        log.debug("Sending back project, mutated by mutation strategy #${strategy?.number}")
        vertx.eventBus().send(resultAddress,
            MutationResult(strategy!!.project, strategy!!.number)
        )
    }

    // TODO: make private
    fun startMutate() {
        log.debug("Starting mutating for strategy #${strategy?.number}")
        strategy?.transformations?.forEach {
            executeMutation(it)
        } ?: throw RuntimeException("Called startMutate but no strategy provided")
    }

    companion object {
        var instanceCounter = 0
        val resultAddress = VertxAddresses.mutatedProject// + "#${instanceNumber}"
        val mutateAddress = VertxAddresses.mutate // + "#${instanceNumber}"
    }
    private val instanceNumber: Int = ++instanceCounter

    private val log = Logger.getLogger("bugFinderLogger")
}
