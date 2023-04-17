package com.stepanov.bbf.bugfinder.project

import com.intellij.psi.PsiErrorElement
import com.stepanov.bbf.bugfinder.server.messages.SourceFileTarget
import com.stepanov.bbf.messages.ProjectMessage
import com.stepanov.bbf.reduktor.parser.PSICreator
import com.stepanov.bbf.reduktor.parser.PSICreator.psiFactory
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
    var files: List<BBFFile> = env.getSourceFiles().map {
        val f = KtPsiFactory(it).createFile(it.virtualFile.path, it.text)
        f.originalFile = it
        BBFFile(f, env)
    }
        private set

    fun createFilesFromProjectMessage(projectMessage: ProjectMessage) {
        files = projectMessage.files.map {
            val f = psiFactory.createFile(it.first, it.second)
            BBFFile(f, env).also { it.updateCtx() }
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
                bbfFile.name.getSimpleNameFile() to bbfFile.text
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

    companion object {
        fun createFromProjectMessage(projectMessage: ProjectMessage): Project {
            projectMessage.files.forEach { (name, text) ->
                File(projectMessage.dir + name).writeText(text)
            }
            return Project(projectMessage.files.map { projectMessage.dir + it.first })
        }
    }

}