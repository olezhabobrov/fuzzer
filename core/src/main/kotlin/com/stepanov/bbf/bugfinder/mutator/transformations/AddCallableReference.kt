package com.stepanov.bbf.bugfinder.mutator.transformations

import com.stepanov.bbf.bugfinder.project.BBFFile
import com.stepanov.bbf.bugfinder.project.Project
import com.stepanov.bbf.bugfinder.mutator.MutationProcessor
import com.stepanov.bbf.bugfinder.util.filterDuplicatesBy
import com.stepanov.bbf.bugfinder.util.getAllPSIChildrenOfType
import com.stepanov.bbf.bugfinder.util.getTrue
import com.stepanov.bbf.bugfinder.util.replaceThis
import com.stepanov.bbf.reduktor.parser.PSICreator
import org.jetbrains.kotlin.builtins.isFunctionType
import org.jetbrains.kotlin.psi.KtCallExpression
import org.jetbrains.kotlin.psi.KtCallableReferenceExpression
import org.jetbrains.kotlin.psi.KtReferenceExpression
import org.jetbrains.kotlin.resolve.calls.callUtil.getType
import kotlin.random.Random

class AddCallableReference(project: Project, file: BBFFile,
                           amountOfTransformations: Int = 1, probPercentage: Int = 100):
    Transformation(project, file,
        amountOfTransformations, probPercentage) {
    override fun transform() {
        var ctx = PSICreator.analyze(file.psiFile) ?: return
        val callsWithFunTypes =
            file.psiFile.getAllPSIChildrenOfType<KtCallExpression>()
                .filter { it.valueArguments.any { it.getArgumentExpression()?.getType(ctx)?.isFunctionType == true } }
        val potentialCallableReferences =
            project.files.flatMap {
                it.psiFile.getAllPSIChildrenOfType<KtReferenceExpression>()
                    .filter { it !is KtCallExpression }
                    .filterDuplicatesBy { it.text }
            }
        tryToReplaceCallableReferences(potentialCallableReferences)
        //Deal with single or not single lamda arg
        for (c in callsWithFunTypes) {
            val lambdaArgument = c.lambdaArguments.firstOrNull() ?: continue
            if (c.valueArgumentList == null) {
                val newValueArgumentList = Factory.psiFactory.createCallArguments("(${lambdaArgument.text})")
                lambdaArgument.replaceThis(newValueArgumentList)
            } else {
                val oldValueArgs = c.valueArguments.joinToString(", ") { it.text }
                val newValueArgumentList = Factory.psiFactory.createCallArguments("($oldValueArgs)")
                c.valueArgumentList!!.replaceThis(newValueArgumentList)
                lambdaArgument.delete()
            }
        }
        ctx = PSICreator.analyze(file.psiFile) ?: return
        for (c in callsWithFunTypes) {
            if (Random.getTrue(50)) continue
            c.valueArguments.forEach { arg ->
                val argType = arg.getArgumentExpression()?.getType(ctx) ?: return@forEach
                if (!argType.isFunctionType) return@forEach
                for (ref in potentialCallableReferences.shuffled()) {
                    val newCallable = Factory.psiFactory.createCallableReferenceExpression("::${ref.text}")
                    log.debug("TRYING TO REPLACE ${arg.text} on ${newCallable?.text} ")
                    if (newCallable != null) {
                        if (MutationProcessor.replaceNodeReturnNode(arg, newCallable, file)) {
                            log.debug("Successfully replaced")
                        }
                    }
                }
            }
        }
    }

    private fun tryToReplaceCallableReferences(potentialCallableReferences: List<KtReferenceExpression>) {
        val callableReferences = file.psiFile.getAllPSIChildrenOfType<KtCallableReferenceExpression>()
        val potentialCallableReferencesAsCallableReferences =
            potentialCallableReferences.mapNotNull { Factory.psiFactory.createCallableReferenceExpression("::${it.text}") }
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