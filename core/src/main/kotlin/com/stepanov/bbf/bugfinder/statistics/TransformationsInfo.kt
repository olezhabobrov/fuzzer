package com.stepanov.bbf.bugfinder.statistics

import kotlinx.serialization.Serializable

@Serializable
internal data class TransformationsInfo(
    val stats: MutableList<TransformationStat>
)

@Serializable
internal data class TransformationStat(
    val transformation: String,
    var successfulCompilations: Int,
    var failedCompilations: Int,
    var reasonForBug: Int,
    var timeouts: Int,
) {
    fun add(stat: TransformationStat) {
        require(transformation == stat.transformation)
        successfulCompilations += stat.successfulCompilations
        failedCompilations += stat.failedCompilations
        reasonForBug += stat.reasonForBug
        timeouts += stat.timeouts
    }
}