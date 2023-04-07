package com.stepanov.bbf

import com.stepanov.bbf.codecs.CompilationResultCodec
import com.stepanov.bbf.codecs.CompilationRequestCodec
import com.stepanov.bbf.information.*
import com.stepanov.bbf.messages.*
import io.vertx.core.AbstractVerticle
import org.slf4j.Logger
import java.io.File
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException
import org.slf4j.LoggerFactory
import java.lang.Exception


abstract class CommonCompiler(
    private val compileAddress: String
): AbstractVerticle() {

    override fun start() {
        establishConsumers()
        registerCodecs()
        log.debug("Compiler deployed")
    }

    abstract fun executeCompilationCheck(project: ProjectMessage): KotlincInvokeResult

    private fun registerCodecs() {
        vertx.eventBus().registerDefaultCodec(CompilationRequest::class.java, CompilationRequestCodec())
        vertx.eventBus().registerDefaultCodec(CompilationResult::class.java, CompilationResultCodec())
    }

    private fun establishConsumers() {
        val eb = vertx.eventBus()
        eb.consumer<CompilationRequest>(compileAddress) { msg ->

            val request = msg.body()
            log.debug("Got ${request.projects.size} projects to compile")
            val compileResults = mutableListOf<KotlincInvokeResult>()
            request.projects.forEach { projectMessage ->
                createLocalTmpProject(projectMessage)
                val compileResult = executeCompilationCheck(projectMessage)
                compileResults.add(compileResult)
            }
            log.debug("Sending back compile results")
            eb.send(
                VertxAddresses.compileResult,
                CompilationResult(
                    this::class.java.simpleName,
                    compileResults,
                    request.mutationStat
                )
            )
        }.exceptionHandler { throwable ->
            log.debug("""Caught an exception in compiler
                | Exception: ${throwable.stackTraceToString()}
            """.trimMargin())
        }
    }

    private fun createLocalTmpProject(project: ProjectMessage) {
        File(project.dir).mkdir()
        project.files.forEach { (name, text) ->
            File(project.dir + name).writeText(text)
        }
    }

    protected fun getAllPathsInLine(project: ProjectMessage): String {
        return project.files.map { it.first }.joinToString(" ")
    }

    /**
     *  @return if it was compiled without timeout
     */
    protected fun executeCompiler(
        task: Runnable
    ): Boolean {
        val threadPool = Executors.newCachedThreadPool()
        val futureExitCode = threadPool.submit(task)
        return try {
            futureExitCode.get(10L, TimeUnit.SECONDS)
            true
        } catch (ex: TimeoutException) {
            futureExitCode.cancel(true)
            false
        }
    }

    companion object {
        val compilerToConfigMap: Map<String, List<CompilationConfiguration>> = mapOf(
            VertxAddresses.NativeCompiler to listOf(
//                ProduceLibrary,
//                PartialLinkage(),
                CompilationConfiguration.Split
            ),
        )
    }

    protected val log: Logger = LoggerFactory.getLogger("CompilerLogger")
}