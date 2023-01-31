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
        createLocalTmpProject(project)
        val arguments = K2NativeCompilerArguments()
        val compilerArgumentsKlib = arrayOf(project.files.first().first,
            "-kotlin-home", "/home/olezhka/.konan/kotlin-native-prebuilt-linux-x86_64-1.8.0/",
            "-p", "library",
            "-o", "tmp/build"
        )

        K2Native().parseArguments(compilerArgumentsKlib , arguments)

        compiler.exec(MsgCollector, Services.EMPTY, arguments)


        if (MsgCollector.hasCompileError) {
            println(MsgCollector.compileErrorMessages[0])
        }
        if (MsgCollector.hasException) {
            println(MsgCollector.compileErrorMessages[0])
        }

        TODO("Not yet implemented")
    }




}