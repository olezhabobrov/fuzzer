package com.stepanov.bbf.bugfinder.project

import com.intellij.psi.PsiErrorElement
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.messages.ProjectMessage
import com.stepanov.bbf.reduktor.parser.PSICreator
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType

class Project(
    fileNameList: List<String>
) {
    val env = PSICreator.createEnv(fileNameList)
    val files: List<BBFFile> = env.getSourceFiles().map { BBFFile(it, env) }

    fun isSyntaxCorrect(): Boolean =
        files.all { Factory.psiFactory.createFile(it.text).getAllPSIChildrenOfType<PsiErrorElement>().isEmpty() }


    override fun toString(): String = files.joinToString("\n\n") {
        it.name + "\n" +
                it.psiFile.text
    }

    fun getProjectMessage(): ProjectMessage {
        val result = ProjectMessage(
            files.map { bbfFile ->
                bbfFile.name to bbfFile.text
            },
            "tmp/build",
            ""
        )
        return result
    }

}