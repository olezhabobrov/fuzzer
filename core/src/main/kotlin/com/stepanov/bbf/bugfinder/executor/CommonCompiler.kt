package com.stepanov.bbf.bugfinder.executor

import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.vertx.information.VertxAddresses
import com.stepanov.bbf.reduktor.executor.CompilationResult
import com.stepanov.bbf.reduktor.executor.KotlincInvokeStatus
import io.vertx.core.AbstractVerticle
import org.apache.log4j.Logger
import java.util.concurrent.atomic.AtomicInteger

abstract class CommonCompiler: AbstractVerticle() {

    override fun start() {
        establishConsumers()
        log.debug("Compiler deployed")
    }

    abstract fun tryToCompile(project: Project): KotlincInvokeStatus

    private fun establishConsumers() {
        val eb = vertx.eventBus()
        eb.consumer<Project>(compileAddress) { msg ->
            val compileResult = tryToCompile(msg.body())
            eb.send(resultAddress, CompilationResult(compileResult, this, msg.body()))
        }.exceptionHandler { throwable ->
            log.debug("""Caught an exception in compiler#$instanceNumber
                | Exception: ${throwable.stackTraceToString()}
            """.trimMargin())
        }
    }

    companion object {
        var counter = AtomicInteger(0)
        val resultAddress = VertxAddresses.compileResult
        val compileAddress = VertxAddresses.compile // + "${instanceNumber}"
    }

    private val instanceNumber = counter.getAndIncrement()

    protected val log = Logger.getLogger("compilerLogger")
}

