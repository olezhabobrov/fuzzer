package com.stepanov.bbf

import com.stepanov.bbf.information.*
import com.stepanov.bbf.messages.KotlincInvokeStatus
import com.stepanov.bbf.messages.ProjectMessage
import org.jetbrains.kotlin.cli.bc.K2Native
import org.jetbrains.kotlin.cli.common.arguments.K2NativeCompilerArguments
import org.jetbrains.kotlin.config.Services


class NativeCompiler: CommonCompiler(VertxAddresses.NativeCompiler) {

    private val compiler = K2Native()

    override fun start() {
        log.debug("Started Native Compiler")
        super.start()
    }

    override fun executeCompilationCheck(project: ProjectMessage): KotlincInvokeStatus {
//        val arguments = createArguments(project)
        val arguments = CompilationArgsBuilder()
        arguments.add(project.configuration)
        when (project.configuration) {
            CompilationConfiguration.Split -> {
                project.files.forEach { (name, _) ->
                    val klibName = createKlib(project, name)
                    if (klibName == null) {
                        log.debug("Couldn't ")
                    }
                }
                log.debug("")
                project.files.first().first.getSimpleFileName()
            }
            else -> {}
        }
        TODO()
    }

    private fun createKlib(project: ProjectMessage, name: String): String? {
        val args = CompilationArgsBuilder()
            .add(CompilationConfiguration.ProduceLibrary)
            .addOutput(project.outputDir, name.getSimpleFileName())
            .build()
        val result = compile(project, createArguments(args))
        if (!result.hasCompilerCrash() && result.isCompileSuccess) {
            return "${project.outputDir}/${name.getSimpleFileName()}.klib"
        } else {
            return null
        }
    }

    private fun compile(project: ProjectMessage, args: K2NativeCompilerArguments): KotlincInvokeStatus {
        val hasTimeout = !executeCompiler {
            MsgCollector.clear()
            val services = Services.EMPTY
            compiler.exec(MsgCollector, services, args)
        }
        val status = KotlincInvokeStatus(
            MsgCollector.crashMessages.joinToString("\n") +
                    MsgCollector.compileErrorMessages.joinToString("\n"),
            !MsgCollector.hasCompileError,
            MsgCollector.hasException,
            hasTimeout
        )
        return status
    }

    private fun createArguments(args: List<String>): K2NativeCompilerArguments {
        val compilerArgumentsList = mutableListOf(
            "-kotlin-home", System.getenv("kotlin-home")
                ?: error("kotlin-home not specified in environment variables (should be in build.gradle)"),
//            "-o", project.outputDir + "result"
        )
        compilerArgumentsList.addAll(args)
        val arguments = K2NativeCompilerArguments()
        K2Native().parseArguments(compilerArgumentsList.toTypedArray() , arguments)
        return arguments
    }


}