package com.stepanov.bbf.bugfinder.mutator.transformations

import com.stepanov.bbf.bugfinder.project.BBFFile
import com.stepanov.bbf.bugfinder.project.Project
import com.stepanov.bbf.bugfinder.mutator.MutationProcessor
import com.stepanov.bbf.bugfinder.mutator.MutationProcessor.psiFactory
import org.jetbrains.kotlin.psi.KtExpression

import com.stepanov.bbf.bugfinder.util.getAllPSIChildrenOfType
import com.stepanov.bbf.bugfinder.util.getRandomBoolean

class AddNotNullAssertions(project: Project, file: BBFFile):
    Transformation(project, file,
        1, 100) {

    override fun transform() {
        file.psiFile.getAllPSIChildrenOfType<KtExpression>()
            .filter { getRandomBoolean(3) }
            .map { tryToAddNotNullAssertion(it) }
    }

    private fun tryToAddNotNullAssertion(exp: KtExpression) {
        try {
            val newExp = psiFactory(file).createExpressionIfPossible("${exp.text}!!") ?: return
            MutationProcessor.replaceNodeReturnNode(exp, newExp, file)
        } catch (e: Exception) {
            return
        }
    }
}
