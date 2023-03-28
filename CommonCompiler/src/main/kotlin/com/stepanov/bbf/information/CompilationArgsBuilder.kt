package com.stepanov.bbf.information

import com.stepanov.bbf.util.getSimpleNameFile

class CompilationArgsBuilder {
    private val args = mutableListOf<String>()

    fun add(s: String) = apply { args.add(s) }

    fun add(s: List<String>) = apply { args.addAll(s) }

    fun add(conf: CompilationConfiguration) = apply {
        when (conf) {
            CompilationConfiguration.ProduceLibrary -> add(listOf("-p", "library"))
            CompilationConfiguration.PartialLinkage -> add("-Xpartial-linkage")
            else -> {}
        }
    }

    fun addFile(root: String, file: String) = add("$root$file")

    fun addFiles(root: String, files: List<String>) = apply {
        files.forEach { addFile(root, it.getSimpleNameFile()) }
    }

    fun addOutput(root: String, s: String) = add(listOf("-o", "$root$s"))

    fun addLibrary(root: String, lib: String) = add(listOf("-l", "$root$lib"))

    fun build() = args
}