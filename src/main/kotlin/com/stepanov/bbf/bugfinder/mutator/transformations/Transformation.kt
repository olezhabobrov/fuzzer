package com.stepanov.bbf.bugfinder.mutator.transformations

import com.stepanov.bbf.bugfinder.executor.project.BBFFile
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.reduktor.parser.PSICreator
import org.apache.log4j.Logger
import org.jetbrains.kotlin.resolve.BindingContext

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

    var ctx: BindingContext? = null
    fun updateCtx() {
        ctx = PSICreator.analyze(file.psiFile, project)
    }

    internal val log = Logger.getLogger("mutatorLogger")

}