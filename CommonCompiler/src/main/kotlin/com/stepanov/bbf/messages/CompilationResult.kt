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

    companion object {
//        val statusWithoutErrors = KotlincInvokeStatus("", true, false, false, listOf())
    }

    fun getFailReason(): FailReason {
        if (arguments.klib == null) {
            return FailReason.KlibFail
        }
        if (arguments.withOldKlib() && hasCompilationError()) {
            return FailReason.InvocatorFail
        }
        return FailReason.LinkingFail // TODO: it can be caused by: bad invocation of callable (Invocator problem), it
        // can be caused by linking problem
    }

    enum class FailReason {
        KlibFail,
        LinkingFail,
        InvocatorFail
    }

}

@Serializable
class KotlincInvokeResult(
    val projectMessage: ProjectMessage,
    val results: List<KotlincInvokeStatus>
) {
    val isCompileSuccess = results.all { it.isCompileSuccess }
    val hasCompilerCrash = results.any { it.hasCompilerCrash() }

    fun isKlibValid() = results.all { it.getFailReason() != KotlincInvokeStatus.FailReason.KlibFail }

    fun hasInvocatorError() = results.any { it.getFailReason() == KotlincInvokeStatus.FailReason.InvocatorFail }

    fun hasLinkingError() = results.any { it.getFailReason() == KotlincInvokeStatus.FailReason.LinkingFail }

    fun getDescription(): CompilationDescription {
        if (hasCompilerCrash)
            return CompilationDescription.COMPILER_CRASHED
        if (hasInvocatorError())
            return CompilationDescription.INVOCATOR_FAIL
        if (!isKlibValid())
            return CompilationDescription.KLIB_INVALID
        require(projectMessage.isBinaryCompatible != null)
        if (projectMessage.isBinaryCompatible!!) {
            if (!isCompileSuccess) {
                if (hasLinkingError()) {
                    return CompilationDescription.COMPATIBLE_NOT_LINKING
                }
                return CompilationDescription.UNKOWN_BEHAVIOUR
            }
            return CompilationDescription.EXPECTED_BEHAVIOUR
        }
        if (isCompileSuccess) {
            return CompilationDescription.INCOMPATIBLE_LINKING
        }
        if (hasLinkingError()) {
            return CompilationDescription.EXPECTED_BEHAVIOUR
        }
        return CompilationDescription.UNKOWN_BEHAVIOUR
    }

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