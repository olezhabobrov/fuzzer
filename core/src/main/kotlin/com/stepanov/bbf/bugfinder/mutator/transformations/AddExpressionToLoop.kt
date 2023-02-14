package com.stepanov.bbf.bugfinder.mutator.transformations

import com.stepanov.bbf.bugfinder.project.BBFFile
import com.stepanov.bbf.bugfinder.project.Project
import com.stepanov.bbf.bugfinder.util.*
import com.stepanov.bbf.reduktor.parser.PSICreator
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.resolve.calls.callUtil.getType
import kotlin.random.Random

class AddExpressionToLoop(project: Project, file: BBFFile):
    Transformation(project, file,
        1, 100) {
    override fun transform() {
        val ctx = PSICreator.analyze(file.psiFile) ?: return
        val expressions = file.psiFile.getAllPSIChildrenOfType<KtExpression>().filter { it.getType(ctx) != null }
        file.psiFile.getAllPSIChildrenOfType<KtLoopExpression>()
            .reversed()
            .filter { Random.getTrue(20) }
            .filter { it.body is KtBlockExpression }
            .forEach { loop ->
                repeat(5) {
                    tryToAddExpressions(loop, expressions)
                }
            }
    }

    private fun tryToAddExpressions(loopExp: KtLoopExpression, expressionsToInsert: List<KtExpression>) {
        val blockBody = loopExp.body!! as KtBlockExpression
        val blockCopy = blockBody.copy()
        val blockTextLines = (blockBody.copy() as KtBlockExpression).let { blockCopy ->
            blockCopy.rBrace?.delete()
            blockBody.lBrace?.delete()
            blockCopy.text.split("\n").filter { it.trim().isNotEmpty() }
        }
        val randomLineNumber = Random.nextInt(0, blockTextLines.size)
        val randomExpressionToInsert = expressionsToInsert.randomOrNull()?.text ?: return
        val left = blockTextLines.take(randomLineNumber).joinToString("\n")
        val right = blockTextLines.drop(randomLineNumber).joinToString("\n")
        val newText = left + "\n${randomExpressionToInsert.trimEnd()}\n" + right
        try {
            val newBlock = Factory.psiFactory.createBlock(newText)
            blockBody.replaceThis(newBlock)
        } catch (e: Exception) {
            log.debug("Caught exception: ${e.stackTraceToString()}")
        } catch (e: Error) {
            log.debug("Caught error: ${e.stackTraceToString()}")
        }
        return
    }
}