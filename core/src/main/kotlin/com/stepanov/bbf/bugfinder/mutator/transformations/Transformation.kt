package com.stepanov.bbf.bugfinder.mutator.transformations

import com.stepanov.bbf.bugfinder.project.BBFFile
import com.stepanov.bbf.bugfinder.project.Project
import com.stepanov.bbf.messages.ProjectMessage
import org.apache.log4j.Logger

abstract class Transformation(
    private val amountOfTransformations: Int
) {
    protected abstract fun transform(target: FTarget)

    fun execTransformations(target: FTarget): Set<ProjectMessage> {
        val result = mutableSetOf<ProjectMessage>()
        repeat(amountOfTransformations) {
            transform(target)
            result.add(target.project.createProjectMessage())
        }
        return result
    }

    companion object {
        val log = Logger.getLogger("mutatorLogger")
    }

    override fun toString() = "transformation=${this::class.java.simpleName}"
}

data class FTarget(
    val project: Project,
    val file: BBFFile
)