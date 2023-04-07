package com.stepanov.bbf.bugfinder.mutator.transformations

import com.stepanov.bbf.bugfinder.util.getAllPSIChildrenOfType
import com.stepanov.bbf.bugfinder.util.getTrue
import com.stepanov.bbf.bugfinder.util.replaceThis
import com.stepanov.bbf.reduktor.parser.PSICreator.psiFactory
import org.jetbrains.kotlin.psi.KtBlockExpression
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.psi.KtLoopExpression
import org.jetbrains.kotlin.resolve.calls.callUtil.getType
import kotlin.random.Random

class AddExpressionToLoop: Transformation() {
    override fun transform(target: FTarget) {
        val file = target.file
        val ctx = file.updateCtx() ?: return
        val expressions = file.psiFile.getAllPSIChildrenOfType<KtExpression>().filter { it.getType(ctx) != null }
        file.psiFile.getAllPSIChildrenOfType<KtLoopExpression>()
            .reversed()
//            .filter { Random.getTrue(20) }
            .filter { it.body is KtBlockExpression }
            .random()
            .let {  loop ->
                tryToAddExpressions(loop, expressions)
            }
    }

    private fun tryToAddExpressions(loopExp: KtLoopExpression, expressionsToInsert: List<KtExpression>) {
        val blockBody = loopExp.body!! as KtBlockExpression
        val blockCopy = blockBody.copy() as KtBlockExpression
        val blockTextLines = blockCopy.let {
            blockCopy.rBrace?.delete()
            blockCopy.lBrace?.delete()
            blockCopy.text.split("\n").filter { it.trim().isNotEmpty() }
        }
        val randomLineNumber = Random.nextInt(0, blockTextLines.size)
        val randomExpressionToInsert = expressionsToInsert.randomOrNull()?.text ?: return
        val left = blockTextLines.take(randomLineNumber).joinToString("\n")
        val right = blockTextLines.drop(randomLineNumber).joinToString("\n")
        val newText = left + "\n${randomExpressionToInsert.trimEnd()}\n" + right
        try {
            val newBlock = psiFactory.createBlock(newText)
            blockBody.replaceThis(newBlock)
        } catch (e: Exception) {
            log.debug("Caught exception: ${e.stackTraceToString()}")
        } catch (e: Error) {
            log.debug("Caught error: ${e.stackTraceToString()}")
        }
        return
    }
}