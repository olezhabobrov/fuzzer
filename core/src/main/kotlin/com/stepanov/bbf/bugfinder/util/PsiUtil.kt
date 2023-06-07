package com.stepanov.bbf.bugfinder.util

import com.intellij.psi.*
import com.intellij.util.IncorrectOperationException
import com.stepanov.bbf.bugfinder.mutator.transformations.filterDuplicates
import com.stepanov.bbf.reduktor.parser.PSICreator.psiFactory
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import com.stepanov.bbf.reduktor.util.getAllParentsWithoutNode
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.psi.psiUtil.allChildren
import org.jetbrains.kotlin.psi.psiUtil.parents
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.bindingContextUtil.getAbbreviatedTypeOrType
import org.jetbrains.kotlin.resolve.calls.callUtil.getType
import org.jetbrains.kotlin.types.KotlinType

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

fun KtFile.findClassByName(name: String): KtClassOrObject? =
    this.getAllPSIChildrenOfType<KtClassOrObject>().firstOrNull { it.name == name }


fun PsiElement.addAfterThisWithWhitespace(psiElement: PsiElement, whiteSpace: String): PsiElement {
    return try {
        val placeToInsert = this.allChildren.lastOrNull() ?: this
        placeToInsert.add(psiFactory.createWhiteSpace(whiteSpace))
        val res = placeToInsert.add(psiElement)
        placeToInsert.add(psiFactory.createWhiteSpace(whiteSpace))
        res
    } catch (e: IncorrectOperationException) {
        this
    }
}

fun KtFile.getAvailableValuesToInsertIn(
    node: PsiElement,
    ctx: BindingContext
): List<Pair<KtExpression, KotlinType?>> {
    val nodeParents = node.parents.toList()
    val parameters = nodeParents
        .filterIsInstance<KtCallableDeclaration>()
        .flatMap { it.valueParameters }
        .filter { it.name != null }
        .map { psiFactory.createExpression(it.name ?: "") to it.typeReference?.getAbbreviatedTypeOrType(ctx) }
        .filter { it.second != null }
    val props = nodeParents
        .flatMap { it.getAllPSIDFSChildrenOfType<PsiElement>().takeWhile { it != node } }
        .filterIsInstance<KtProperty>()
        .filterDuplicates { a: KtProperty, b: KtProperty -> if (a == b) 0 else 1 }
        .filter { it.parents.filter { it is KtBlockExpression }.all { it in nodeParents } }
        .filter { it.name != null }
        .map {
            val kotlinType = it.typeReference?.getAbbreviatedTypeOrType(ctx) ?: it.initializer?.getType(ctx)
            psiFactory.createExpression(it.name ?: "") to kotlinType
        }
        .filter { it.second != null }
    return parameters + props
}

fun KtBlockExpression.addToBlock(element: PsiElement) {
    val blockCopy = this.copy() as KtBlockExpression
    val blockText = blockCopy.let {
        blockCopy.rBrace?.delete()
        blockCopy.lBrace?.delete()
        blockCopy.text
    }
    val newText = blockText + "\n${element.text}\n"
    val newBlock = psiFactory.createBlock(newText)
    this.replace(newBlock)
}