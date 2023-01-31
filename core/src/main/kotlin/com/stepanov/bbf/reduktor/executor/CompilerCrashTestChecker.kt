package com.stepanov.bbf.reduktor.executor

import com.stepanov.bbf.bugfinder.executor.CommonCompiler
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.manager.BugType
import com.stepanov.bbf.bugfinder.vertx.serverMessages.ProjectMessage
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSourceLocation

class KotlincInvokeStatus(
    val combinedOutput: String,
    val isCompileSuccess: Boolean,
    val hasException: Boolean,
    val hasTimeout: Boolean,
    val compilerExecTimeInMlls: Long,
    val locations: List<CompilerMessageSourceLocation> = listOf()
) {
    fun hasCompilerCrash(): Boolean = hasTimeout || hasException

    fun hasCompilationError(): Boolean = !isCompileSuccess

    fun bugType(): BugType =
        if (combinedOutput.contains("Exception while analyzing expression"))
            BugType.FRONTEND
        else
            BugType.BACKEND
}

class CompilationResult(
    val compiler: String,
    val invokeStatus: KotlincInvokeStatus,
    val project: ProjectMessage
)