package com.stepanov.bbf.bugfinder.mutator.vertxMessages

import com.stepanov.bbf.bugfinder.mutator.transformations.Transformation
import com.stepanov.bbf.messages.ProjectMessage

data class MutationRequest(
    val transformation: Transformation,
    val targets: List<ProjectMessage>,
    val strategyNumber: Int,
)