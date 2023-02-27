package com.stepanov.bbf.bugfinder.mutator.transformations

import com.stepanov.bbf.bugfinder.project.BBFFile
import com.stepanov.bbf.bugfinder.project.Project
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.generators.RandomFunctionGenerator
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GClass
import com.stepanov.bbf.bugfinder.util.addPsiToBody
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.KtNamedFunction

class AddMethodToClass(project: Project, file: BBFFile):
    Transformation(project, file,
        1, 100) {
    override fun transform() {
        repeat(MAGIC_CONST) {
            val ctx = file.updateCtx() ?: return
            val randomClass = file.psiFile.getAllPSIChildrenOfType<KtClassOrObject>().randomOrNull() ?: return
            val randomGClass = GClass.fromPsi(randomClass)
            val functionGenerator = RandomFunctionGenerator(file, randomGClass)
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