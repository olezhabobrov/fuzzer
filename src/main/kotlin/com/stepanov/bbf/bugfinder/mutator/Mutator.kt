package com.stepanov.bbf.bugfinder.mutator

import com.stepanov.bbf.bugfinder.mutator.transformations.*
import com.stepanov.bbf.bugfinder.vertx.MutationOptions
import com.stepanov.bbf.bugfinder.vertx.VertxAddresses
import io.vertx.core.AbstractVerticle
import org.apache.log4j.Logger
import java.lang.RuntimeException
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

    fun startMutate() {
        strategy?.transformations?.forEach {
            executeMutation(it)
        } ?: throw RuntimeException("Called startMutate but no strategy provided")
    }

    companion object {
        var instanceCounter = 0
    }

    private val instanceNumber: Int = ++instanceCounter

    val mutateAddress = VertxAddresses.mutate + "#${instanceNumber}"

    private val log = Logger.getLogger("bugFinderLogger")
}
