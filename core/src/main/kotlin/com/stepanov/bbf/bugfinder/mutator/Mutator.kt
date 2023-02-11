package com.stepanov.bbf.bugfinder.mutator

import com.stepanov.bbf.bugfinder.mutator.transformations.*
import com.stepanov.bbf.bugfinder.mutator.vertxMessages.MutationResult
import com.stepanov.bbf.bugfinder.mutator.vertxMessages.MutationStrategy
import com.stepanov.bbf.information.VertxAddresses
import io.vertx.core.AbstractVerticle
import org.apache.log4j.Logger
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
                log.debug("Sending back project, mutated by mutation strategy #${strategy.number}")
                msg.reply(MutationResult(strategy.project, strategy.number))
            } catch(e: Throwable) {
                log.debug("Caught exception while mutating: ${e.stackTraceToString()}")
                msg.fail(1, e.message)
            }
        }
    }

    private fun executeMutation(t: Transformation) {
        if (Random.nextInt(0, 100) < t.probPercentage) {
            //Update ctx
            t.updateCtx()
            t.ctx ?: return
            log.debug("Cur transformation ${t::class.simpleName}")
            t.execTransformations()
            t.file.changePsiFile(t.file.text)
        }
    }

    private fun startMutate(strategy: MutationStrategy) {
        log.debug("Starting mutating for strategy #${strategy.number}")
        strategy.transformations.forEach {
            executeMutation(it)
        }
    }

    private val log = Logger.getLogger("mutatorLogger")
}
