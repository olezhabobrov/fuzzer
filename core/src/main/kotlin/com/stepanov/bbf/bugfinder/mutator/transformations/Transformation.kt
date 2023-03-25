package com.stepanov.bbf.bugfinder.mutator.transformations

import com.stepanov.bbf.bugfinder.project.BBFFile
import com.stepanov.bbf.bugfinder.project.Project
import com.stepanov.bbf.messages.ProjectMessage
import org.apache.log4j.Logger

abstract class Transformation(
    val project: Project,
    val file: BBFFile,
    val amountOfTransformations: Int,
    val probPercentage: Int
) {
    protected abstract fun transform()

    fun execTransformations(): Set<ProjectMessage> {
        val result = mutableSetOf<ProjectMessage>()
        repeat(amountOfTransformations) {
            transform()
            result.add(project.createProjectMessage())
        }
        return result
    }

    companion object {
        val log = Logger.getLogger("mutatorLogger")
    }

    override fun toString() = """Transformation {
            file=${file.name}
            transformation=${this::class.java.simpleName}
        }
    """.trimIndent()

}