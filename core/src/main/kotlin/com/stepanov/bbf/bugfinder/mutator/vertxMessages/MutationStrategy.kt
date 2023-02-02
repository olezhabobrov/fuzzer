package com.stepanov.bbf.bugfinder.mutator.vertxMessages

import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.mutator.transformations.Transformation

data class MutationStrategy(
    val transformations: List<Transformation>,
) {
    val project: Project = transformations.firstOrNull()?.project ?: error("Empty strategy")
    val number: Int = ++counter

    companion object {
        var counter = 0
    }
}