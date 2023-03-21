package com.stepanov.bbf.bugfinder.project

import com.intellij.psi.PsiErrorElement
import com.stepanov.bbf.bugfinder.filePartition.FilePartition
import com.stepanov.bbf.bugfinder.server.messages.SourceFileTarget
import com.stepanov.bbf.information.CompilationConfiguration
import com.stepanov.bbf.messages.ProjectMessage
import com.stepanov.bbf.reduktor.parser.PSICreator
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import com.stepanov.bbf.util.getSimpleNameFile
import org.jetbrains.kotlin.psi.KtPsiFactory
import java.io.File

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

    fun createProjectMessage(): ProjectMessage {
        return ProjectMessage(
            files.map { bbfFile ->
                bbfFile.name.getSimpleNameFile() to bbfFile.text
            }
        )
    }

    fun splitProject(): ProjectMessage {
        val (first, second) = FilePartition.splitFile(files.first())
        val text1 = File(first).readText()
        val text2 = File(second).readText()
        return ProjectMessage(
            listOf(first.getSimpleNameFile() to text1,
                second.getSimpleNameFile() to text2),
            isSplit = true
        )
    }

    override fun hashCode(): Int {
        return files.map { bbfFile ->
            bbfFile.name to bbfFile.text
        }.sumOf { (name, text) -> name.hashCode() * text.hashCode() }
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Project)
            return false
        val files = files.map { bbfFile ->
            bbfFile.name to bbfFile.text
        }
        val otherFiles = other.files.map { bbfFile ->
            bbfFile.name to bbfFile.text
        }
        return files.sortedBy { it.first }.zip(otherFiles.sortedBy { it.first }).all { (first, second) ->
            first.second == second.second
        }
    }

}