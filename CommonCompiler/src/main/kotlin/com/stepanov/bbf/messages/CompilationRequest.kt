package com.stepanov.bbf.messages

import com.stepanov.bbf.information.MutationStat
import kotlinx.serialization.Serializable

@Serializable
data class CompilationRequest(
    val projects: List<ProjectMessage>,
    val mutationStat: MutationStat,
    val shouldBeBinaryCompatible: Boolean,
)
