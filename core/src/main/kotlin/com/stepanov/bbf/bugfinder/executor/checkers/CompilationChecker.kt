package com.stepanov.bbf.bugfinder.executor.checkers

import com.intellij.psi.PsiErrorElement
import com.intellij.psi.PsiFile
import com.stepanov.bbf.bugfinder.executor.CommonCompiler
import com.stepanov.bbf.bugfinder.executor.project.BBFFile
import com.stepanov.bbf.bugfinder.executor.project.LANGUAGE
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.reduktor.parser.PSICreator
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import org.apache.log4j.Logger

//Project adaptation
open class CompilationChecker(private val compilers: List<CommonCompiler>) {

    constructor(compiler: CommonCompiler) : this(listOf(compiler))

    //Back compatibility
    fun checkTextCompiling(text: String): Boolean = checkCompilingWithBugSaving(Project.createFromCode(text), null)
    fun checkCompilingWithBugSaving(file: PsiFile): Boolean = checkTextCompiling(file.text)


    private fun createPsiAndCheckOnErrors(text: String, language: LANGUAGE): Boolean =
        when (language) {
            LANGUAGE.JAVA -> PSICreator.getPsiForJava(text, Factory.file.project)
            else -> Factory.psiFactory.createFile(text)
        }.let { tree ->
            tree.getAllPSIChildrenOfType<PsiErrorElement>().isEmpty() && additionalConditions.all { it.invoke(tree) }
        }

    //FALSE IF ERROR
    private fun checkSyntaxCorrectnessAndAddCond(project: Project, curFile: BBFFile?) =
        curFile?.let {
            createPsiAndCheckOnErrors(curFile.text, curFile.getLanguage())
        } ?: project.files.any { createPsiAndCheckOnErrors(it.text, it.getLanguage()) }


    fun checkCompiling(project: Project): Boolean {
        TODO()
    }

    fun checkCompilingWithBugSaving(project: Project, curFile: BBFFile? = null): Boolean {
        TODO()
    }

    val additionalConditions: MutableList<(PsiFile) -> Boolean> = mutableListOf()

    private val checkedConfigurations = hashMapOf<String, Boolean>()
    private val log = Logger.getLogger("mutatorLogger")
}