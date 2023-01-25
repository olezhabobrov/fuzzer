package com.stepanov.bbf.bugfinder.mutator.vertxMessages

import com.stepanov.bbf.bugfinder.executor.project.Project

data class MutationResult(
    val project: Project,
    val strategyNumber: Int
    // TODO: add some information about completed transformations
)