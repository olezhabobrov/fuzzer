package com.stepanov.bbf.information

import kotlinx.serialization.Serializable

@Serializable
data class MutationStat(
    val transformation: String,
    val successfulMutations: Int,
    val unsuccessfulMutations: Int,
    val newProjectsProduced: Int,
    val avgTimeInMS: Long,
    val timeouts: Int,
)