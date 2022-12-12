package com.stepanov.bbf.bugfinder.mutator

import com.stepanov.bbf.bugfinder.executor.project.LANGUAGE
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.generator.targetsgenerators.typeGenerators.RandomTypeGenerator
import com.stepanov.bbf.bugfinder.mutator.transformations.*
import com.stepanov.bbf.bugfinder.mutator.transformations.util.ExpressionReplacer
import com.stepanov.bbf.reduktor.parser.PSICreator
import org.apache.log4j.Logger
import org.jetbrains.kotlin.psi.KtFile
import kotlin.random.Random
import kotlin.system.exitProcess

class Mutator(val project: Project) {

    private fun executeMutation(t: Transformation, probPercentage: Int = 50) {
        if (Random.nextInt(0, 100) < probPercentage) {
            //Update ctx
            Transformation.updateCtx()
            Transformation.ctx ?: return
            log.debug("Cur transformation ${t::class.simpleName}")
            t.transform()
            processor.curFile.changePsiFile(processor.curFile.text)
        }
    }


    fun startMutate() {
        for (bbfFile in project.files) {
            log.debug("Mutation of ${bbfFile.name} started")
//            Transformation.checker.curFile = bbfFile
            Transformation.processor = MutationProcessor(project, bbfFile)
            when (bbfFile.getLanguage()) {
                LANGUAGE.JAVA -> startJavaMutations()
                LANGUAGE.KOTLIN -> startKotlinMutations()
            }
            log.debug("End")
        }
    }


    //TODO!! Implement java mutations
    private fun startJavaMutations() {
        TODO("")
//        println("STARTING JAVA MUTATIONS")
//        executeMutation(ChangeRandomJavaASTNodesFromAnotherTrees(), 100)
//        println("END JAVA MUTATIONS")
//        log.debug("Verify = ${verify()}")
//        return
    }

    private fun startKotlinMutations() {
        val ktx = PSICreator.analyze(processor.curFile.psiFile, processor.project) ?: return
        val ktFile = processor.curFile.psiFile as KtFile
        RandomTypeGenerator.setFileAndContext(ktFile, ktx)
        if (!processor.checkCompiling()) return
        val mut1 = listOf(
            ExpressionReplacer() to 100,
        ).shuffled()
        for (i in 0 until Random.nextInt(1, 3)) {
            mut1.forEach { executeMutation(it.first, it.second) }
        }
        exitProcess(0)
    }


    private fun isProject() = project.files.size > 1

    private fun verify(): Boolean {
        val res = Transformation.processor.checkCompiling()
        if (!res) {
            log.debug("Cant compile project ${project}")
            System.exit(1)
        }
        return res
    }

    private val log = Logger.getLogger("bugFinderLogger")
    private val processor
        get() = Transformation.processor

}