package com.stepanov.bbf

import com.stepanov.bbf.information.CompilationArgs
import com.stepanov.bbf.messages.ProjectMessage

object CompilationArgsGenerator {

    fun generateForKlibFuzzing(project: ProjectMessage): List<CompilationArgs> {
        val oldKlib = project.oldKlib!!.name
        val newKlib = project.newKlib!!.name
        val mainFile = project.mainFile.name
        val libName = "lib.klib"
        val mainName = "main.klib"
        val args1 = CompilationArgs(1).addFile(oldKlib).makeKlib().createOutputName(libName)
        val args2 = CompilationArgs(2).addFile(mainFile).addKlib(libName).makeKlib().createOutputName(mainName)
        val args3 = CompilationArgs(3).addXinclude(mainName).addKlib(libName).addPartialLinkage()
        val args4 = CompilationArgs(4).addFile(newKlib).makeKlib().createOutputName(libName)
        val args5 = CompilationArgs(5).addXinclude(mainName).addKlib(libName).addPartialLinkage().createOutputName("program")

        return listOf(args1, args2, args3, args4, args5)
    }

    fun getAllCombinations(project: ProjectMessage): List<CompilationArgs> {
        val klibFiles = project.files.filter { it.isKlib }
        val files = project.files.filter { !it.isKlib }
        val argsList =
            generateArgsCombinations(files.map { it.name }, !project.hasMain())
        if (klibFiles.isNotEmpty()) {
            val argsListKlib =
                generateArgsCombinations(klibFiles.map { it.name }, true)
            return addKlib(argsList, argsListKlib)
        } else {
            return argsList
        }
    }

    private fun generateArgsCombinations(files: List<String>, createKlib: Boolean): List<CompilationArgs> {
        val results = mutableListOf<CompilationArgs>()
        val args = CompilationArgs(0)
        args.addFiles(files)
        if (createKlib) {
            args.makeKlib()
        }
        results.add(args)
        results.addAll(partialLinkage(results))
        results.addAll(k2(results))
        return results
    }

    private fun addKlib(targets: List<CompilationArgs>,
                klibs: List<CompilationArgs>): List<CompilationArgs> {
        return targets.flatMap { args ->
            klibs.map { klib ->
                TODO()
            }
        }
    }

    private fun partialLinkage(argsList: List<CompilationArgs>): List<CompilationArgs> {
        return argsList.map { it.copy().addPartialLinkage() }
    }

    private fun k2(argsList: List<CompilationArgs>): List<CompilationArgs> {
        return argsList.map { it.copy().useK2() }
    }
    private fun klib(argsList: List<CompilationArgs>): List<CompilationArgs> {
        return argsList.map { it.copy().makeKlib() }
    }
}