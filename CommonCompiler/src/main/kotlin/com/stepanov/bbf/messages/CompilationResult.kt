package com.stepanov.bbf.messages

import com.stepanov.bbf.information.CompilationArgs
import com.stepanov.bbf.information.MutationStat
import kotlinx.serialization.Serializable

@Serializable
class   KotlincInvokeStatus(
    val combinedOutput: String,
    val isCompileSuccess: Boolean,
    val hasException: Boolean,
    val hasTimeout: Boolean,
    val arguments: CompilationArgs,
) {
    fun hasCompilerCrash(): Boolean = hasTimeout || hasException

    fun hasCompilationError(): Boolean = !isCompileSuccess

    fun failed() = !isCompileSuccess || hasException
}

@Serializable
class KotlincInvokeResult(
    val projectMessage: ProjectMessage,
    val results: List<KotlincInvokeStatus>
) {
    val isCompileSuccess = results.all { it.isCompileSuccess }
    val hasCompilerCrash = results.any { it.hasCompilerCrash() }

    fun getDescription(): CompilationDescription {

        if (hasCompilerCrash)
            return CompilationDescription.COMPILER_CRASHED

        if (getById(1).failed())
            return CompilationDescription.UNKOWN_BEHAVIOUR
        if (getById(2).failed())
            return CompilationDescription.INVOCATOR_FAIL
        if (getById(3).failed())
            return CompilationDescription.UNKOWN_BEHAVIOUR
        if (getById(4).failed())
            return CompilationDescription.KLIB_INVALID
        if (getById(5).failed()) {
            return if (projectMessage.isBinaryCompatible!!) {
                CompilationDescription.COMPATIBLE_NOT_LINKING
            } else {
                CompilationDescription.EXPECTED_BEHAVIOUR
            }
        } else {
            return if (projectMessage.isBinaryCompatible!!) {
                CompilationDescription.EXPECTED_BEHAVIOUR
            } else {
                CompilationDescription.INCOMPATIBLE_LINKING
            }
        }
    }

    private fun getById(x: Int) = results.find { it.arguments.number == x }!!

}

@Serializable
class CompilationResult(
    val compiler: String,
    val results: List<KotlincInvokeResult>,
    val mutationStat: MutationStat,
)

enum class CompilationDescription {
    KLIB_INVALID,
    INVOCATOR_FAIL,
    COMPATIBLE_NOT_LINKING,
    INCOMPATIBLE_LINKING,
    COMPILER_CRASHED,
    EXPECTED_BEHAVIOUR,
    UNKOWN_BEHAVIOUR,
}