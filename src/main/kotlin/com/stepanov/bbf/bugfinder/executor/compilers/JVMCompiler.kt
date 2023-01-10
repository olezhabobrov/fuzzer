package com.stepanov.bbf.bugfinder.executor.compilers

import com.stepanov.bbf.bugfinder.executor.CommonCompiler
import com.stepanov.bbf.bugfinder.executor.CompilerArgs
import com.stepanov.bbf.bugfinder.executor.project.Directives
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.coverage.CompilerInstrumentation
import com.stepanov.bbf.reduktor.executor.KotlincInvokeStatus
import com.stepanov.bbf.reduktor.util.MsgCollector
import org.apache.commons.io.FileUtils
import org.jetbrains.kotlin.cli.common.arguments.K2JVMCompilerArguments
import org.jetbrains.kotlin.cli.jvm.K2JVMCompiler
import org.jetbrains.kotlin.config.Services
import java.io.File
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

open class JVMCompiler: CommonCompiler() {

    // TODO: add some additional arguments maybe
    private fun prepareArgs(project: Project, path: String, destination: String): K2JVMCompilerArguments {
        val destFile = File(destination)
        if (destFile.isFile) destFile.delete()
        else if (destFile.isDirectory) FileUtils.cleanDirectory(destFile)
        val projectArgs = project.getProjectSettingsAsCompilerArgs("JVM") as K2JVMCompilerArguments
        val compilerArgs = "$path -d $destination".split(" ")
        projectArgs.apply { K2JVMCompiler().parseArguments(compilerArgs.toTypedArray(), this) }
        //projectArgs.compileJava = true
        projectArgs.classpath =
            "${CompilerArgs.jvmStdLibPaths.joinToString(
                separator = ":"
            )}:${System.getProperty("java.class.path")}"
                .split(":")
                .filter { it.isNotEmpty() }
                .toSet().toList()
                .joinToString(":")
        projectArgs.jvmTarget = "1.8"
        projectArgs.optIn = arrayOf("kotlin.ExperimentalStdlibApi", "kotlin.contracts.ExperimentalContracts")
        if (project.configuration.jvmDefault.isNotEmpty())
            projectArgs.jvmDefault = project.configuration.jvmDefault.substringAfter(Directives.jvmDefault)
        //TODO!!
        //if (project.configuration.samConversion.isNotEmpty()) {
        //val samConvType = project.configuration.samConversion.substringAfterLast(": ")
        //projectArgs.samConversions = samConvType.toLowerCase()
        //}
        return projectArgs
    }

    private fun executeCompiler(project: Project, args: K2JVMCompilerArguments): KotlincInvokeStatus {
        val compiler = K2JVMCompiler()
        val services = Services.EMPTY
        MsgCollector.clear()
        val threadPool = Executors.newCachedThreadPool()
        if (CompilerArgs.isInstrumentationMode) {
            CompilerInstrumentation.clearRecords()
            CompilerInstrumentation.shouldProbesBeRecorded = true
        } else {
            CompilerInstrumentation.shouldProbesBeRecorded = false
        }
        val futureExitCode = threadPool.submit {
            compiler.exec(MsgCollector, services, args)
        }
        var hasTimeout = false
        var compilerWorkingTime: Long = -1
        try {
            val startTime = System.currentTimeMillis()
            futureExitCode.get(10L, TimeUnit.SECONDS)
            compilerWorkingTime = System.currentTimeMillis() - startTime
        } catch (ex: TimeoutException) {
            hasTimeout = true
            futureExitCode.cancel(true)
        } finally {
            project.saveOrRemoveToTmp(false)
        }
        if (CompilerArgs.isInstrumentationMode) {
            CompilerInstrumentation.shouldProbesBeRecorded = false
        }
        val status = KotlincInvokeStatus(
            MsgCollector.crashMessages.joinToString("\n") +
                    MsgCollector.compileErrorMessages.joinToString("\n"),
            !MsgCollector.hasCompileError,
            MsgCollector.hasException,
            hasTimeout,
            compilerWorkingTime,
            MsgCollector.locations.toMutableList()
        )
        //println(status.combinedOutput)
        return status
    }

    override fun tryToCompile(project: Project): KotlincInvokeStatus {
        val path = project.saveOrRemoveToTmp(true)
        val trashDir = "${CompilerArgs.pathToTmpDir}/trash/"
        val args = prepareArgs(project, path, trashDir)
        return executeCompiler(project, args)
    }

}