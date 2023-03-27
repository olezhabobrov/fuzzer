package com.stepanov.bbf.messages

import kotlinx.serialization.Serializable

@Serializable
data class CompilationRequest(
    val projectMessage: List<ProjectMessage>,
    val mutationNumber: Int
)