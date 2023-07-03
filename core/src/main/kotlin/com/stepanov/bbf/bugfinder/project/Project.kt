package com.stepanov.bbf.bugfinder.project

import com.intellij.openapi.util.Disposer
import com.intellij.psi.PsiErrorElement
import com.stepanov.bbf.bugfinder.server.messages.SourceFileTarget
import com.stepanov.bbf.messages.FileData
import com.stepanov.bbf.messages.ProjectMessage
import com.stepanov.bbf.reduktor.parser.PSICreator
import com.stepanov.bbf.reduktor.parser.PSICreator.psiFactory
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import com.stepanov.bbf.util.getSimpleNameFile
import org.apache.log4j.Logger
import org.jetbrains.kotlin.psi.KtPsiFactory
import java.io.File
import java.util.concurrent.atomic.AtomicInteger

class Project(
    projectMessage: ProjectMessage
) {

    companion object {
        val counter = AtomicInteger(0)
    }

    init {
        counter.incrementAndGet()
        projectMessage.files.forEach { (name, text) ->
            File(projectMessage.dir + name).writeText(text)
        }
    }

    constructor(code: String): this(
        SourceFileTarget(code).also { it.writeFile() }.let {
            ProjectMessage(mutableListOf(FileData(it.getLocalName().getSimpleNameFile(), it.getSourceCode())), null)
        }
    )

    val env = PSICreator.createEnv(projectMessage.files.map { projectMessage.dir + it.name })

    var files: List<BBFFile> = env.getSourceFiles().map {
        val f = KtPsiFactory(it).createFile(it.virtualFile.path, it.text)
        f.originalFile = it
        val fileData = projectMessage.findByName(it.virtualFile.path.getSimpleNameFile())
        if (fileData != null) {
            BBFFile(f, env, fileData.isKlib)
        } else {
            BBFFile(f, env)
        }
    }

    val mainFile
        get() = files.first { !it.isKlib }

    val klib
        get() = files.first { it.isKlib }

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
            }.toMutableList(), null
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

    fun dispose() {
        Disposer.dispose(env.project)
        counter.decrementAndGet()
//        if (counter.decrementAndGet() > 3)
//        log.debug("PROJECT COUNT IS ${counter.get()}")
    }

    private val log = Logger.getLogger("mutatorLogger")

}