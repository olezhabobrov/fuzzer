package com.stepanov.bbf.messages

import com.stepanov.bbf.information.CompilationConfiguration
import kotlinx.serialization.Serializable

@Serializable
class KotlincInvokeStatus(
    val combinedOutput: String,
    val isCompileSuccess: Boolean,
    val hasException: Boolean,
    val hasTimeout: Boolean,
    val configuration: CompilationConfiguration
) {
    fun hasCompilerCrash(): Boolean = hasTimeout || hasException

    fun hasCompilationError(): Boolean = !isCompileSuccess

    companion object {
//        val statusWithoutErrors = KotlincInvokeStatus("", true, false, false, listOf())
    }

}

@Serializable
class KotlincInvokeResult(
    val projectMessage: ProjectMessage,
    val results: List<KotlincInvokeStatus>
) {
    val isCompileSuccess = results.any { it.isCompileSuccess }
    val hasCompilerCrash = results.any { it.hasCompilerCrash() }
}

@Serializable
class CompilationResult(
    val compiler: String,
    val results: List<KotlincInvokeResult>,
    val strategyNumber: Int,
    val transformation: String,
)