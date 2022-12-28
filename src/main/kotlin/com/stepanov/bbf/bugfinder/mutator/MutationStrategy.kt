package com.stepanov.bbf.bugfinder.mutator

import com.stepanov.bbf.bugfinder.mutator.transformations.Transformation

data class MutationStrategy(
    val transformations: List<Transformation>,
    val number: Int = ++counter
) {
    companion object {
        var counter = 0
    }
}