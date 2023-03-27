package com.stepanov.bbf.bugfinder.mutator.vertxMessages

import com.stepanov.bbf.bugfinder.project.Project
import com.stepanov.bbf.messages.ProjectMessage
import org.jetbrains.kotlin.backend.common.push
import java.util.concurrent.atomic.AtomicInteger

data class MutationResult(
    val projects: Set<ProjectMessage>,
    val strategyNumber: Int
) {

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