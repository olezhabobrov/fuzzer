package com.stepanov.bbf.bugfinder.mutator.transformations

import org.jetbrains.kotlin.psi.KtExpression
import com.stepanov.bbf.bugfinder.executor.MutationChecker
import com.stepanov.bbf.bugfinder.util.getAllPSIChildrenOfType
import com.stepanov.bbf.bugfinder.util.getRandomBoolean

class ChangeVarToNull : Transformation() {

    override fun transform() {
        file.getAllPSIChildrenOfType<KtExpression>()
                .filter { getRandomBoolean(16) }
                .forEach { MutationChecker.replacePSINodeIfPossible(file, it, psiFactory.createExpression("null")) }
    }

}