package com.stepanov.bbf

import com.stepanov.bbf.information.CompilationArgs
import com.stepanov.bbf.messages.ProjectMessage

object CompilationArgsGenerator {

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
        val args = CompilationArgs()
        args.addFiles(files)
        if (createKlib) {
            args.makeKlib()
        }
        results.add(args)
        results.addAll(partialLinkage(results))
        return results
    }

    private fun addKlib(targets: List<CompilationArgs>,
                klibs: List<CompilationArgs>): List<CompilationArgs> {
        return targets.flatMap { args ->
            klibs.map { klib ->
                args.addKlib(klib)
            }
        }
    }

    private fun partialLinkage(argsList: List<CompilationArgs>): List<CompilationArgs> {
        return argsList.map { it.copy().addPartialLinkage() }
    }

    private fun klib(argsList: List<CompilationArgs>): List<CompilationArgs> {
        return argsList.map { it.copy().makeKlib() }
    }
}