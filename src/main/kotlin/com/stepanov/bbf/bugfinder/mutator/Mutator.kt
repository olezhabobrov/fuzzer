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
        val eb = vertx.eventBus()
        eb.consumer<MutationStrategy>(mutateAddress) { newStrategy ->
            strategy = newStrategy.body()
            startMutate()
        }
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
        vertx.eventBus().send(resultAddress,
            MutationResult(strategy!!.project, strategy!!.number)
        )
    }

    // TODO: make private
    fun startMutate() {
        strategy?.transformations?.forEach {
            executeMutation(it)
        } ?: throw RuntimeException("Called startMutate but no strategy provided")
        sendMutatedProject()
    }

    companion object {
        var instanceCounter = 0
        val resultAddress = VertxAddresses.mutatedProject// + "#${instanceNumber}"
    }

    private val instanceNumber: Int = ++instanceCounter
    val mutateAddress = VertxAddresses.mutate + "#${instanceNumber}"

    private val log = Logger.getLogger("bugFinderLogger")
}
