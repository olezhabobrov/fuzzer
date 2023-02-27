package com.stepanov.bbf.bugfinder.mutator.transformations

import com.intellij.psi.PsiFile
import com.stepanov.bbf.bugfinder.project.BBFFile
import com.stepanov.bbf.bugfinder.project.Project
import com.stepanov.bbf.bugfinder.mutator.MutationProcessor
import com.stepanov.bbf.bugfinder.mutator.transformations.tce.StdLibraryGenerator
import com.stepanov.bbf.bugfinder.util.addAfterThisWithWhitespace
import com.stepanov.bbf.bugfinder.util.getAllPSIChildrenOfType
import com.stepanov.bbf.bugfinder.util.getTrue
import com.stepanov.bbf.reduktor.parser.PSICreator.psiFactory
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.psi.KtLabeledExpression
import org.jetbrains.kotlin.psi.psiUtil.parents
import kotlin.random.Random

class AddRandomControlStatements(project: Project, file: BBFFile):
    Transformation(project, file,
        1, 100) {
    override fun transform() {
        repeat(RANDOM_CONST) { insertRandomStatement() }
    }

    private fun insertRandomStatement() {
        val fileBackup = file.psiFile.copy() as PsiFile
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

    private val RANDOM_CONST = Random.nextInt(50, 51)
    private val listOfRandomExceptions = StdLibraryGenerator.getListOfExceptionsFromStdLibrary()
}