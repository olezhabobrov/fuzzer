package com.stepanov.bbf.bugfinder.mutator.transformations

import com.intellij.lang.ASTNode
import com.stepanov.bbf.bugfinder.mutator.MutationProcessor
import com.stepanov.bbf.bugfinder.project.BBFFile
import com.stepanov.bbf.bugfinder.util.getAllChildrenNodes
import com.stepanov.bbf.bugfinder.util.getRandomBoolean
import com.stepanov.bbf.reduktor.parser.PSICreator.psiFactory
import org.jetbrains.kotlin.KtNodeTypes
import org.jetbrains.kotlin.lexer.KtTokens
import java.util.*

class ChangeOperators: Transformation(20) {

    override fun transform(target: FTarget) {
        val file = target.file
        val operators = file.psiFile.node.getAllChildrenNodes()
                .filter { it.treeParent.elementType == KtNodeTypes.OPERATION_REFERENCE || it.elementType == KtTokens.DOT }
        operators.forEach {
            when (it.text) {
                "<" -> replaceOperator(file, it.treeParent, listOf(">=", ">", "<=", "==", "!="))
                "<=" -> replaceOperator(file, it.treeParent, listOf(">=", "<", ">", "==", "!="))
                ">" -> replaceOperator(file, it.treeParent, listOf(">=", "<", "<=", "==", "!="))
                ">=" -> replaceOperator(file, it.treeParent, listOf(">", "<", "<=", "==", "!="))
                "==" -> replaceOperator(file, it.treeParent, "!=")
                "!=" -> replaceOperator(file, it.treeParent, "==")
                "+" -> replaceOperator(file, it.treeParent, listOf("-", "*", "/", "%"))
                "-" -> replaceOperator(file, it.treeParent, listOf("+", "*", "/", "%"))
                "/" -> replaceOperator(file, it.treeParent, listOf("-", "*", "+", "%"))
                "*" -> replaceOperator(file, it.treeParent, listOf("-", "+", "/", "%"))
                //TODO add ?.
                "." -> replaceOperator(file, it, "!!.")
                "+=" -> replaceOperator(file, it.treeParent, listOf("-=", "*=", "/="))
                "-=" -> replaceOperator(file, it.treeParent, listOf("+=", "*=", "/="))
                "*=" -> replaceOperator(file, it.treeParent, listOf("-=", "+=", "/="))
                "/=" -> replaceOperator(file, it.treeParent, listOf("-=", "*=", "+="))
                "&&" -> replaceOperator(file, it.treeParent, "||")
                "||" -> replaceOperator(file, it.treeParent, "&&")
                "++" -> replaceOperator(file, it, "--")
                "--" -> replaceOperator(file, it, "++")
                else -> return@forEach
            }
        }
    }

    private fun replaceOperator(file: BBFFile, replace: ASTNode, replacement: List<String>, isRandom: Boolean = true) {
        if (isRandom && getRandomBoolean() || !isRandom) {
            val index = Random().nextInt(replacement.size)
            replaceOperator(file, replace, replacement[index])
        }
    }

    private fun replaceOperator(file: BBFFile, replace: ASTNode, replacement: String, isRandom: Boolean = true) {
        if (isRandom && getRandomBoolean() || !isRandom) {
            val replacementNode =
                    if (replace.elementType == KtNodeTypes.OPERATION_REFERENCE)
                        psiFactory.createOperationName(replacement)
                    else
                        psiFactory.createExpression(replacement)

            MutationProcessor.replaceNodeReturnNode(replace.psi, replacementNode, file)
        }
    }


}