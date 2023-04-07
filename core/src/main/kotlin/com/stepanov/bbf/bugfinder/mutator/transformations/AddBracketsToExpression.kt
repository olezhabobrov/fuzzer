package com.stepanov.bbf.bugfinder.mutator.transformations


import com.stepanov.bbf.bugfinder.mutator.MutationProcessor
import com.stepanov.bbf.bugfinder.util.getAllPSIChildrenOfType
import org.jetbrains.kotlin.psi.KtExpression

class AddBracketsToExpression:
    Transformation(10) {

    override fun transform(target: FTarget) {
        val file = target.file
        file.psiFile.getAllPSIChildrenOfType<KtExpression>().random().let {
            try {
                val newExpr = MutationProcessor.createExpression(file, "(${it.text})")
                MutationProcessor.replaceNodeReturnNode(it, newExpr, file)
            } catch (e: Exception) {
                return
            }
        }
    }
}