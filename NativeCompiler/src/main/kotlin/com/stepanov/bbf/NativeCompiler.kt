package com.stepanov.bbf

import com.stepanov.bbf.information.CompilationArgs
import com.stepanov.bbf.information.VertxAddresses
import com.stepanov.bbf.messages.KotlincInvokeResult
import com.stepanov.bbf.messages.KotlincInvokeStatus
import com.stepanov.bbf.messages.ProjectMessage
import org.jetbrains.kotlin.cli.bc.K2Native
import org.jetbrains.kotlin.cli.common.arguments.K2NativeCompilerArguments
import org.jetbrains.kotlin.config.Services


class NativeCompiler: CommonCompiler(VertxAddresses.NativeCompiler) {


    override fun start() {
        log.debug("Started Native Compiler")
        super.start()
    }



    override fun executeCompilationCheck(project: ProjectMessage): KotlincInvokeResult {
        val argsList = CompilationArgsGenerator.getAllCombinations(project)
        val statuses = argsList.map { compile(it) }
        return KotlincInvokeResult(project, statuses)
    }

    private fun compile(args: CompilationArgs): KotlincInvokeStatus {
//        log.debug("Trying to compile with args:\n $args")

        if (args.klib != null) {
//            log.debug("Trying to compile klib first")
            val result = compile(args.klib!!)
            if (result.hasCompilerCrash() || !result.isCompileSuccess) {
//                log.debug("klib was not compiled successfully")
                return result
            }
        }

        val hasTimeout = !executeCompiler {
            log.debug("Before")
            MsgCollector.clear()
            val services = Services.EMPTY
            val compiler = K2Native()
            val args = createArguments(args.build())
            compiler.exec(MsgCollector, services, args)
            log.debug("After")
        }
        val status = KotlincInvokeStatus(
            MsgCollector.crashMessages.joinToString("\n") +
                    MsgCollector.compileErrorMessages.joinToString("\n"),
            !MsgCollector.hasCompileError,
            MsgCollector.hasException,
            hasTimeout,
            args
        )
        return status
    }

    private val kotlinHome = System.getenv("kotlin-home")
        ?: error("kotlin-home not specified in environment variables (should be in build.gradle)")

    private fun createArguments(args: List<String>): K2NativeCompilerArguments {
        val compilerArgumentsList = mutableListOf(
            "-kotlin-home", kotlinHome
        )
        compilerArgumentsList.addAll(args)
        val arguments = K2NativeCompilerArguments()
        K2Native().parseArguments(compilerArgumentsList.toTypedArray() , arguments)
        return arguments
    }


}