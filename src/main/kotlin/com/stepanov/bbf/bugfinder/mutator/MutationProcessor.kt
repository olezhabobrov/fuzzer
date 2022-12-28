package com.stepanov.bbf.bugfinder.mutator

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiErrorElement
import com.intellij.psi.PsiFile
import com.intellij.psi.impl.source.tree.TreeElement
import com.stepanov.bbf.bugfinder.executor.COMPILE_STATUS
import com.stepanov.bbf.bugfinder.executor.CompilerArgs
import com.stepanov.bbf.bugfinder.executor.checkers.CoverageGuider
import com.stepanov.bbf.bugfinder.executor.checkers.PerformanceOracle
import com.stepanov.bbf.bugfinder.executor.checkers.TracesChecker
import com.stepanov.bbf.bugfinder.executor.project.BBFFile
import com.stepanov.bbf.bugfinder.executor.project.LANGUAGE
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.manager.Bug
import com.stepanov.bbf.bugfinder.manager.BugManager
import com.stepanov.bbf.bugfinder.manager.BugType
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.bugfinder.tracer.Tracer
import com.stepanov.bbf.bugfinder.util.StatisticCollector
import com.stepanov.bbf.bugfinder.util.getAllParentsWithoutNode
import com.stepanov.bbf.bugfinder.vertx.CompileRequestMessage
import com.stepanov.bbf.bugfinder.vertx.MessageSender
import com.stepanov.bbf.bugfinder.vertx.VertxAddresses
import com.stepanov.bbf.reduktor.executor.KotlincInvokeStatus
import com.stepanov.bbf.reduktor.parser.PSICreator
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import coverage.MyMethodBasedCoverage
import io.vertx.core.AsyncResult
import io.vertx.core.eventbus.EventBus
import io.vertx.core.eventbus.Message
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.apache.log4j.Logger
import kotlin.math.absoluteValue

class MutationProcessor(
    val project: Project,
    val curFile: BBFFile,
    private val eventBus: EventBus,
    private val MutatorIndex: Int
) {
    val msgSender = MessageSender(eventBus)

    /**
     * Probably unsafe.
     * Not checking if it compiles
     */
    fun replaceNode(node: ASTNode, replacement: ASTNode, filenameOpt: String? = null): ASTNode? {
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
                    require(filenameOpt != null)
                    project.saveMutation(curFile, CompilerArgs.pathToMutatedDir + filenameOpt)
                }
                return replCopy
            } catch (e: Error) {
            }
        }
        return null
    }

    fun replaceNode(node: PsiElement, replacement: PsiElement, filenameOpt: String? = null): Boolean =
        replaceNode(node.node, replacement.node, filenameOpt) != null

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


    private val DUMMY_HOLDER_INDEX: Short = 86
    private val log = Logger.getLogger("mutatorLogger")
}