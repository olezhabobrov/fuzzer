package com.stepanov.bbf.bugfinder.mutator.transformations

import com.stepanov.bbf.bugfinder.project.BBFFile
import com.stepanov.bbf.bugfinder.project.Project
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.generators.RandomPropertyGenerator
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GClass
import com.stepanov.bbf.bugfinder.util.*
import com.stepanov.bbf.reduktor.parser.PSICreator.psiFactory
import org.jetbrains.kotlin.psi.*

class AddPropertiesToClass(project: Project, file: BBFFile):
    Transformation(project, file,
        1, 100) {

    override fun transform() {
        repeat(RAND_CONST) {
            val ctx = file.updateCtx() ?: return
            val randomKlass = file.psiFile.getAllPSIChildrenOfType<KtClass>().randomOrNull() ?: return
            val gClass = GClass.fromPsi(randomKlass)
            val (genProp, rType) = RandomPropertyGenerator(file, gClass).generateInterestingProperty(randomKlass)
                ?: return@repeat
            var r = randomKlass.addPsiToBody(genProp) ?: return@repeat
            if (r is KtBlockExpression) {
                val newR = r.getAllPSIChildrenOfType<KtProperty>().firstOrNull()
                if (newR != null) r = newR
            }
            val newKlass = psiFactory.createClass(randomKlass.text)
            randomKlass.replaceThis(newKlass)
        }
    }

    private val RAND_CONST = 50
}
