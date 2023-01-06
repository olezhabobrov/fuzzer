package com.stepanov.bbf.bugfinder.mutator

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.impl.source.tree.TreeElement
import com.stepanov.bbf.bugfinder.executor.CompilerArgs
import com.stepanov.bbf.bugfinder.executor.project.BBFFile
import com.stepanov.bbf.bugfinder.util.getAllParentsWithoutNode
import org.apache.log4j.Logger
import java.io.File

object MutationProcessor {

    /**
     * Probably unsafe.
     * Not checking if it compiles
     */
    fun replaceNode(node: ASTNode, replacement: ASTNode, curFile: BBFFile, filenameOpt: String? = null): ASTNode? {
        log.debug("[UNSAFE] Trying to replace $node on $replacement")
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
                if (CompilerArgs.checkCompilationWhileMutating) {
                    // TODO: send file to be checked

                }
                if (CompilerArgs.shouldSaveMutatedFiles) {
//                    require(filenameOpt != null)
                    if (filenameOpt != null)
                        saveMutation(curFile, CompilerArgs.pathToMutatedDir + filenameOpt)
                }
                return replCopy
            } catch (e: Error) {
            }
        }
        return null
    }

    fun replaceNode(node: PsiElement, replacement: PsiElement, curFile: BBFFile, filenameOpt: String? = null): Boolean =
        replaceNode(node.node, replacement.node, curFile, filenameOpt) != null

    fun checkCompiling(): Boolean {
        TODO()
//        if (CompilerArgs.checkCompilationWhileMutating) {
//            eventBus.request(VertxAddresses.compileCheck,
//                Json.encodeToString(CompileRequestMessage(curFile.name, curFile.text))) {
//                    compilationResult: AsyncResult<Message<String>> ->
//                val result = Json.decodeFromString<KotlincInvokeStatus>(compilationResult.result().body())
//
//            }
//        }
//        return true
    }

    fun saveMutation(file: BBFFile, pathToSave: String) {
        File(CompilerArgs.pathToMutatedDir).mkdirs()
        File(pathToSave).writeText(file.psiFile.text)
    }

    private val DUMMY_HOLDER_INDEX: Short = 86
    private val log = Logger.getLogger("mutatorLogger")
}