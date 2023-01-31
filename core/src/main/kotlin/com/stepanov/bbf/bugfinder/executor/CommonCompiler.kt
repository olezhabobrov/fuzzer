package com.stepanov.bbf.bugfinder.executor

import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.vertx.information.VertxAddresses
import com.stepanov.bbf.bugfinder.vertx.serverMessages.ProjectMessage
import com.stepanov.bbf.reduktor.executor.CompilationResult
import com.stepanov.bbf.reduktor.executor.KotlincInvokeStatus
import io.vertx.core.AbstractVerticle
import org.apache.log4j.Logger
import org.jetbrains.kotlin.cli.common.arguments.CommonCompilerArguments
import org.jetbrains.kotlin.cli.common.arguments.K2JSCompilerArguments
import org.jetbrains.kotlin.cli.common.arguments.K2JVMCompilerArguments
import org.jetbrains.kotlin.cli.js.K2JSCompiler
import org.jetbrains.kotlin.cli.jvm.K2JVMCompiler
import java.io.File
import java.util.concurrent.atomic.AtomicInteger

abstract class CommonCompiler: AbstractVerticle() {

    override fun start() {
        establishConsumers()
        log.debug("Compiler deployed")
    }

    abstract fun tryToCompile(project: ProjectMessage): KotlincInvokeStatus

    private fun establishConsumers() {
        val eb = vertx.eventBus()
        eb.consumer<ProjectMessage>(compileAddress) { msg ->
            val compileResult = tryToCompile(msg.body())
            eb.send(resultAddress,
                CompilationResult(
                    this::class.java.simpleName,
                    compileResult,
                    msg.body()
                )
            )
        }.exceptionHandler { throwable ->
            log.debug("""Caught an exception in compiler#$instanceNumber
                | Exception: ${throwable.stackTraceToString()}
            """.trimMargin())
        }
    }

    protected fun createLocalTmpProject(project: ProjectMessage) {
        project.files.forEach { (name, text) ->
            File(name.substringBeforeLast("/")).mkdirs()
            File(name).writeText(text)
        }
    }

    protected fun deleteLocalTmpProject(project: ProjectMessage) {
        project.files.forEach { (name, text) ->
            File(name).delete()
        }
    }

    protected fun getAllPathsInLine(project: ProjectMessage): String {
        return project.files.map { it.first }.joinToString(" ")
    }

//    fun getProjectSettingsAsCompilerArgs(backendType: String): CommonCompilerArguments {
//        val args = when (backendType) {
//            "JVM" -> K2JVMCompilerArguments()
//            else -> K2JSCompilerArguments()
//        }
//        val languageDirective = "-XXLanguage:"
//        val languageFeaturesAsArgs = configuration.languageSettings.joinToString(
//            separator = " $languageDirective",
//            prefix = languageDirective,
//        ).split(" ")
//        when (backendType) {
//            "JVM" -> args.apply {
//                K2JVMCompiler().parseArguments(
//                    languageFeaturesAsArgs.toTypedArray(),
//                    this as K2JVMCompilerArguments
//                )
//            }
//            "JS" -> args.apply {
//                K2JSCompiler().parseArguments(
//                    languageFeaturesAsArgs.toTypedArray(),
//                    this as K2JSCompilerArguments
//                )
//            }
//        }
//        args.optIn = configuration.useExperimental.toTypedArray()
//        return args
//    }

    companion object {
        var counter = AtomicInteger(0)
        val resultAddress = VertxAddresses.compileResult
        val compileAddress = VertxAddresses.compile // + "${instanceNumber}"
    }

    private val instanceNumber = counter.getAndIncrement()

    protected val log = Logger.getLogger("compilerLogger")
}

