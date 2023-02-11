package com.stepanov.bbf.bugfinder.mutator.transformations

import com.stepanov.bbf.bugfinder.project.BBFFile
import com.stepanov.bbf.bugfinder.project.Project
import com.stepanov.bbf.bugfinder.mutator.MutationProcessor
import com.stepanov.bbf.bugfinder.util.*
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.util.capitalizeDecapitalize.toLowerCaseAsciiOnly
import kotlin.random.Random

class AddLabels(project: Project, file: BBFFile,
                amountOfTransformations: Int = 1, probPercentage: Int = 100):
    Transformation(project, file,
        amountOfTransformations, probPercentage) {
    override fun transform() {
        val randomLoops =
            file.psiFile.getAllPSIChildrenOfFourTypes<KtForExpression, KtWhileExpression, KtDoWhileExpression, KtLambdaExpression>()
                .filter { Random.getTrue(25) && it.parent !is KtLabeledExpression }
                .map { it as KtExpression }
                .reversed()
        for (randomLoop in randomLoops) {
            val labelRandomName = Random.getRandomVariableName(1).toLowerCaseAsciiOnly()
            val newLabeledExpression = Factory.psiFactory.createLabeledExpression("$labelRandomName@${randomLoop.text}")
            MutationProcessor.replaceNodeReturnNode(randomLoop, newLabeledExpression, file)
        }
    }
}