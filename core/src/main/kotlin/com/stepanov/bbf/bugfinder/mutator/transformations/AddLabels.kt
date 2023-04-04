package com.stepanov.bbf.bugfinder.mutator.transformations

import com.stepanov.bbf.bugfinder.mutator.MutationProcessor
import com.stepanov.bbf.bugfinder.util.getAllPSIChildrenOfFourTypes
import com.stepanov.bbf.bugfinder.util.getRandomVariableName
import com.stepanov.bbf.bugfinder.util.getTrue
import com.stepanov.bbf.reduktor.parser.PSICreator.psiFactory
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.util.capitalizeDecapitalize.toLowerCaseAsciiOnly
import kotlin.random.Random

class AddLabels: Transformation() {
    override fun transform(target: FTarget) {
        val file = target.file
        val randomLoops =
            file.psiFile.getAllPSIChildrenOfFourTypes<KtForExpression, KtWhileExpression, KtDoWhileExpression, KtLambdaExpression>()
                .filter { Random.getTrue(25) && it.parent !is KtLabeledExpression }
                .map { it as KtExpression }
                .reversed()
        for (randomLoop in randomLoops) {
            val labelRandomName = Random.getRandomVariableName(1).toLowerCaseAsciiOnly()
            val newLabeledExpression = psiFactory.createLabeledExpression("$labelRandomName@${randomLoop.text}")
            MutationProcessor.replaceNodeReturnNode(randomLoop, newLabeledExpression, file)
        }
    }
}