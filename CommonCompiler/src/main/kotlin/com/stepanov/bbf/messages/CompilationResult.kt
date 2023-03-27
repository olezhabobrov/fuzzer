package com.stepanov.bbf.messages

import kotlinx.serialization.Serializable

@Serializable
class KotlincInvokeStatus(
    val combinedOutput: String,
    val isCompileSuccess: Boolean,
    val hasException: Boolean,
    val hasTimeout: Boolean,
    val projectMessage: ProjectMessage
) {
    fun hasCompilerCrash(): Boolean = hasTimeout || hasException

    fun hasCompilationError(): Boolean = !isCompileSuccess

    companion object {
//        val statusWithoutErrors = KotlincInvokeStatus("", true, false, false, listOf())
    }

}

@Serializable
class CompilationResult(
    val compiler: String,
    val results: List<KotlincInvokeStatus>,
    val strategyNumber: Int
) {
    fun hasCompilerCrash(): Boolean = results.any { it.hasCompilerCrash() }
}