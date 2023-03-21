package com.stepanov.bbf.messages

import com.stepanov.bbf.information.CompilationConfiguration
import kotlinx.serialization.Serializable

@Serializable
data class CompilationRequest(
    val projectMessage: ProjectMessage,
    val configuration: CompilationConfiguration,
    val logInfo: String,
    val mutationNumber: Int
)