package com.stepanov.bbf.messages

import kotlinx.serialization.Serializable

@Serializable
data class CompilationRequest(
    val projects: List<ProjectMessage>,
    val strategyNumber: Int
)