package com.stepanov.bbf.bugfinder.mutator.transformations


import com.stepanov.bbf.bugfinder.project.BBFFile
import com.stepanov.bbf.bugfinder.project.Project
import com.stepanov.bbf.bugfinder.mutator.MutationProcessor
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.psi.KtWhenExpression
import com.stepanov.bbf.bugfinder.util.getAllPSIChildrenOfType
import com.stepanov.bbf.bugfinder.util.getRandomBoolean

class AddBracketsToExpression(project: Project, file: BBFFile):
    Transformation(project, file,
        1, 100) {

    override fun transform() {
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