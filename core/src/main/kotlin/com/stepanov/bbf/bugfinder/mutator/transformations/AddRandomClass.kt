package com.stepanov.bbf.bugfinder.mutator.transformations

import com.stepanov.bbf.bugfinder.executor.project.BBFFile
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.generators.RandomClassGenerator
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GClass
import com.stepanov.bbf.bugfinder.util.addToTheTop
import com.stepanov.bbf.bugfinder.util.getTrue
import com.stepanov.bbf.reduktor.parser.PSICreator
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.KtFile
import kotlin.random.Random

class AddRandomClass(project: Project, file: BBFFile,
                     amountOfTransformations: Int = 1, probPercentage: Int = 100):
    Transformation(project, file,
        amountOfTransformations, probPercentage) {

    override fun transform() {
        val ktFile = file.psiFile as KtFile
        val ctx = PSICreator.analyze(ktFile) ?: return
        val parentClass = ktFile.getAllPSIChildrenOfType<KtClassOrObject>().randomOrNull()
        val klassGenerator =
            if (parentClass != null && Random.getTrue(50)) {
                val parentClassAsGClass = GClass.fromPsi(parentClass)
                RandomClassGenerator(ktFile, ctx, 0, parentClassAsGClass)
            } else {
                RandomClassGenerator(ktFile, ctx)
            }
        val newClass = klassGenerator.generate() ?: return
        ktFile.addToTheTop(newClass)
    }
}