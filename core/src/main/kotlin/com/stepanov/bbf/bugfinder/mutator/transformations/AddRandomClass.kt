package com.stepanov.bbf.bugfinder.mutator.transformations

import com.stepanov.bbf.bugfinder.mutator.transformations.abi.generators.RandomClassGenerator
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GClass
import com.stepanov.bbf.bugfinder.util.addToTheTop
import com.stepanov.bbf.bugfinder.util.getTrue
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import org.jetbrains.kotlin.psi.KtClassOrObject
import kotlin.random.Random

class AddRandomClass: Transformation() {

    override fun transform(target: FTarget) {
        val file = target.file
        val parentClass = file.psiFile.getAllPSIChildrenOfType<KtClassOrObject>().randomOrNull()
        val klassGenerator =
            if (parentClass != null && Random.getTrue(50)) {
                val parentClassAsGClass = GClass.fromPsi(parentClass)
                RandomClassGenerator(file, 0, parentClassAsGClass)
            } else {
                RandomClassGenerator(file)
            }
        val newClass = klassGenerator.generate() ?: return
        file.psiFile.addToTheTop(newClass)
    }
}