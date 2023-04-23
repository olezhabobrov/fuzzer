package com.stepanov.bbf.bugfinder.project

import com.intellij.psi.PsiErrorElement
import com.stepanov.bbf.bugfinder.server.messages.SourceFileTarget
import com.stepanov.bbf.messages.FileData
import com.stepanov.bbf.messages.ProjectMessage
import com.stepanov.bbf.reduktor.parser.PSICreator
import com.stepanov.bbf.reduktor.parser.PSICreator.psiFactory
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import com.stepanov.bbf.util.getSimpleNameFile
import org.jetbrains.kotlin.psi.KtPsiFactory
import java.io.File

class Project(
    projectMessage: ProjectMessage
) {
    init {
        projectMessage.files.forEach { (name, text) ->
            File(projectMessage.dir + name).writeText(text)
        }
    }

    constructor(code: String): this(
        SourceFileTarget(code).also { it.writeFile() }.let {
            ProjectMessage(listOf(FileData(it.getLocalName(), it.getSourceCode())))
        }
    )

    val env = PSICreator.createEnv(projectMessage.files.map { it.name })

    var files: List<BBFFile> = projectMessage.files.map {
        val f = psiFactory.createFile(it.name, it.text)
        // f.originalFile = it ???
        BBFFile(f, env, it.isKlib).also { it.updateCtx() }
    }

    fun createFilesFromProjectMessage(projectMessage: ProjectMessage) {
        files = projectMessage.files.map {
            val f = psiFactory.createFile(it.name, it.text)
            // f.originalFile = it ???
            BBFFile(f, env, it.isKlib).also { it.updateCtx() }
        }
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
                FileData(bbfFile.name.getSimpleNameFile(), bbfFile.text, bbfFile.isKlib)
            }
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