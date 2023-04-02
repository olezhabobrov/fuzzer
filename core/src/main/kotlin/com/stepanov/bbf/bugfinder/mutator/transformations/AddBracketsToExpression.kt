package com.stepanov.bbf.bugfinder.mutator.transformations


import com.stepanov.bbf.bugfinder.mutator.MutationProcessor
import com.stepanov.bbf.bugfinder.util.getAllPSIChildrenOfType
import com.stepanov.bbf.bugfinder.util.getRandomBoolean
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.psi.KtWhenExpression

class AddBracketsToExpression:
    Transformation(1) {

    override fun transform(target: FTarget) {
        val file = target.file
        file.psiFile.getAllPSIChildrenOfType<KtExpression>()
            .filter { getRandomBoolean(4) }
            .forEach {
                if (it is KtWhenExpression) return@forEach
                try {
                    val newExpr = MutationProcessor.createExpression(file, "(${it.text})")
                    MutationProcessor.replaceNodeReturnNode(it, newExpr, file)
                } catch (e: Exception) {
                    return@forEach
                }
            }
    }
}