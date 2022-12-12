package com.stepanov.bbf.bugfinder.mutator.transformations

import com.intellij.psi.PsiFile
import com.stepanov.bbf.bugfinder.executor.checkers.MutationChecker
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.mutator.MutationProcessor
import com.stepanov.bbf.reduktor.parser.PSICreator
import org.apache.log4j.Logger
import org.jetbrains.kotlin.resolve.BindingContext

abstract class Transformation {
    abstract fun transform()
    protected var useCounter = 0

    companion object {
//        lateinit var checker: MutationChecker

        lateinit var processor: MutationProcessor
        val file: PsiFile
            get() = processor.curFile.psiFile
        val project: Project
            get() = processor.project
        var ctx: BindingContext? = null
        internal val log = Logger.getLogger("mutatorLogger")

        fun updateCtx() {
            ctx = PSICreator.analyze(file, project)
        }
    }

}