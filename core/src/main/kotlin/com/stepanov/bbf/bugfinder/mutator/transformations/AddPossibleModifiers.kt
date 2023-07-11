package com.stepanov.bbf.bugfinder.mutator.transformations

import com.stepanov.bbf.bugfinder.util.getAllChildrenNodes
import com.stepanov.bbf.bugfinder.util.getRandomBoolean
import org.jetbrains.kotlin.KtNodeTypes
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtFunction
import org.jetbrains.kotlin.psi.KtProperty
import java.util.*

class AddPossibleModifiers: Transformation(100) {

    override fun transform(target: FTarget) {
        val file = target.file
        val value = file.psiFile.node.getAllChildrenNodes()
                .filter { it.elementType == KtNodeTypes.CLASS || it.elementType == KtNodeTypes.PROPERTY
                        || it.elementType == KtNodeTypes.FUN }
                .random()
        val curWorkingList =
            when (value.elementType) {
                KtNodeTypes.CLASS -> possibleClassModifiers
                KtNodeTypes.PROPERTY -> possiblePropertyModifiers
                else -> possibleFunctionModifiers
            }
        val el =
            when (value.elementType) {
                KtNodeTypes.CLASS -> value.psi as KtClass
                KtNodeTypes.PROPERTY -> value.psi as KtProperty
                else -> value.psi as KtFunction
            }
        val num = Random().nextInt(curWorkingList.size)
        val keyword = KtTokens.MODIFIER_KEYWORDS_ARRAY.find { it.value == curWorkingList[num] } ?: return
        if (el.hasModifier(keyword)) return
        el.addModifier(keyword)
    }

    private val possibleClassModifiers = listOf("private", "protected", "internal", "public", "open", "inner", "sealed",
            "data", "abstract", "enum")

    private val possiblePropertyModifiers = listOf("lateinit", "override", "open", "final", "abstract", "private",
            "public", "protected", "internal", "const")

    private val possibleFunctionModifiers = listOf("tailrec", "operator", "infix") //"external", "suspend"

}