package com.stepanov.bbf.bugfinder.statistics

import com.stepanov.bbf.messages.CompilationResult
import com.stepanov.bbf.messages.KotlincInvokeStatus
import kotlinx.serialization.Serializable

@Serializable
internal data class TransformationFullStat(
    val transformation: String,
    var successfulCompilations: ExtendedStat,
    var failedCompilations: ExtendedStat,
    var reasonForBug: ExtendedStat,
    var compilationTimeouts: ExtendedStat,
    var totalMutationCount: Int,
    var successfulMutations: Int,
    var uselessMutations: Int, // no new projects produced
    var unSuccessfulMutations: Int, // failed with exception
    var newProjectsProducedDelta: Double,
    var avgTimeInMS: Long,
    var mutationTimeouts: Int,
) {
    fun add(result: CompilationResult) {
        val mutationStat = result.mutationStat
        require(transformation == mutationStat.transformation)
        successfulCompilations.add(result) { it.isCompileSuccess }
        failedCompilations.add(result) { !it.isCompileSuccess }
        reasonForBug.add(result) { it.hasCompilerCrash() }
        compilationTimeouts.add(result) { it.hasTimeout }

        avgTimeInMS = (avgTimeInMS * totalMutationCount +
                mutationStat.avgTimeInMS * mutationStat.totalMutationCount) /
                (totalMutationCount + mutationStat.totalMutationCount)
        newProjectsProducedDelta = (newProjectsProducedDelta * totalMutationCount +
                mutationStat.newProjectsProducedDelta * mutationStat.totalMutationCount) /
                (totalMutationCount + mutationStat.totalMutationCount)
        successfulMutations += mutationStat.successfulMutations
        uselessMutations += mutationStat.uselessMutations
        unSuccessfulMutations += mutationStat.unsuccessfulMutations
        mutationTimeouts += mutationStat.timeouts
        totalMutationCount += mutationStat.totalMutationCount
    }

    @Serializable
    internal data class ExtendedStat(
        var total: Int,
        val configToAmount: MutableMap<String, Int>
    ) {
        fun add(result: CompilationResult, predicate: (KotlincInvokeStatus) -> Boolean) {
            val statuses = getAllWithCondition(result, predicate)
            total += statuses.size
            statuses.forEach { status ->
                addInfo(status.configuration.toString())
            }
        }

        private fun addInfo(config: String, amount: Int = 1) {
            configToAmount[config] = configToAmount.getOrDefault(config, 0) + amount
        }

        private fun getAllWithCondition(compilationResult: CompilationResult,
                                        predicate: (KotlincInvokeStatus) -> Boolean) = compilationResult.results
            .map { it.results }.flatten().filter(predicate)
    }

    companion object {
        fun initialStat(transformation: String): TransformationFullStat = TransformationFullStat(
            transformation,
            ExtendedStat(0, mutableMapOf()),
            ExtendedStat(0, mutableMapOf()),
            ExtendedStat(0, mutableMapOf()),
            ExtendedStat(0, mutableMapOf()),
            0,
            0,
            0,
            0,
            0.0,
            0L,
            0
        )
    }
}
