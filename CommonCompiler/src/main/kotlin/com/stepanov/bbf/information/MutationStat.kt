package com.stepanov.bbf.information

import kotlinx.serialization.Serializable

@Serializable
data class MutationStat(
    val transformation: String,
    val totalMutationCount: Int,
    val successfulMutations: Int,
    val uselessMutations: Int,
    val unsuccessfulMutations: Int,
    val newProjectsProducedDelta: Double,
    val avgTimeInMS: Long,
    val timeouts: Int,
) {
    companion object {
        val emptyStat = MutationStat("", 0, 0,
            0, 0,0.0,0,0)
    }
}