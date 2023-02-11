package com.stepanov.bbf.bugfinder.mutator.transformations

import com.stepanov.bbf.bugfinder.project.BBFFile
import com.stepanov.bbf.bugfinder.project.Project
import com.stepanov.bbf.bugfinder.mutator.MutationProcessor
import org.jetbrains.kotlin.psi.KtExpression

import com.stepanov.bbf.bugfinder.util.getAllPSIChildrenOfType
import com.stepanov.bbf.bugfinder.util.getRandomBoolean
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory.psiFactory as psiFactory

class ChangeVarToNull(project: Project, file: BBFFile,
                      amountOfTransformations: Int = 1, probPercentage: Int = 100):
    Transformation(project, file,
        amountOfTransformations, probPercentage) {

    override fun transform() {
        file.psiFile.getAllPSIChildrenOfType<KtExpression>()
                .filter { getRandomBoolean(16) }
                .forEach { MutationProcessor.replaceNodeReturnNode(it, psiFactory.createExpression("null"), file) }
    }

}