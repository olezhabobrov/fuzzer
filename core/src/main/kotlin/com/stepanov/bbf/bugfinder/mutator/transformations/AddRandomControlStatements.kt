package com.stepanov.bbf.bugfinder.mutator.transformations

import com.stepanov.bbf.bugfinder.mutator.MutationProcessor
import com.stepanov.bbf.bugfinder.mutator.transformations.tce.StdLibraryGenerator
import com.stepanov.bbf.bugfinder.project.BBFFile
import com.stepanov.bbf.bugfinder.util.addAfterThisWithWhitespace
import com.stepanov.bbf.bugfinder.util.getAllPSIChildrenOfType
import com.stepanov.bbf.bugfinder.util.getTrue
import com.stepanov.bbf.reduktor.parser.PSICreator.psiFactory
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.psi.KtLabeledExpression
import org.jetbrains.kotlin.psi.psiUtil.parents
import kotlin.random.Random

class AddRandomControlStatements: Transformation(50) {
    override fun transform(target: FTarget) {
        insertRandomStatement(target.file)
    }

    private fun insertRandomStatement(file: BBFFile) {
        val randomExp = file.psiFile.getAllPSIChildrenOfType<KtExpression>().randomOrNull() ?: return
        val randomLabel =
            randomExp.parents.filter { it is KtLabeledExpression }.toList().randomOrNull() as? KtLabeledExpression
        val labelAsString =
            if (randomLabel != null && Random.getTrue(75))
                "@${randomLabel.getLabelName()}"
            else
                ""
        val randomToken =
            when (Random.nextInt(0, 10)) {
                in 0..1 -> "return"
                in 2..5 -> "break"
                else -> "continue"
            }
        val randomControlExpr =
            if (Random.getTrue(75)) {
                psiFactory.createExpression("$randomToken$labelAsString")
            } else {
                psiFactory.createExpression("throw ${listOfRandomExceptions}(\"\")")
            }
        if (Random.getTrue(90)) {
            randomExp.addAfterThisWithWhitespace(randomControlExpr, "\n")
        } else {
            MutationProcessor.replaceNodeReturnNode(randomExp, randomControlExpr, file)
        }
    }

    private val listOfRandomExceptions = StdLibraryGenerator.getListOfExceptionsFromStdLibrary()
}