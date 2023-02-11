package com.stepanov.bbf.bugfinder.mutator.transformations

import com.stepanov.bbf.bugfinder.project.BBFFile
import com.stepanov.bbf.bugfinder.project.Project
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.generators.RandomFunctionGenerator
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GClass
import com.stepanov.bbf.bugfinder.util.addPsiToBody
import com.stepanov.bbf.reduktor.parser.PSICreator
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtNamedFunction

class AddMethodToClass(project: Project, file: BBFFile,
                       amountOfTransformations: Int = 1, probPercentage: Int = 100):
    Transformation(project, file,
        amountOfTransformations, probPercentage) {
    override fun transform() {
        repeat(MAGIC_CONST) {
            val ctx = PSICreator.analyze(file.psiFile) ?: return
            val ktFile = file.psiFile as? KtFile ?: return
            val randomClass = file.psiFile.getAllPSIChildrenOfType<KtClassOrObject>().randomOrNull() ?: return
            val randomGClass = GClass.fromPsi(randomClass)
            val functionGenerator = RandomFunctionGenerator(ktFile, ctx, randomGClass)
            val generatedFunction = functionGenerator.generate() as? KtNamedFunction ?: return@repeat
            if (randomClass.body == null) {
                randomClass.addPsiToBody(generatedFunction)
            } else {
                randomClass.addPsiToBody(generatedFunction)
            }
        }
    }

    private val MAGIC_CONST = 10
}