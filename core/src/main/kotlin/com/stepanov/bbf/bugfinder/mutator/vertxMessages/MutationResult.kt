package com.stepanov.bbf.bugfinder.mutator.vertxMessages

import com.stepanov.bbf.bugfinder.project.Project
import com.stepanov.bbf.messages.ProjectMessage
import org.jetbrains.kotlin.backend.common.push
import java.util.concurrent.atomic.AtomicInteger

data class MutationResult(
    val projects: List<ProjectMessage>,
    val strategyNumber: Int,
    val usefulTransformations: List<String>,
    val isFinal: Boolean = false
) {

    fun logInfo(): String {
        val transformations = usefulTransformations.size
        return usefulTransformations.takeLast(10).joinToString(
            separator="\n",
            prefix="strategy#$strategyNumber.\n" +
                    "isFinal=$isFinal\n" +
                    "Transformed $transformations times by:\n" +
                    "...\n"
        )
    }

    val mutationNumber = mutationCounter.incrementAndGet()

    companion object {

        val mutationCounter = AtomicInteger(0)

        fun createProjects(project: Project): List<ProjectMessage> {
            val projects = mutableListOf<ProjectMessage>()
            projects.push(project.createProjectMessage())
            projects.push(project.splitProject())
            return projects
        }
    }

}