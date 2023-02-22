package com.stepanov.bbf.bugfinder.util

import com.intellij.psi.*
import com.intellij.util.IncorrectOperationException
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.psi.psiUtil.allChildren

fun PsiFile.getNodesBetweenWhitespaces(begin: Int, end: Int): List<PsiElement> {
    val resList = mutableListOf<PsiElement>()
    var whiteSpacesCounter = 0
    for (node in getAllPSIDFSChildrenOfType<PsiElement>()) {
        if (node is PsiWhiteSpace || node is PsiComment) whiteSpacesCounter += node.text.count { it == '\n' }
        if (this is KtFile && node is KtStringTemplateEntry) whiteSpacesCounter += node.text.count { it == '\n' }
        if (whiteSpacesCounter in begin..end) resList.add(node)
        if (whiteSpacesCounter > end) break
    }
    return resList
}

fun KtNamedFunction.isUnit() = this.typeReference == null && this.hasBlockBody()

fun KtPsiFactory.createNonEmptyClassBody(body: String): KtClassBody {
    return createClass("class A(){\n$body\n}").body!!
}
