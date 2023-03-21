package com.stepanov.bbf.bugfinder.server.messages

import com.stepanov.bbf.messages.CompilationResult
import org.jetbrains.kotlin.backend.common.push

class CompilationResultsProcessor {
    private val mutationToCounter = mutableMapOf<Int, Int>()
    private val mutationToResult = mutableMapOf<Int, MutableList<CompilationResult>>()

    fun increaseCounter(mutationNumber: Int) {
        mutationToCounter[mutationNumber] =
            mutationToCounter.getOrDefault(mutationNumber, 0) + 1
    }

    fun processCompilationResult(compilationResult: CompilationResult) {
        val results = mutationToResult
            .getOrDefault(compilationResult.request.mutationNumber, mutableListOf())
        results.push(compilationResult)
        mutationToResult[compilationResult.request.mutationNumber] = results
    }

    fun receivedAllResults(mutation: Int): Boolean =
        ((mutationToResult[mutation]?.size ?: 0) >=
                (mutationToCounter[mutation] ?: Int.MAX_VALUE))

    fun shouldSendToBugManager(mutationNumber: Int): Boolean =
        receivedAllResults(mutationNumber) &&
                mutationToResult[mutationNumber]!!.any { result ->
                    result.invokeStatus.hasCompilerCrash()
                }

    fun getCompilationResults(mutationNumber: Int) =
        mutationToResult[mutationNumber]!!

    fun removeProject(mutation: Int) {
        mutationToResult.remove(mutation)
        mutationToCounter.remove(mutation)
    }
}