package com.stepanov.bbf

import com.stepanov.bbf.codecs.CompilationResultCodec
import com.stepanov.bbf.codecs.ProjectCodec
import com.stepanov.bbf.information.VertxAddresses
import com.stepanov.bbf.messages.ProjectMessage
import com.stepanov.bbf.messages.CompilationResult
import com.stepanov.bbf.messages.KotlincInvokeStatus
import io.vertx.core.AbstractVerticle
import org.slf4j.Logger
import java.io.File
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException
import org.slf4j.LoggerFactory


abstract class CommonCompiler(
    private val compileAddress: String
): AbstractVerticle() {

    override fun start() {
        establishConsumers()
        registerCodecs()
        log.debug("Compiler deployed")
    }

    abstract fun tryToCompile(project: ProjectMessage): KotlincInvokeStatus

    private fun registerCodecs() {
        vertx.eventBus().registerDefaultCodec(ProjectMessage::class.java, ProjectCodec())
        vertx.eventBus().registerDefaultCodec(CompilationResult::class.java, CompilationResultCodec())
    }

    private fun establishConsumers() {
        val eb = vertx.eventBus()
        eb.consumer<ProjectMessage>(compileAddress) { msg ->
            log.debug("Got a project to compile")
            val project = msg.body()
            createLocalTmpProject(project)
            val compileResult = tryToCompile(project)
            deleteLocalTmpProject(project)
            log.debug("Sending back compile result")
            eb.send(
                VertxAddresses.compileResult,
                CompilationResult(
                    this::class.java.simpleName,
                    compileResult,
                    msg.body()
                )
            )
        }.exceptionHandler { throwable ->
            log.debug("""Caught an exception in compiler
                | Exception: ${throwable.stackTraceToString()}
            """.trimMargin())
        }
    }

    private fun createLocalTmpProject(project: ProjectMessage) {
        project.files.forEach { (name, text) ->
            File(name.substringBeforeLast("/")).mkdir()
            File(name).writeText(text)
        }
        File(project.outputDir).mkdir()
    }

    private fun deleteLocalTmpProject(project: ProjectMessage) {
        project.files.forEach { (name, _) ->
            if (File(name).exists())
                File(name).deleteRecursively()
        }
        File(project.outputDir).deleteRecursively()
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

    protected val log: Logger = LoggerFactory.getLogger("CompilerLogger")
}