package com.stepanov.bbf.bugfinder.statistics

import com.stepanov.bbf.messages.CompilationResult
import com.stepanov.bbf.messages.KotlincInvokeStatus
import kotlinx.serialization.Serializable

@Serializable
internal data class TransformationStat(
    val transformation: String,
    var successfulCompilations: ExtendedStat,
    var failedCompilations: ExtendedStat,
    var reasonForBug: ExtendedStat,
    var timeouts: ExtendedStat,
) {
    fun add(result: CompilationResult) {
        require(transformation == result.transformation)
        successfulCompilations.add(result) { it.isCompileSuccess }
        failedCompilations.add(result) { !it.isCompileSuccess }
        reasonForBug.add(result) { it.hasCompilerCrash() }
        timeouts.add(result) { it.hasTimeout }
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
        fun initialStat(transformation: String): TransformationStat = TransformationStat(
            transformation,
            ExtendedStat(0, mutableMapOf()),
            ExtendedStat(0, mutableMapOf()),
            ExtendedStat(0, mutableMapOf()),
            ExtendedStat(0, mutableMapOf()),
        )
    }
}

