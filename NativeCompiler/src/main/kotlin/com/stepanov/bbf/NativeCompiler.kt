package com.stepanov.bbf

import com.stepanov.bbf.information.*
import com.stepanov.bbf.messages.CompilationRequest
import com.stepanov.bbf.messages.KotlincInvokeStatus
import com.stepanov.bbf.messages.ProjectMessage
import com.stepanov.bbf.util.getKlibName
import com.stepanov.bbf.util.getSimpleFileNameWithoutExt
import com.stepanov.bbf.util.getSimpleNameFile
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
//        when (request.configuration) {
//            CompilationConfiguration.Split -> {
//                if (project.files.size != 2) {
//                    error("In Split configuration should be 2 files to compile, but received ${project.files.size}")
//                }
//
//                val result = createKlib(project, project.files.map { it.first })
//                if (result.hasCompilerCrash()) {
//                    return result
//                }
//                if (!result.isCompileSuccess) {
//                    log.debug("Split- : bad division; doesn't compile")
//                    return result
//                }
//
//                val (klibs, dependedFiles) = project.files.partition { (name, _) ->
//                    val result = createKlib(project, name)
//                    if (result.hasCompilerCrash()) {
//                        return result
//                    }
//                    result.isCompileSuccess
//                }
//                if (klibs.size == 2) {
//                    log.debug("Split- : not interesting case: both files compiled independently")
//                    return KotlincInvokeStatus.statusWithoutErrors
//                }
//                if (dependedFiles.size == 2) {
//                    log.debug("Split- : not interesting case: both files depend on each other")
//                    return KotlincInvokeStatus.statusWithoutErrors
//                }
//                log.debug("Split+ : found interesting division ")
//
//                return createKlib(project, dependedFiles.first().first, klibs.first().first.getKlibName(project))
//            }
//            else -> {}
//        }
        TODO()
    }

    private fun createKlib(project: ProjectMessage, name: String): KotlincInvokeStatus {
        val args = CompilationArgsBuilder()
            .add(CompilationConfiguration.ProduceLibrary)
            .addOutput(project.dir, name.getSimpleFileNameWithoutExt() + ".klib")
            .addFile(project.dir, name.getSimpleNameFile())
            .build()
        return compile(project, createArguments(args))
    }

    private fun createKlib(project: ProjectMessage, names: List<String>): KotlincInvokeStatus {
        val args = CompilationArgsBuilder()
            .add(CompilationConfiguration.ProduceLibrary)
            .addOutput(project.dir, "library.klib")
            .addFiles(project.dir, names)
            .build()
        return compile(project, createArguments(args))
    }

    private fun createKlib(project: ProjectMessage, name: String, libraryName: String): KotlincInvokeStatus {
        val args = CompilationArgsBuilder()
            .add(CompilationConfiguration.ProduceLibrary)
            .addLibrary(project.dir, libraryName)
            .addOutput(project.dir, name.getSimpleFileNameWithoutExt() + ".klib")
            .addFile(project.dir, name.getSimpleNameFile())
            .build()
        return compile(project, createArguments(args))
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
            hasTimeout,
            project
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