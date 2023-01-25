package com.stepanov.bbf.bugfinder.mutator.transformations

import com.stepanov.bbf.bugfinder.executor.project.BBFFile
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.generators.*
import com.stepanov.bbf.bugfinder.generator.targetsgenerators.typeGenerators.RandomTypeGenerator
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.GeneratorFactory
import com.stepanov.bbf.reduktor.parser.PSICreator
import org.jetbrains.kotlin.psi.KtFile

class AddRandomDS(project: Project, file: BBFFile,
                  amountOfTransformations: Int = 1, probPercentage: Int = 100):
    Transformation(project, file,
        amountOfTransformations, probPercentage) {

    init {
        updateCtx()
    }

    var tries = 14
    var enoughClasses = 7

    private fun update() {
        file.changePsiFile(file.text)
        ctx = PSICreator.analyze(file.psiFile)!!
        GeneratorFactory.update(file.psiFile as KtFile, ctx!!)
        RandomTypeGenerator.setFileAndContext(file.psiFile as KtFile, ctx!!)
    }

    override fun transform() {
        var successClasses = 0
        for (i in 0 until tries) {
            update()
            //TODO!!!!
            val generator = GeneratorFactory.getRandomGenerator()
            //val generator = RandomClassGenerator(file as KtFile, ctx)
            val addedPsiElement = generator.generateAndAddToFile() ?: continue
            if (generator is AbstractClassGenerator) successClasses++
            if (successClasses == enoughClasses) break
        }
    }

}