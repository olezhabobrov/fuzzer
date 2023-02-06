package com.stepanov.bbf

import com.stepanov.bbf.bugfinder.executor.CommonCompiler
import com.stepanov.bbf.bugfinder.executor.CompilerArgs
import com.stepanov.bbf.bugfinder.vertx.information.VertxAddresses
import com.stepanov.bbf.bugfinder.vertx.serverMessages.ProjectMessage
import com.stepanov.bbf.reduktor.executor.KotlincInvokeStatus
import com.stepanov.bbf.reduktor.util.MsgCollector
import org.apache.commons.io.FileUtils
import org.jetbrains.kotlin.cli.common.arguments.K2JVMCompilerArguments
import org.jetbrains.kotlin.cli.jvm.K2JVMCompiler
import org.jetbrains.kotlin.config.Services
import java.io.File

open class JVMCompiler: CommonCompiler(VertxAddresses.JVMCompiler) {

    val compiler = K2JVMCompiler() // TODO: make it in CommonCompiler, can't figure suitable type

    override fun start() {
        log.debug("Started JVMCompiler")
        super.start()
    }

    override fun tryToCompile(project: ProjectMessage): KotlincInvokeStatus {
        val args = prepareArgs(project, "tmp/build/")
        return executeCompiler(project) {
            val services = Services.EMPTY
            compiler.exec(MsgCollector, services, args)
        }
    }

    // TODO: add some additional arguments maybe
    private fun prepareArgs(project: ProjectMessage, destination: String): K2JVMCompilerArguments {
        val destFile = File(destination)
        if (destFile.isFile) destFile.delete()
        else if (destFile.isDirectory) FileUtils.cleanDirectory(destFile)
        else destFile.mkdir()
        val projectArgs = K2JVMCompilerArguments()
//            project.getProjectSettingsAsCompilerArgs("JVM") as K2JVMCompilerArguments
        val compilerArgs = "${getAllPathsInLine(project)} -d $destination".split(" ")
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
//        if (project.configuration.jvmDefault.isNotEmpty())
//            projectArgs.jvmDefault = project.configuration.jvmDefault.substringAfter(Directives.jvmDefault)
        //TODO!!
        //if (project.configuration.samConversion.isNotEmpty()) {
        //val samConvType = project.configuration.samConversion.substringAfterLast(": ")
        //projectArgs.samConversions = samConvType.toLowerCase()
        //}
        return projectArgs
    }

}