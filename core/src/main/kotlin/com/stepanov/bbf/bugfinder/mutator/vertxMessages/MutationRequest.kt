package com.stepanov.bbf.bugfinder.mutator.vertxMessages

import com.stepanov.bbf.bugfinder.mutator.transformations.Transformation

data class MutationRequest(
    val transformations: List<Transformation>,
    val strategyNumber: Int,
    val transformationNumber: Int,
)