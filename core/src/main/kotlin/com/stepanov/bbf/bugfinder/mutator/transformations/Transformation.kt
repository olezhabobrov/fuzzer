package com.stepanov.bbf.bugfinder.mutator.transformations

import com.stepanov.bbf.bugfinder.filePartition.FilePartition
import com.stepanov.bbf.bugfinder.project.BBFFile
import com.stepanov.bbf.bugfinder.project.Project
import com.stepanov.bbf.kootstrap.FooBarCompiler
import com.stepanov.bbf.messages.ProjectMessage
import org.apache.log4j.Logger

abstract class Transformation(
    private val amountOfTransformations: Int
) {
    protected abstract fun transform(target: FTarget)

    fun execTransformations(projectMessage: ProjectMessage): Set<ProjectMessage> {
        val result = mutableSetOf<ProjectMessage>()
//        result.add(tryToSplit(projectMessage))
        repeat(amountOfTransformations) {
            val project = Project.createFromProjectMessage(projectMessage)
            val file = project.files.random()
            if (file.text.lines().size > MAX_LINES) {
                log.debug("File is too big, returning back")
                return@repeat
            }
            transform(FTarget(project, file))
            result.add(project.createProjectMessage())
            FooBarCompiler.tearDownMyEnv(project.env)

        }
        return result
    }

    private fun tryToSplit(projectMessage: ProjectMessage): ProjectMessage {
        val project = Project.createFromProjectMessage(projectMessage)
        return FilePartition.splitFile(project.files.random()).also {
            FooBarCompiler.tearDownMyEnv(project.env)
        }
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