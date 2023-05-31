package com.stepanov.bbf.bugfinder.mutator

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.impl.source.tree.TreeElement
import com.stepanov.bbf.information.CompilerArgs
import com.stepanov.bbf.bugfinder.project.BBFFile
import com.stepanov.bbf.bugfinder.util.getAllParentsWithoutNode
import org.apache.log4j.Logger
import org.jetbrains.kotlin.psi.KtPsiFactory
import java.io.File

object MutationProcessor {

    fun createExpression(file: BBFFile, text: String) =
            KtPsiFactory(file.psiFile.project).createExpression(text)

    fun createExpressionOrThrow(file: BBFFile, text: String) = try {
        KtPsiFactory(file.psiFile.project).createExpression(text)
    } catch (_: Throwable) {
        null
    }

    fun psiFactory(file: BBFFile) = KtPsiFactory(file.psiFile.project)

    fun replaceNodeReturnNode(node: ASTNode, replacement: ASTNode, curFile: BBFFile, filenameOpt: String? = null): ASTNode? {
//        log.debug("Trying to replace $node on $replacement")
        if (node.text.isEmpty() || node == replacement) {
            return node
        }
        for (p in node.getAllParentsWithoutNode()) {
            try {
                if (node.treeParent.elementType.index == DUMMY_HOLDER_INDEX) continue
                val oldText = curFile.text
                val replCopy = replacement.copyElement()
                if ((node as TreeElement).treeParent !== p) {
                    continue
                }
                p.replaceChild(node, replCopy)
                if (oldText == curFile.text)
                    continue
                if (CompilerArgs.shouldSaveMutatedFiles) {
                    if (filenameOpt != null)
                        saveMutation(curFile, CompilerArgs.pathToMutatedDir + filenameOpt)
                }
                return replCopy
            } catch (e: Error) {
                log.debug("Caught error in MutationProcessor while replacing node: ${e.stackTraceToString()}")
                throw e
            }
        }
        return null
    }

    fun replaceNodeReturnNode(node: PsiElement, replacement: PsiElement, curFile: BBFFile, filenameOpt: String? = null): Boolean =
        replaceNodeReturnNode(node.node, replacement.node, curFile, filenameOpt) != null

    fun saveMutation(file: BBFFile, pathToSave: String) {
        File(CompilerArgs.pathToMutatedDir).mkdirs()
        File(pathToSave).writeText(file.psiFile.text)
    }

    fun addNode(anchor: PsiElement, node: PsiElement, before: Boolean = false) {
        if (node.text.isEmpty() || node == anchor) return
        try {
            if (before) anchor.parent.addBefore(node, anchor)
            else anchor.parent.addAfter(node, anchor)
        } catch (e: Throwable) {
            log.debug("e = $e")
        }
    }
    private val DUMMY_HOLDER_INDEX: Short = 86
    private val log = Logger.getLogger("mutatorLogger")
}