package com.stepanov.bbf.bugfinder.mutator.transformations

import com.stepanov.bbf.bugfinder.mutator.MutationProcessor
import com.stepanov.bbf.bugfinder.project.BBFFile
import com.stepanov.bbf.bugfinder.util.getAllPSIChildrenOfType
import com.stepanov.bbf.bugfinder.util.getRandomVariableName
import com.stepanov.bbf.reduktor.parser.PSICreator.psiFactory
import org.jetbrains.kotlin.psi.KtConstantExpression
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.psi.KtStringTemplateEntry
import java.util.*

class ChangeConstants: Transformation() {

    enum class Type { BOOLEAN, INTEGER, DOUBLE }

    override fun transform(target: FTarget) {
        val file = target.file
        val constants = file.psiFile.getAllPSIChildrenOfType<KtConstantExpression>()
        val stringConstants = file.psiFile.getAllPSIChildrenOfType<KtStringTemplateEntry>()
        constants.forEach {
            when {
                it.text == "true" || it.text == "false" -> changeExpression(file, it,
                    Type.BOOLEAN
                )
                it.textContains('.') -> changeExpression(file, it,
                    Type.DOUBLE
                )
                else -> changeExpression(file, it,
                    Type.INTEGER
                )
            }
        }
        //println("constants = ${stringConstants.map { it.text }}")
        stringConstants
                .forEach { changeStringConst(file, it) }
    }

    private fun changeExpression(file: BBFFile, exp: KtExpression, type: Type, isRandom: Boolean = true) {
        val replacement = when (type) {
            Type.BOOLEAN -> psiFactory.createExpression("${Random().nextBoolean()}")
            Type.DOUBLE -> psiFactory.createExpression("${Random().nextDouble()}")
            Type.INTEGER -> psiFactory.createExpression("${Random().nextInt()}")
        }
        if (isRandom && Random().nextBoolean() || !isRandom)
            MutationProcessor.replaceNodeReturnNode(exp, replacement, file)
    }


    private fun changeStringConst(file: BBFFile, exp: KtStringTemplateEntry, isRandom: Boolean = true) =
            if (isRandom && Random().nextBoolean() || !isRandom)
                MutationProcessor.replaceNodeReturnNode(exp,
                        psiFactory.createExpression(Random().getRandomVariableName(NAME_SIZE)), file)
            else false

    private val NAME_SIZE = 5
}