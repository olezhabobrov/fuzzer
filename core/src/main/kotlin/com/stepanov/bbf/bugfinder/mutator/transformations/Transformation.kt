package com.stepanov.bbf.bugfinder.mutator.transformations

import com.stepanov.bbf.bugfinder.mutator.transformations.tce.UsagesSamplesGenerator
import com.stepanov.bbf.bugfinder.project.BBFFile
import com.stepanov.bbf.bugfinder.project.Project
import com.stepanov.bbf.messages.ProjectMessage
import org.apache.log4j.Logger

abstract class Transformation(
    private val amountOfTransformations: Int = 1
) {
    abstract fun transform(target: FTarget)

    fun execTransformations(projectMessage: ProjectMessage): Set<ProjectMessage> {
        val result = mutableSetOf<ProjectMessage>()
        repeat(amountOfTransformations) {
            val project = Project(projectMessage)
            try {
                val file = project.klib
                if (file.text.lines().size > MAX_LINES) {
                    log.debug("File is too big, returning back")
                    return@repeat
                }
                repeat(5) {
                transform(FTarget(project, file))
                    println("DADA")
                }
            } finally {
                result.add(project.createProjectMessage())
                project.dispose()
                UsagesSamplesGenerator.disposeProjects()
            }
            if (it % 10 == 0 && it != 0)
                log.debug("$it transformations completed")
        }
        result.remove(projectMessage)
        return result
    }

    companion object {
        val log = Logger.getLogger("mutatorLogger")
    }

    override fun toString() = "transformation=${this::class.java.simpleName}"

    private val MAX_LINES = 500
}

data class FTarget(
    val project: Project,
    val file: BBFFile
)