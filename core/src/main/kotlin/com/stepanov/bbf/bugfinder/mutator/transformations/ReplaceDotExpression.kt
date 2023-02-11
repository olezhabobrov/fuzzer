package com.stepanov.bbf.bugfinder.mutator.transformations

import com.stepanov.bbf.bugfinder.project.BBFFile
import com.stepanov.bbf.bugfinder.project.Project
import com.stepanov.bbf.bugfinder.mutator.MutationProcessor
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory.tryToCreateExpression
import com.stepanov.bbf.bugfinder.util.getAllPSIChildrenOfType
import com.stepanov.bbf.bugfinder.util.getTrue
import org.jetbrains.kotlin.psi.KtDotQualifiedExpression
import kotlin.random.Random

class ReplaceDotExpression(project: Project, file: BBFFile,
                           amountOfTransformations: Int = 1, probPercentage: Int = 100):
    Transformation(project, file,
        amountOfTransformations, probPercentage) {
    override fun transform() {
        file.psiFile.getAllPSIChildrenOfType<KtDotQualifiedExpression>().reversed()
            .filter { Random.getTrue(15) }
            .forEach {
                val left = it.receiverExpression.text
                val right = it.selectorExpression?.text ?: return@forEach
                val safeCallExpression = Factory.psiFactory.tryToCreateExpression("$left?.$right") ?: return@forEach
                MutationProcessor.replaceNodeReturnNode(it, safeCallExpression, file)
            }
    }
}