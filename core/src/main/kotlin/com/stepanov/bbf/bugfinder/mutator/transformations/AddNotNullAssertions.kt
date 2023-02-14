package com.stepanov.bbf.bugfinder.mutator.transformations

import com.stepanov.bbf.bugfinder.project.BBFFile
import com.stepanov.bbf.bugfinder.project.Project
import com.stepanov.bbf.bugfinder.mutator.MutationProcessor
import org.jetbrains.kotlin.psi.KtExpression

import com.stepanov.bbf.bugfinder.util.getAllPSIChildrenOfType
import com.stepanov.bbf.bugfinder.util.getRandomBoolean
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory.psiFactory as psiFactory

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
            val newExp = psiFactory.createExpressionIfPossible("${exp.text}!!") ?: return
            MutationProcessor.replaceNodeReturnNode(exp, newExp, file)
        } catch (e: Exception) {
            return
        }
    }
}
