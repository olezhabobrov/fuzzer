package com.stepanov.bbf.bugfinder.mutator.transformations

import com.stepanov.bbf.bugfinder.executor.project.BBFFile
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.mutator.MutationProcessor
import org.jetbrains.kotlin.KtNodeTypes
import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.psi.KtPsiFactory
import org.jetbrains.kotlin.psi.KtReturnExpression

import com.stepanov.bbf.bugfinder.util.getAllChildrenNodes
import com.stepanov.bbf.bugfinder.util.getAllPSIChildrenOfType
import com.stepanov.bbf.bugfinder.util.getRandomBoolean

class ChangeReturnValueToConstant(project: Project, file: BBFFile,
                                  amountOfTransformations: Int = 1, probPercentage: Int = 100):
    Transformation(project, file,
        amountOfTransformations, probPercentage) {

    override fun transform() {
        val functions = file.psiFile.getAllPSIChildrenOfType<KtNamedFunction>().filter { getRandomBoolean() }
        for (f in functions) {
            val key = typeConstants.keys.find { f.typeReference?.text?.startsWith(it) == true } ?: continue
            val returns = f.node.getAllChildrenNodes()
                    .asSequence()
                    .filter { it.elementType == KtNodeTypes.RETURN }
                    .map { it.psi as KtReturnExpression }
                    .toList()
            for (r in returns) {
                val replacement = KtPsiFactory(file.psiFile.project).createExpression(typeConstants[key]!!)
                if (r.returnedExpression != null) {
                    MutationProcessor.replaceNodeReturnNode(r.returnedExpression!!, replacement, file)
                }
            }
        }
    }

    //TODO make for a sortedMap, huyap etc.
    private val typeConstants = mapOf(Pair("Int", "1"), Pair("Double", "0.0"), Pair("String", "\"\""),
            Pair("ArrayList", "arrayListOf()"), Pair("List", "listOf()"), Pair("Set", "setOf()"), Pair("Map", "mapOf()"),
            Pair("Array", "arrayOf()"))
}