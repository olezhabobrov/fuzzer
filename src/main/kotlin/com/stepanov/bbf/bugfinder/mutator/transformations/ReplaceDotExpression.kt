package com.stepanov.bbf.bugfinder.mutator.transformations

import com.stepanov.bbf.bugfinder.mutator.transformations.Factory.tryToCreateExpression
import com.stepanov.bbf.bugfinder.util.getAllPSIChildrenOfType
import com.stepanov.bbf.bugfinder.util.getTrue
import org.jetbrains.kotlin.psi.KtDotQualifiedExpression
import kotlin.random.Random

class ReplaceDotExpression: Transformation() {
    override fun transform() {
        file.getAllPSIChildrenOfType<KtDotQualifiedExpression>().reversed()
            .filter { Random.getTrue(15) }
            .forEach {
                val left = it.receiverExpression.text
                val right = it.selectorExpression?.text ?: return@forEach
                val safeCallExpression = Factory.psiFactory.tryToCreateExpression("$left?.$right") ?: return@forEach
                checker.replaceNodeIfPossible(it, safeCallExpression)
            }
    }
}