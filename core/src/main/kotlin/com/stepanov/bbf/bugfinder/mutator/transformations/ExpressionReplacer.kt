package com.stepanov.bbf.bugfinder.mutator.transformations

import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.generator.targetsgenerators.RandomInstancesGenerator
import com.stepanov.bbf.bugfinder.mutator.MutationProcessor
import com.stepanov.bbf.bugfinder.mutator.transformations.tce.StdLibraryGenerator
import com.stepanov.bbf.bugfinder.mutator.transformations.util.ScopeCalculator
import com.stepanov.bbf.bugfinder.mutator.transformations.util.ScopeCalculator.Companion.processScope
import com.stepanov.bbf.bugfinder.project.BBFFile
import com.stepanov.bbf.bugfinder.project.Project
import com.stepanov.bbf.bugfinder.util.getNameWithoutError
import com.stepanov.bbf.reduktor.parser.PSICreator
import com.stepanov.bbf.reduktor.util.getAllChildren
import org.jetbrains.kotlin.descriptors.CallableDescriptor
import org.jetbrains.kotlin.descriptors.FunctionDescriptor
import org.jetbrains.kotlin.descriptors.PropertyDescriptor
import org.jetbrains.kotlin.psi.KtDotQualifiedExpression
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.psi.KtSimpleNameExpression
import org.jetbrains.kotlin.psi.psiUtil.getReceiverExpression
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.calls.callUtil.getType
import org.jetbrains.kotlin.types.KotlinType
import org.jetbrains.kotlin.types.isError
import org.jetbrains.kotlin.types.isNullable
import org.jetbrains.kotlin.types.typeUtil.isTypeParameter
import org.jetbrains.kotlin.types.typeUtil.isUnit

//Class which trying to replace expression of one type by expressions from available context
class ExpressionReplacer: Transformation(20) {

    private val blockListOfTypes = listOf("Nothing", "Nothing?")
    private val generatedFunCalls = mutableMapOf<FunctionDescriptor, KtExpression?>()
    private var rig: RandomInstancesGenerator? = null


    override fun transform(target: FTarget) {
        val file = target.file
        val ktFile = file.psiFile
        val ctx = file.ctx ?: error("PSICreator returned null")
        rig = RandomInstancesGenerator(file)
        val nodeToChange = updateReplacement(ktFile.getAllChildren(), ctx).random()
        replaceExpression(nodeToChange.first, nodeToChange.second!!, file, target.project)
    }

    private fun replaceExpression(exp: KtExpression, expType: KotlinType, file:BBFFile, project: Project): Boolean {
        if (expType.getNameWithoutError() in blockListOfTypes) return false
        val processedScope = ScopeCalculator(file, project).run {
            processScope(rig!!, calcScope(exp).shuffled(), generatedFunCalls)
        }
        val randomExpressionToReplace = getRandomExpressionToReplace(expType, processedScope) ?: return false
        return MutationProcessor.replaceNodeReturnNode(exp, randomExpressionToReplace, file)
    }

    private fun handleCallSeq(postfix: List<CallableDescriptor>, scope: List<ScopeCalculator.ScopeComponent>): KtExpression? {
        val res = StringBuilder()
        var prefix = ""
        postfix.map { desc ->
            val expr = when (desc) {
                is PropertyDescriptor -> desc.name.asString()
                is FunctionDescriptor -> ScopeCalculator.generateCallExpr(rig!!, desc, scope)?.text
                else -> ""
            }
            expr ?: return null
            res.append(prefix)
            prefix = if (desc.returnType?.isNullable() == true) "?." else "."
            res.append(expr)
        }
        return PSICreator.psiFactory.createExpression(res.toString())
    }

    private fun getRandomExpressionToReplace(
        needType: KotlinType,
        scope: List<ScopeCalculator.ScopeComponent>
    ): KtExpression? {
        if (needType.isUnit()) {
            return scope
                .firstOrNull { it.type.isUnit() }
                ?.let { PSICreator.psiFactory.createExpression(it.psiElement.text) }
        }
        if (needType.isTypeParameter()) {
            return scope
                .filter { it.declaration !is FunctionDescriptor }
                .firstOrNull { it.type.isTypeParameter() }
                ?.let { PSICreator.psiFactory.createExpression(it.psiElement.text) }
        }
        val localRes = mutableListOf<KtExpression>()
        for (scopeEl in scope) {
            if (scopeEl.type.isTypeParameter()) continue
            //println("TRYING TO GET TYPE $needType from ${scopeEl.psiElement.text} of type ${scopeEl.type}")
            val expressionToUse = scopeEl.psiElement.copy() as? KtExpression ?: continue
            if (expressionToUse.text.isEmpty()) continue
            val typeDescriptorOfUsage = scopeEl.type.constructor.declarationDescriptor
            if (typeDescriptorOfUsage?.defaultType == needType.constructor.declarationDescriptor?.defaultType) {
                localRes.add(expressionToUse)
            }
            when {
                scopeEl.type.getNameWithoutError() == "$needType" -> {
                    localRes.add(expressionToUse)
                }
                scopeEl.type.getNameWithoutError() == "$needType?" -> {
                    localRes.add(expressionToUse)
                }
                StdLibraryGenerator.isImplementation(needType, scopeEl.type) -> {
                    localRes.add(expressionToUse)
                }
            }
            if (needType.isNullable()) localRes.add(PSICreator.psiFactory.createExpression("null"))
            StdLibraryGenerator.generateCallSequenceToGetType(scopeEl.type, needType)
                .filter { it.isNotEmpty() }
                .shuffled()
                .take(5)
                .forEach { list ->
                    handleCallSeq(list, scope)?.let {
                        val prefix = "(${expressionToUse.text})" + if (needType.isNullable()) "?." else "."
                        val exp = "$prefix${it.text}"
                        val postfix = if (exp.contains("?.") && !needType.isNullable()) "!!" else ""
                        PSICreator.psiFactory.createExpression("$exp$postfix").let {
                            localRes.add(it)
                        }
                    }
                }
            if (localRes.isNotEmpty()) {
                return localRes.random()
            }
        }
        return null
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