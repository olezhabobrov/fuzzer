package com.stepanov.bbf.bugfinder.filePartition

import com.intellij.ide.SelectInEditorManager
import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.project.BBFFile
import com.stepanov.bbf.messages.ProjectMessage
import org.jetbrains.kotlin.psi.KtPackageDirective

object FilePartition {
    fun splitFile(file: BBFFile): ProjectMessage {
        file.updateCtx()
        val children = file.psiFile.children.toList()

        val (children1, children2) = divideChildren(children)
        val oldName = file.name
        val newName1 = getNewNameForFile(oldName, 1)
        val newName2 = getNewNameForFile(oldName, 2)
        // TODO: add package if there is one
        return ProjectMessage(listOf(
            newName1 to getTextForElements(children1),
            newName2 to getTextForElements(children2)))
    }

    private fun getTextForElements(elements: List<PsiElement>): String {
        val stringBuilder = StringBuilder()
        elements.forEach {
            stringBuilder.append(it.text)
        }
        return stringBuilder.toString()
    }

    private fun divideChildren(children: List<PsiElement>): Pair<List<PsiElement>, List<PsiElement>> {
        val (children1, children2) = children.chunked(children.size / 2 + 1).map { it.toMutableList() }
        val packages = children1.filter { it is KtPackageDirective }
        packages.reversed().forEach { pc ->
            children2.add(0, pc)
        }
        return children1 to children2
    }

    private fun getNewNameForFile(oldName: String, i: Int): String =
        "${oldName.substringBeforeLast(".kt")}$i.kt"

}