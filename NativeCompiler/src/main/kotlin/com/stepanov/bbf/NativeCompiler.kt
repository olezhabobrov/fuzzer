package com.stepanov.bbf

import com.stepanov.bbf.information.VertxAddresses
import com.stepanov.bbf.messages.KotlincInvokeResult
import com.stepanov.bbf.messages.KotlincInvokeStatus
import com.stepanov.bbf.messages.ProjectMessage
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

    private fun executeCompilationCheckForFile(project: ProjectMessage): KotlincInvokeResult {
        val results = mutableListOf<KotlincInvokeStatus>()
        val name = project.files.first().first
        results.add(
            createKlib(project, name)
        )
        results.add(
            createKlibWithPartialLinkage(project, name)
        )
        return KotlincInvokeResult(project, results)
    }

    private fun executeCompiltationCheckForProject(project: ProjectMessage): KotlincInvokeResult {
        log.debug("Received project with ${project.files.size} files")
        val results = mutableListOf<KotlincInvokeStatus>()
        val files = project.files
        files.forEachIndexed { index, (name, text) ->
            val libraryFiles = files.filterIndexed { indexInternal, _ -> indexInternal != index }
            val result = createKlib(project, libraryFiles.map {it.first} )
            if (result.hasCompilerCrash()) {
                results.add(result)
                return@forEachIndexed
            }
            if (!result.isCompileSuccess)
                return@forEachIndexed
            val

        }

        val result = createKlib(project, project.files.map { it.first })
            if (result.hasCompilerCrash()) {
                return result
            }
            if (!result.isCompileSuccess) {
                log.debug("Split- : bad division; doesn't compile")
                return result
            }

            val (klibs, dependedFiles) = project.files.partition { (name, _) ->
                val result = createKlib(project, name)
                if (result.hasCompilerCrash()) {
                    return result
                }
                result.isCompileSuccess
            }
            if (klibs.size == 2) {
                log.debug("Split- : not interesting case: both files compiled independently")
                return KotlincInvokeStatus.statusWithoutErrors
            }
            if (dependedFiles.size == 2) {
                log.debug("Split- : not interesting case: both files depend on each other")
                return KotlincInvokeStatus.statusWithoutErrors
            }
            log.debug("Split+ : found interesting division ")

            return createKlib(project, dependedFiles.first().first, klibs.first().first.getKlibName(project))

    }

    override fun executeCompilationCheck(project: ProjectMessage): KotlincInvokeResult {
        if (project.files.size == 1) {
            return executeCompilationCheckForFile(project)
        }
        else {
            return executeCompiltationCheckForProject(project)
        }
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
    }

    private fun createKlib(project: ProjectMessage, name: String): KotlincInvokeStatus {
        val output = name.getSimpleFileNameWithoutExt() + ".klib"
        val args = CompilationArgsBuilder()
            .add(CompilationConfiguration.ProduceLibrary)
            .addOutput(project.dir, output)
            .addFile(project.dir, name.getSimpleNameFile())
            .build()
        return compile(project, createArguments(args), CompilationConfiguration.ProduceLibrary, output)
    }

    private fun createKlib(project: ProjectMessage, names: List<String>): KotlincInvokeStatus {
        val output = names.first().getSimpleFileNameWithoutExt() + ".klib"
        val args = CompilationArgsBuilder()
            .add(CompilationConfiguration.ProduceLibrary)
            .addOutput(project.dir, output)
            .addFiles(project.dir, names)
            .build()
        return compile(project, createArguments(args), CompilationConfiguration.ProduceLibrary, output)
    }

    private fun createKlibWithPartialLinkage(project: ProjectMessage, name: String): KotlincInvokeStatus {
        val output = name.getSimpleFileNameWithoutExt() + ".klib"
        val args = CompilationArgsBuilder()
            .add(CompilationConfiguration.ProduceLibrary)
            .add(CompilationConfiguration.PartialLinkage)
            .addOutput(project.dir, output)
            .addFile(project.dir, name.getSimpleNameFile())
            .build()
        return compile(project, createArguments(args), CompilationConfiguration.PartialLinkage, output)
    }

    private fun compileWithLibrary(project: ProjectMessage,
                                   files: List<String>,
                                   library: String): KotlincInvokeStatus {
        val output = files.first().getSimpleFileNameWithoutExt() + ".kexe"
        val args = CompilationArgsBuilder()
            .addFiles(project.dir, files)
            .addLibrary(project.dir, library)
            .addOutput(project.dir, output)
            .build()
        return compile(project, createArguments(args), CompilationConfiguration.Split, output)
    }

//    private fun createKlib(project: ProjectMessage, names: List<String>): KotlincInvokeStatus {
//        val args = CompilationArgsBuilder()
//            .add(CompilationConfiguration.ProduceLibrary)
//            .addOutput(project.dir, "library.klib")
//            .addFiles(project.dir, names)
//            .build()
//        return compile(project, createArguments(args))
//    }
//
//    private fun createKlib(project: ProjectMessage, name: String, libraryName: String): KotlincInvokeStatus {
//        val args = CompilationArgsBuilder()
//            .add(CompilationConfiguration.ProduceLibrary)
//            .addLibrary(project.dir, libraryName)
//            .addOutput(project.dir, name.getSimpleFileNameWithoutExt() + ".klib")
//            .addFile(project.dir, name.getSimpleNameFile())
//            .build()
//        return compile(project, createArguments(args))
//    }

    private fun compile(project: ProjectMessage,
                        args: K2NativeCompilerArguments,
                        configuration: CompilationConfiguration,
                        output: String,
                        additionalInfo: String = ""): KotlincInvokeStatus {
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
            configuration,
            output,
            additionalInfo
        )
        return status
    }

    private val kotlinHome = System.getenv("kotlin-home")
        ?: error("kotlin-home not specified in environment variables (should be in build.gradle)")

    private fun createArguments(args: List<String>): K2NativeCompilerArguments {
        val compilerArgumentsList = mutableListOf(
            "-kotlin-home", kotlinHome
//            "-o", project.outputDir + "result"
        )
        compilerArgumentsList.addAll(args)
        val arguments = K2NativeCompilerArguments()
        K2Native().parseArguments(compilerArgumentsList.toTypedArray() , arguments)
        return arguments
    }


}