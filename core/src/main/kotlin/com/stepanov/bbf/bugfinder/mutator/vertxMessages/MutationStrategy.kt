package com.stepanov.bbf.bugfinder.mutator.vertxMessages

import com.stepanov.bbf.bugfinder.project.Project
import com.stepanov.bbf.bugfinder.mutator.transformations.Transformation
import java.util.concurrent.atomic.AtomicInteger

data class MutationStrategy(
    val transformations: List<Transformation>,
    val project: Project
) {
    val number: Int = counter.incrementAndGet()

    companion object {
        val counter: AtomicInteger = AtomicInteger(0)
    }

    override fun toString() = """MutationStrategy {
            number=${number}
            transformations:
            ${transformations.take(5).joinToString("\n")}
            .......
        }
    """.trimIndent()
}