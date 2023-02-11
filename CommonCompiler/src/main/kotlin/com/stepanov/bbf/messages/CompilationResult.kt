package com.stepanov.bbf.messages

//import com.stepanov.bbf.bugfinder.manager.BugType
import com.stepanov.bbf.messages.ProjectMessage
import kotlinx.serialization.Serializable

@Serializable
class KotlincInvokeStatus(
    val combinedOutput: String,
    val isCompileSuccess: Boolean,
    val hasException: Boolean,
    val hasTimeout: Boolean,
) {
    fun hasCompilerCrash(): Boolean = hasTimeout || hasException

    fun hasCompilationError(): Boolean = !isCompileSuccess

}

class CompilationResult(
    val compiler: String,
    val invokeStatus: KotlincInvokeStatus,
    val project: ProjectMessage
)