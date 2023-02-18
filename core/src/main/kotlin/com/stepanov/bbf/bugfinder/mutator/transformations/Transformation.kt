package com.stepanov.bbf.bugfinder.mutator.transformations

import com.stepanov.bbf.bugfinder.project.BBFFile
import com.stepanov.bbf.bugfinder.project.Project
import org.apache.log4j.Logger

abstract class Transformation(
    val project: Project,
    val file: BBFFile,
    val amountOfTransformations: Int,
    val probPercentage: Int
) {
    protected abstract fun transform()
    protected var useCounter = 0

    fun execTransformations() {
        repeat(amountOfTransformations) { transform() }
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