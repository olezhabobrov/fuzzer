package com.stepanov.bbf.bugfinder.util

import com.intellij.psi.*
import com.stepanov.bbf.reduktor.parser.PSICreator.psiFactory
import org.jetbrains.kotlin.psi.*

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


fun KtClassOrObject.addPsiToBody(prop: PsiElement): PsiElement? =
    this.body?.addBeforeRBrace(prop) ?: this.add(psiFactory.createNonEmptyClassBody(prop.text))

fun KtClassBody.addBeforeRBrace(psiElement: PsiElement): PsiElement {
    return this.rBrace?.let { rBrace ->
        val ws = this.addBefore(psiFactory.createWhiteSpace("\n"), rBrace)
        val res = this.addAfter(psiElement, ws)
        this.addAfter(psiFactory.createWhiteSpace("\n"), res)
        res
    } ?: psiElement
}

fun KtNamedFunction.isUnit() = this.typeReference == null && this.hasBlockBody()

fun KtPsiFactory.createNonEmptyClassBody(body: String): KtClassBody {
    return createClass("class A(){\n$body\n}").body!!
}
