package com.stepanov.bbf.bugfinder.mutator.transformations

import com.stepanov.bbf.bugfinder.mutator.MutationProcessor
import com.stepanov.bbf.bugfinder.project.BBFFile
import com.stepanov.bbf.bugfinder.util.filterDuplicatesBy
import com.stepanov.bbf.bugfinder.util.getAllPSIChildrenOfType
import com.stepanov.bbf.bugfinder.util.getTrue
import com.stepanov.bbf.bugfinder.util.replaceThis
import com.stepanov.bbf.reduktor.parser.PSICreator.psiFactory
import org.jetbrains.kotlin.builtins.isFunctionType
import org.jetbrains.kotlin.psi.KtCallExpression
import org.jetbrains.kotlin.psi.KtCallableReferenceExpression
import org.jetbrains.kotlin.psi.KtReferenceExpression
import org.jetbrains.kotlin.resolve.calls.callUtil.getType
import kotlin.random.Random

class AddCallableReference:
    Transformation(1) {
    override fun transform(target: FTarget) {
        val file = target.file
        val project = target.project
        var ctx = file.updateCtx() ?: return
        val callsWithFunTypes =
            file.psiFile.getAllPSIChildrenOfType<KtCallExpression>()
                .filter { it.valueArguments.any { it.getArgumentExpression()?.getType(ctx)?.isFunctionType == true } }
        val potentialCallableReferences =
            project.files.flatMap {
                it.psiFile.getAllPSIChildrenOfType<KtReferenceExpression>()
                    .filter { it !is KtCallExpression }
                    .filterDuplicatesBy { it.text }
            }
        tryToReplaceCallableReferences(file, potentialCallableReferences)
        //Deal with single or not single lamda arg
        for (c in callsWithFunTypes) {
            val lambdaArgument = c.lambdaArguments.firstOrNull() ?: continue
            if (c.valueArgumentList == null) {
                val newValueArgumentList = psiFactory.createCallArguments("(${lambdaArgument.text})")
                lambdaArgument.replaceThis(newValueArgumentList)
            } else {
                val oldValueArgs = c.valueArguments.joinToString(", ") { it.text }
                val newValueArgumentList = psiFactory.createCallArguments("($oldValueArgs)")
                c.valueArgumentList!!.replaceThis(newValueArgumentList)
                lambdaArgument.delete()
            }
        }
        ctx = file.updateCtx() ?: return
        for (c in callsWithFunTypes) {
            if (Random.getTrue(50)) continue
            c.valueArguments.forEach { arg ->
                val argType = arg.getArgumentExpression()?.getType(ctx) ?: return@forEach
                if (!argType.isFunctionType) return@forEach
                for (ref in potentialCallableReferences.shuffled()) {
                    val newCallable = psiFactory.createCallableReferenceExpression("::${ref.text}")
//                    log.debug("TRYING TO REPLACE ${arg.text} on ${newCallable?.text} ")
                    if (newCallable != null) {
                        if (MutationProcessor.replaceNodeReturnNode(arg, newCallable, file)) {
//                            log.debug("Successfully replaced")
                        }
                    }
                }
            }
        }
    }

    private fun tryToReplaceCallableReferences(file: BBFFile,
                                               potentialCallableReferences: List<KtReferenceExpression>) {
        val callableReferences = file.psiFile.getAllPSIChildrenOfType<KtCallableReferenceExpression>()
        val potentialCallableReferencesAsCallableReferences =
            potentialCallableReferences.mapNotNull { psiFactory.createCallableReferenceExpression("::${it.text}") }
        for (c in callableReferences) {
            for (potCallableReference in potentialCallableReferencesAsCallableReferences) {
                if (c.text == potCallableReference.text) continue
                val copy = potCallableReference.copy()
                c.replaceThis(copy)
                break
            }
        }
    }
}