package com.stepanov.bbf.bugfinder.mutator

import com.stepanov.bbf.bugfinder.mutator.transformations.Transformation

data class MutationStrategy(
    val transformations: List<Transformation>
) {

}