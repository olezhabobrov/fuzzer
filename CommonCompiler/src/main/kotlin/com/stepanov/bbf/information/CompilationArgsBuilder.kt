package com.stepanov.bbf.information

class CompilationArgsBuilder {
    private val args = mutableListOf<String>()

    fun add(s: String) = apply { args.add(s) }

    fun add(s: List<String>) = apply { args.addAll(s) }

    fun add(conf: CompilationConfiguration) = apply {
        when (conf) {
            CompilationConfiguration.ProduceLibrary -> add(listOf("-p", "library"))
            else -> {}
        }
    }

    fun addOutput(root: String, s: String) = add(listOf("-o", "$root/$s"))

    fun build() = args
}