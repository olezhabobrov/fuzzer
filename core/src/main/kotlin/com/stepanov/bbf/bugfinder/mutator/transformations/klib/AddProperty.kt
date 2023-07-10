package com.stepanov.bbf.bugfinder.mutator.transformations.klib

import com.stepanov.bbf.bugfinder.mutator.transformations.FTarget
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.generators.RandomPropertyGenerator
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GClass
import com.stepanov.bbf.bugfinder.util.addPsiToBody
import com.stepanov.bbf.bugfinder.util.getAllPSIChildrenOfType
import com.stepanov.bbf.bugfinder.util.replaceThis
import com.stepanov.bbf.reduktor.parser.PSICreator.psiFactory
import org.jetbrains.kotlin.psi.KtBlockExpression
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtProperty

class AddProperty: BinaryCompatibleTransformation(1) {

    override fun transform(target: FTarget) {
        val file = target.file
        val randomKlass = file.psiFile.getAllPSIChildrenOfType<KtClass>()
            .filter {
                val gclass = GClass.fromPsi(it)
                !gclass.isInterface()
            }
            .randomOrNull() ?: return
        val gClass = GClass.fromPsi(randomKlass)
        val property = RandomPropertyGenerator(file, gClass).generateRandomProperty(false)
        var r = randomKlass.addPsiToBody(psiFactory.createProperty(property)) ?: return
        if (r is KtBlockExpression) {
            val newR = r.getAllPSIChildrenOfType<KtProperty>().firstOrNull()
            if (newR != null) r = newR
        }
        val newKlass = psiFactory.createClass(randomKlass.text)
        randomKlass.replaceThis(newKlass)
    }

}