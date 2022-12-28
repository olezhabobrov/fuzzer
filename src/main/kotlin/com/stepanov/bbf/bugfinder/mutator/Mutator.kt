package com.stepanov.bbf.bugfinder.mutator

import com.stepanov.bbf.bugfinder.mutator.transformations.*
import com.stepanov.bbf.bugfinder.vertx.MutationOptions
import com.stepanov.bbf.bugfinder.vertx.VertxAddresses
import io.vertx.core.AbstractVerticle
import org.apache.log4j.Logger
import kotlin.random.Random

class Mutator(val strategy: MutationStrategy): AbstractVerticle() {

    override fun start() {
        val eb = vertx.eventBus()
        eb.consumer<MutationOptions>(VertxAddresses.startMutations) { options ->
            startMutate()
        }
    }

    fun executeMutation(t: Transformation) {
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
        strategy.transformations.forEach {
            executeMutation(it)
        }
    }

    companion object {
        var instanceCounter = 0
    }

    val instanceNumber: Int = ++instanceCounter

    private val log = Logger.getLogger("bugFinderLogger")
}
