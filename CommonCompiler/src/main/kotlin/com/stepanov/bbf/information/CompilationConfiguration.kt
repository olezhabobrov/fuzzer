package com.stepanov.bbf.information

import kotlinx.serialization.Serializable

@Serializable
sealed class CompilationConfiguration {
    abstract fun getNextConfiguration(): List<String>
}
//    ProduceLibrary,
//    PartialLinkage,
//    Split
@Serializable
sealed class NativeConfiguration: CompilationConfiguration()

@Serializable
sealed class JVMConfiguration: CompilationConfiguration()

@Serializable
open class ProduceLibrary: NativeConfiguration() {
    override fun getNextConfiguration(): List<String> = listOf(
        "-p", "library"
    )
}

@Serializable
class PartialLinkage: ProduceLibrary() {
    override fun getNextConfiguration(): List<String> {
        val result = mutableListOf(
            "-Xpartial-linkage"
        )
        result.addAll(super.getNextConfiguration())
        return result
    }
}

@Serializable
class Split: NativeConfiguration() {
    override fun getNextConfiguration(): List<String> {
        TODO("Not yet implemented")
    }
}
