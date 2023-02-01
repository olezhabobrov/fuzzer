package com.stepanov.bbf

import com.stepanov.bbf.bugfinder.executor.CommonCompiler
import com.stepanov.bbf.bugfinder.vertx.serverMessages.ProjectMessage
import com.stepanov.bbf.reduktor.executor.KotlincInvokeStatus
import com.stepanov.bbf.reduktor.util.MsgCollector
import org.jetbrains.kotlin.cli.bc.K2Native
import org.jetbrains.kotlin.cli.common.arguments.K2NativeCompilerArguments
import org.jetbrains.kotlin.config.Services

class NativeCompiler: CommonCompiler() {
    private val compiler = K2Native()

    override fun start() {
        log.debug("Started Native Compiler")
        super.start()
    }

    override fun tryToCompile(project: ProjectMessage): KotlincInvokeStatus {
        val arguments = createArguments(project)
        return executeCompiler(project) {
            val services = Services.EMPTY
            compiler.exec(MsgCollector, services, arguments)
        }
    }

    private fun createArguments(project: ProjectMessage): K2NativeCompilerArguments {
        val compilerArgumentsList = mutableListOf(
            "-kotlin-home", System.getenv("kotlin-home")
                ?: error("kotlin-home not specified in environment variables (should be in build.gradle)"),
            "-p", "library",
            "-o", project.outputDir + "result"
        )
        project.files.forEach { compilerArgumentsList.add(it.first) }
        val arguments = K2NativeCompilerArguments()
        K2Native().parseArguments(compilerArgumentsList.toTypedArray() , arguments)
        return arguments
    }


}