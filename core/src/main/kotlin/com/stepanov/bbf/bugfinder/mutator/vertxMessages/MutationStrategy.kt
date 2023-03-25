package com.stepanov.bbf.bugfinder.mutator.vertxMessages

import com.stepanov.bbf.bugfinder.project.Project
import com.stepanov.bbf.bugfinder.server.messages.MutationProblem
import com.stepanov.bbf.bugfinder.server.messages.TransformationClass
import java.util.concurrent.atomic.AtomicInteger

data class MutationStrategy(
    val transformations: List<TransformationClass>,
    val project: Project,
    val mutationProblem: MutationProblem
) {
    val number: Int = counter.incrementAndGet()

    companion object {
        val counter: AtomicInteger = AtomicInteger(0)
    }

    override fun toString() = """MutationStrategy {
            number=${number}
            transformations:
            ${transformations.take(5).map { it.clazz.simpleName }.joinToString("\n")}
            .......
        }
    """.trimIndent()
}