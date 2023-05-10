package com.stepanov.bbf.bugfinder.mutator.transformations

import com.stepanov.bbf.bugfinder.mutator.transformations.abi.generators.RandomFunctionGenerator
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GClass
import com.stepanov.bbf.bugfinder.util.addPsiToBody
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.KtNamedFunction

class AddMethodToClass: Transformation(30) {
    override fun transform(target: FTarget) {
        val file = target.file
        val ctx = file.updateCtx() ?: return
        val randomClass = file.psiFile.getAllPSIChildrenOfType<KtClassOrObject>().randomOrNull() ?: return
        val randomGClass = GClass.fromPsi(randomClass)
        val functionGenerator = RandomFunctionGenerator(file, randomGClass)
        val generatedFunction = functionGenerator.generate() as? KtNamedFunction ?: return
        if (randomClass.body == null) {
            randomClass.addPsiToBody(generatedFunction)
        } else {
            randomClass.addPsiToBody(generatedFunction)
        }
    }

}