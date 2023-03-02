package com.stepanov.bbf.bugfinder.mutator.vertxMessages

import com.stepanov.bbf.bugfinder.project.Project

data class MutationResult(
    val project: Project,
    val strategyNumber: Int,
    val usefulTransformations: List<String>,
    val isFinal: Boolean = false
) {
    fun logInfo(): String {
        val transformations = usefulTransformations.size
        return usefulTransformations.takeLast(10).joinToString(
            separator="\n",
            prefix="Mutated by strategy#$strategyNumber.\n" +
                    "isFinal=$isFinal\n" +
                    "Transformed $transformations times by:\n" +
                    "...\n"
        )
    }
}