package com.stepanov.bbf.bugfinder.mutator.vertxMessages

import com.stepanov.bbf.bugfinder.mutator.transformations.FTarget
import com.stepanov.bbf.bugfinder.mutator.transformations.Transformation

data class MutationRequest(
    val transformation: Transformation,
    val targets: List<FTarget>,
    val strategyNumber: Int,
)