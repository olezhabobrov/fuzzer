package com.stepanov.bbf

import com.stepanov.bbf.information.CompilationArgs
import com.stepanov.bbf.messages.ProjectMessage

object CompilationArgsGenerator {

    fun getAllCombinations(project: ProjectMessage): List<CompilationArgs> {
        val klibFiles = project.files.filter { it.isKlib }
        val files = project.files.filter { !it.isKlib }
//
//        val args = CompilationArgs()
//        val klibArgs = CompilationArgs()
//        klibArgs.addFiles(klibFiles.map {it.name})
//        klibArgs.makeKlib()
//        klibArgs.addPartialLinkage()
//        args.addFiles(files.map {it.name})
//        args.addPartialLinkage()
//        args.addKlib(klibArgs)
//        return listOf(args)
        val argsList =
            generateArgsCombinations(files.map { it.name })
        if (klibFiles.isNotEmpty()) {
            val argsListKlib =
                generateArgsCombinations(klibFiles.map { it.name })
            return addKlib(argsList, argsListKlib)
        } else {
            return argsList
        }
    }

    private fun generateArgsCombinations(files: List<String>): List<CompilationArgs> {
        val results = mutableListOf<CompilationArgs>()
        val args = CompilationArgs()
        args.addFiles(files)
        results.add(args)
        results.addAll(partialLinkage(results))
        results.addAll(klib(results))
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