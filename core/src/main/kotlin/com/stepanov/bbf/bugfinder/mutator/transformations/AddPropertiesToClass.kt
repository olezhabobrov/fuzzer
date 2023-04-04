package com.stepanov.bbf.bugfinder.mutator.transformations

import com.stepanov.bbf.bugfinder.mutator.transformations.abi.generators.RandomPropertyGenerator
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GClass
import com.stepanov.bbf.bugfinder.util.addPsiToBody
import com.stepanov.bbf.bugfinder.util.getAllPSIChildrenOfType
import com.stepanov.bbf.bugfinder.util.replaceThis
import com.stepanov.bbf.reduktor.parser.PSICreator.psiFactory
import org.jetbrains.kotlin.psi.KtBlockExpression
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtProperty

class AddPropertiesToClass: Transformation() {

    override fun transform(target: FTarget) {
        val file = target.file
        val ctx = file.updateCtx() ?: return
        val randomKlass = file.psiFile.getAllPSIChildrenOfType<KtClass>().randomOrNull() ?: return
        val gClass = GClass.fromPsi(randomKlass)
        val (genProp, rType) = RandomPropertyGenerator(file, gClass).generateInterestingProperty(randomKlass)
            ?: return
        var r = randomKlass.addPsiToBody(genProp) ?: return
        if (r is KtBlockExpression) {
            val newR = r.getAllPSIChildrenOfType<KtProperty>().firstOrNull()
            if (newR != null) r = newR
        }
        val newKlass = psiFactory.createClass(randomKlass.text)
        randomKlass.replaceThis(newKlass)
    }

}
