package com.stepanov.bbf.bugfinder.mutator.transformations

import com.stepanov.bbf.bugfinder.executor.project.BBFFile
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.mutator.MutationProcessor
import com.stepanov.bbf.bugfinder.mutator.transformations.tce.StdLibraryGenerator
import com.stepanov.bbf.bugfinder.util.getAllPSIChildrenOfType
import com.stepanov.bbf.bugfinder.util.getTrue
import com.stepanov.bbf.bugfinder.util.subList
import org.jetbrains.kotlin.psi.KtExpression
import kotlin.random.Random

class AddTryExpression(project: Project, file: BBFFile,
                       amountOfTransformations: Int = 1, probPercentage: Int = 100):
    Transformation(project, file,
        amountOfTransformations, probPercentage) {
    override fun transform() {
        for (i in 0 until randomConst) {
            addTryExpressionAsPsi()
        }
    }

    private fun addTryExpressionAsPsi() {
        val randomNode = file.psiFile.getAllPSIChildrenOfType<KtExpression>().randomOrNull() ?: return
        val tryBlock = "try {\n${randomNode.text}\n}"
        val catchBlocks = mutableListOf<String>()
        repeat(Random.nextInt(0, 3)) {
            val randomExpressionToInsertInCatch =
                (file.psiFile.getAllPSIChildrenOfType<KtExpression>().randomOrNull()?.text ?: "")
            val catchBlock = "catch(e: ${listOfRandomExceptions.random()}){\n" +
                    randomExpressionToInsertInCatch +
                    "\n}"
            catchBlocks.add(catchBlock)
        }
        val catchBlocksAsString = catchBlocks.joinToString("\n")
        val finallyBlock =
            if (catchBlocks.isNotEmpty() && Random.getTrue(70)) ""
            else "finally {\n ${file.psiFile.getAllPSIChildrenOfType<KtExpression>().randomOrNull()?.text ?: ""}\n}"
        val newTryExpression = Factory.psiFactory.createExpression("$tryBlock\n$catchBlocksAsString\n$finallyBlock")
        MutationProcessor.replaceNodeReturnNode(randomNode.node, newTryExpression.node, file)
    }

    private fun addTryExpressionAsText() {
        val fileText = file.text
        val numOfLinesInLine = fileText.count { it == '\n' }
        val randomPlaceToInsertFrom = Random.nextInt(0, numOfLinesInLine)
        val randomPlaceToInsertTo = Random.nextInt(randomPlaceToInsertFrom, numOfLinesInLine)
        if (randomPlaceToInsertTo - randomPlaceToInsertFrom < 2) return
        val randomTryBlockPlace =
            randomPlaceToInsertFrom to Random.nextInt(randomPlaceToInsertFrom, randomPlaceToInsertTo)
        val randomCatchBlock =
            if (Random.nextBoolean()) 0 to 0
            else randomTryBlockPlace.second to Random.nextInt(randomTryBlockPlace.second, randomPlaceToInsertTo)
        val randomFinallyBlock =
            if (Random.nextBoolean() && randomCatchBlock.first != 0)
                0 to 0
            else if (randomCatchBlock.first != 0)
                randomCatchBlock.second to Random.nextInt(
                    randomCatchBlock.second,
                    randomPlaceToInsertTo
                )
            else
                randomTryBlockPlace.second to Random.nextInt(randomTryBlockPlace.second, randomPlaceToInsertTo)
        val tryBlock =
            "try {\n${getSubtext(randomTryBlockPlace)}\n}\n"
        val catchBlock =
            if (randomCatchBlock.first == 0) ""
            else "catch (e: ${listOfRandomExceptions.random()}) {\n${getSubtext(randomCatchBlock)}\n}\n"
        val finallyBlock =
            if (randomFinallyBlock.first == 0) ""
            else "finally {\n${getSubtext(randomFinallyBlock)}\n}\n"
        val remainText =
            if (randomFinallyBlock.first == 0) getSubtext(randomCatchBlock.second to numOfLinesInLine + 1)
            else getSubtext(randomFinallyBlock.second to numOfLinesInLine + 1)
        val newText =
            "${getSubtext(0 to randomPlaceToInsertFrom)}\n" +
                    tryBlock +
                    catchBlock +
                    finallyBlock +
                    remainText
        file.changePsiFile(newText, checkCorrectness = true, genCtx = false)
    }

    private fun getSubtext(range: Pair<Int, Int>) = fileText.split("\n").subList(range).joinToString("\n")

    private val fileText: String
        get() = file.text
    private val listOfRandomExceptions = StdLibraryGenerator.getListOfExceptionsFromStdLibrary()
    private val randomConst = Random.nextInt(25, 50)
}