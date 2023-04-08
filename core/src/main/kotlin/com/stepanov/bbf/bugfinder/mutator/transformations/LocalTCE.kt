package com.stepanov.bbf.bugfinder.mutator.transformations

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiWhiteSpace
import com.stepanov.bbf.bugfinder.mutator.MutationProcessor
import com.stepanov.bbf.bugfinder.mutator.transformations.tce.FillerGenerator
import com.stepanov.bbf.bugfinder.mutator.transformations.tce.TCEUsagesCollector
import com.stepanov.bbf.bugfinder.project.BBFFile
import com.stepanov.bbf.bugfinder.util.addAfterThisWithWhitespace
import com.stepanov.bbf.bugfinder.util.flatMap
import com.stepanov.bbf.bugfinder.util.getAvailableValuesToInsertIn
import com.stepanov.bbf.bugfinder.util.getTrue
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.psi.psiUtil.getReceiverExpression
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.calls.callUtil.getType
import org.jetbrains.kotlin.types.KotlinType
import org.jetbrains.kotlin.types.isError
import org.jetbrains.kotlin.types.typeUtil.isUnit
import kotlin.random.Random

class LocalTCE: Transformation() {

    private val blockListOfTypes = listOf("Unit", "Nothing", "Nothing?")
    lateinit var usageExamples: MutableList<Triple<KtExpression, String, KotlinType?>>

    override fun transform(target: FTarget) {
        usageExamples = TCEUsagesCollector.collectUsageCases(target.file, target.project).toMutableList()
        addRandomUnitCalls(target.file)
        replaceNodesOfFile(target.file, target.file.psiFile.getAllPSIChildrenOfType())
    }

    private fun addRandomUnitCalls(file: BBFFile) {
        val unitCalls = usageExamples.filter { it.third?.isUnit() ?: false }.filter { Random.getTrue(80) }
        for (call in unitCalls) {
            val randomPlaceToInsert =
                file.psiFile.getAllPSIChildrenOfType<PsiElement>()
                    .filter { it.nextSibling.let { it is PsiWhiteSpace && it.text.contains("\n") } }
                    .randomOrNull() ?: continue
            randomPlaceToInsert.addAfterThisWithWhitespace(call.first.copy(), "\n")
        }
    }

    private fun replaceNodesOfFile(
        file: BBFFile,
        replaceNodes: List<PsiElement>
    ): Boolean {
        val fillerGenerator = FillerGenerator(file, usageExamples)
        val replacementsList = mutableListOf<PsiElement>()
        var nodesForChange = updateReplacement(replaceNodes, file.ctx!!).shuffled()
        log.debug("Trying to change ${nodesForChange.size} nodes")
        for (i in nodesForChange.indices) {
            log.debug("Node $i from ${nodesForChange.size}")
            if (Random.getTrue(60)) continue
            if (i >= nodesForChange.size) break
            val node = nodesForChange.randomOrNull() ?: continue
            log.debug("trying to replace ${node} ${node.first.text}")
            val oldUESize = usageExamples.size
            file.psiFile.getAvailableValuesToInsertIn(node.first, file.ctx!!).forEach { v ->
                if (v.second != null) usageExamples.add(Triple(v.first, v.first.text, v.second))
            }
            log.debug("replacing ${node.first.text to node.second?.toString()}")
            //node.first.parents.firstOrNull { it is KtNamedFunction }?.let { addPropertiesToFun(it as KtNamedFunction) }
            val replacement = fillerGenerator.getFillExpressions(node).randomOrNull()
            while (usageExamples.size != oldUESize) usageExamples.removeLast()
            if (replacement == null) {
                log.debug("Cant find and generate replacement for ${node.first.text} type ${node.second}")
                continue
            }
            log.debug("replacement of ${node.first.text} of type ${node.second} is ${replacement.text}")
            TODO("WTF")
//            MutationProcessor.addNode(
//                node.first.node,
//                replacement.copy().node
//            )?.let { replacementsList.add(it.psi) }
            nodesForChange = updateReplacement(replaceNodes, file.ctx!!)
        }
//        changeValuesInExpression(replacementsList)
        return true
    }


    private fun changeValuesInExpression(file: BBFFile, nodeList: List<PsiElement>) {
        val ctx = file.updateCtx() ?: return
        val constants = nodeList
            .flatMap { it.getAllPSIChildrenOfType<KtConstantExpression>() }
            .map { it to it.getType(ctx) }
            .filter { it.second != null }
        val psiExpToType = file.psiFile.getAllPSIChildrenOfType<KtExpression>()
            .map { it to it.getType(ctx) }
            .filter { it.second != null }
        val expToType = usageExamples.map { it.first to it.third } + psiExpToType
        for (constant in constants) {
            val sameTypeExpression =
                expToType.filter { it.second!!.toString() == constant.second!!.toString() }.randomOrNull()
            sameTypeExpression?.let {
                if (constant.first.parent is KtPrefixExpression) {
                    MutationProcessor.addNode(
                        constant.first.parent,
                        it.first
                    )
                } else MutationProcessor.addNode(
                    constant.first,
                    it.first
                )
            }
        }
    }

    private fun updateReplacement(nodes: List<PsiElement>, ctx: BindingContext) =
        nodes
            .asSequence()
            .filterIsInstance<KtExpression>()
            .map { it to it.getType(ctx) }
            .filter {
                it.second != null &&
                        !it.second!!.isError &&
                        it.second.toString() !in blockListOfTypes &&
                        it.second?.toString()?.contains("name provided") == false
            }
            .filter { if (it is KtSimpleNameExpression) it.getReceiverExpression() == null else true }
            .filter { it.first.parent !is KtDotQualifiedExpression }
            .toList()
}