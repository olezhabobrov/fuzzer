package com.stepanov.bbf.bugfinder.server.messages

import com.stepanov.bbf.messages.CompilationResult
import com.stepanov.bbf.messages.ProjectMessage
import org.jetbrains.kotlin.backend.common.push

class CompilationResultsProcessor {
    private val projectToRequestCounter = mutableMapOf<ProjectMessage, Int>()
    private val projectMessageToCompilationResult = mutableMapOf<ProjectMessage, MutableList<CompilationResult>>()

    fun increaseCounter(projectMessage: ProjectMessage) {
        projectToRequestCounter[projectMessage] = projectToRequestCounter.getOrDefault(projectMessage, 0) + 1
    }

    fun processCompilationResult(compilationResult: CompilationResult) {
        val results = projectMessageToCompilationResult
            .getOrDefault(compilationResult.project, mutableListOf())
        results.push(compilationResult)
        if (results.size >= (projectToRequestCounter[compilationResult.project] ?: Int.MAX_VALUE)) {

        }
    }

    fun receivedAllResults(projectMessage: ProjectMessage): Boolean =
        ((projectMessageToCompilationResult[projectMessage]?.size ?: 0) >=
                (projectToRequestCounter[projectMessage] ?: Int.MAX_VALUE))

    fun shouldSendToBugManager(projectMessage: ProjectMessage): Boolean =
        receivedAllResults(projectMessage) &&
                projectMessageToCompilationResult[projectMessage]!!.any { result ->
                    result.invokeStatus.hasCompilerCrash()
                }

    fun getCompilationResults(projectMessage: ProjectMessage) =
        projectMessageToCompilationResult[projectMessage]!!

    fun removeProject(projectMessage: ProjectMessage) {
        projectMessageToCompilationResult.remove(projectMessage)
        projectToRequestCounter.remove(projectMessage)
    }
}