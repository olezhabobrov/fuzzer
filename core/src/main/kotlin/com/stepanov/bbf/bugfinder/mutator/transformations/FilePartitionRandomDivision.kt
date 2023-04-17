package com.stepanov.bbf.bugfinder.mutator.transformations

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiWhiteSpace
import com.stepanov.bbf.bugfinder.project.BBFFile
import com.stepanov.bbf.bugfinder.project.Project
import com.stepanov.bbf.messages.ProjectMessage
import com.stepanov.bbf.reduktor.parser.PSICreator.psiFactory
import org.jetbrains.kotlin.psi.KtImportList
import org.jetbrains.kotlin.psi.KtPackageDirective
import kotlin.random.Random

class FilePartitionRandomDivision: Transformation(3) {
    override fun transform(target: FTarget) {
        val projectMessage = splitFile(target.file, target.project)
        if (projectMessage != null)
            target.project.createFilesFromProjectMessage(projectMessage)
    }

    fun splitFile(file: BBFFile, project: Project): ProjectMessage? {
        file.updateCtx()
        val children = file.psiFile.children.toList()
        val (children1, children2) = divideChildren(children) ?: return null
        val oldName = file.name
        val newName1 = getNewNameForFile(oldName, 1)
        val newName2 = getNewNameForFile(oldName, 2)
        return ProjectMessage(
            mutableListOf(
            newName1 to getTextForElements(children1),
            newName2 to getTextForElements(children2)).also { it.addAll(
                project.files.filter { it != file }.map {
                    it.name to it.text
                }
            )}
        )
    }

    private fun getTextForElements(elements: List<PsiElement>): String {
        val stringBuilder = StringBuilder()
        elements.forEach {
            stringBuilder.append(it.text + "\n\n")
        }
        return stringBuilder.toString()
    }

    private fun divideChildren(children: List<PsiElement>): Pair<List<PsiElement>, List<PsiElement>>? {
        val (children1, children2) = children.filter { it !is KtPackageDirective && it !is PsiWhiteSpace && it !is KtImportList}
            .partition { Random.nextBoolean() }.toList()
            .map { it.toMutableList() }
        if (children1.isEmpty() || children2.isEmpty())
            return null
        val packageAndImportList = children.filter { it is KtPackageDirective || it is KtImportList }
        packageAndImportList.reversed().forEach { pc ->
            children1.add(0, pc)
            children2.add(0, pc)
        }
        return children1 to children2
    }

    private fun getNewNameForFile(oldName: String, i: Int): String =
        "${oldName.substringBeforeLast(".kt")}$i.kt"


}