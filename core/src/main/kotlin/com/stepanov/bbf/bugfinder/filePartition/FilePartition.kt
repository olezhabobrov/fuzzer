package com.stepanov.bbf.bugfinder.filePartition

import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.project.BBFFile
import java.io.File

object FilePartition {
    fun splitFile(file: BBFFile): Pair<String, String> {
        file.updateCtx()
        val children = file.psiFile.children.toList()
        val (children1, children2) = children.chunked(children.size / 2 + 1)
        val oldName = file.name
        val newName1 = getNewNameForFile(oldName, 1)
        val newName2 = getNewNameForFile(oldName, 2)
        // TODO: add package if there is one
        File(newName1).writeText(getTextForElements(children1))
        File(newName2).writeText(getTextForElements(children2))
        return (newName1 to newName2)
    }

    private fun getTextForElements(elements: List<PsiElement>): String {
        val stringBuilder = StringBuilder()
        elements.forEach {
            stringBuilder.append(it.text)
        }
        return stringBuilder.toString()
    }

    private fun getNewNameForFile(oldName: String, i: Int): String =
        "${oldName.substringBeforeLast(".kt")}$i.kt"

}