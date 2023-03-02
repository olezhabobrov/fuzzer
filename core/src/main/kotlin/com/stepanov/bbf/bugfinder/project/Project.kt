package com.stepanov.bbf.bugfinder.project

import com.intellij.psi.PsiErrorElement
import com.stepanov.bbf.bugfinder.server.messages.SourceFileTarget
import com.stepanov.bbf.messages.ProjectMessage
import com.stepanov.bbf.reduktor.parser.PSICreator
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import org.jetbrains.kotlin.psi.KtPsiFactory
import kotlin.math.log

class Project(
    fileNameList: List<String>
) {
    constructor(code: String): this(listOf(
        SourceFileTarget(code).also { it.writeFile() }.getLocalName()
    ))

    val env = PSICreator.createEnv(fileNameList)
    val files: List<BBFFile> = env.getSourceFiles().map {
        val f = KtPsiFactory(it).createFile(it.virtualFile.path, it.text)
        f.originalFile = it
        BBFFile(f, env)
    }

    fun isSyntaxCorrect(): Boolean =
        files.all { it.psiFile.getAllPSIChildrenOfType<PsiErrorElement>().isEmpty() }


    override fun toString(): String = files.joinToString("\n\n") {
        it.name + "\n" +
                it.psiFile.text
    }

    fun getProjectMessage(logInfo: String): ProjectMessage {
        val result = ProjectMessage(
            files.map { bbfFile ->
                bbfFile.name to bbfFile.text
            },
            "tmp/build",
            "",
            logInfo
        )
        return result
    }

}