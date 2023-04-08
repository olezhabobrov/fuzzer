package com.stepanov.bbf.bugfinder.mutator.transformations

import com.stepanov.bbf.bugfinder.mutator.MutationProcessor
import com.stepanov.bbf.bugfinder.mutator.MutationProcessor.psiFactory
import com.stepanov.bbf.bugfinder.project.BBFFile
import com.stepanov.bbf.bugfinder.util.getAllPSIChildrenOfType
import org.jetbrains.kotlin.psi.KtExpression

class AddNotNullAssertions: Transformation(2) {

    override fun transform(target: FTarget) {
        target.file.psiFile.getAllPSIChildrenOfType<KtExpression>()
            .random().let { tryToAddNotNullAssertion(target.file, it) }
    }

    private fun tryToAddNotNullAssertion(file: BBFFile, exp: KtExpression) {
        try {
            val newExp = psiFactory(file).createExpressionIfPossible("${exp.text}!!") ?: return
            MutationProcessor.replaceNodeReturnNode(exp, newExp, file)
        } catch (e: Exception) {
            return
        }
    }
}
