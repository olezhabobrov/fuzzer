package com.stepanov.bbf.bugfinder.mutator.transformations

import com.stepanov.bbf.bugfinder.mutator.MutationProcessor
import com.stepanov.bbf.bugfinder.util.NodeCollector
import com.stepanov.bbf.bugfinder.util.getAllChildrenNodes
import com.stepanov.bbf.information.CompilerArgs
import com.stepanov.bbf.reduktor.parser.PSICreator.psiFactory
import org.jetbrains.kotlin.psi.KtConstantExpression
import org.jetbrains.kotlin.psi.KtNamedFunction
import java.io.File
import kotlin.random.Random


class ChangeRandomASTNodesFromAnotherTrees: Transformation(2000) {

    override fun transform(target: FTarget) {
        val nodes = target.file.psiFile.node.getAllChildrenNodes().filter { it.elementType !in NodeCollector.excludes }
            val randomNode = nodes[Random.nextInt(0, nodes.size - 1)]
            //Do not touch box func
            if (randomNode.psi is KtNamedFunction && randomNode.text.contains("fun box")) return
            /*if (randomNode.getAllParentsWithoutNode().size < magicConst) continue*/
            //Searching nodes of same type in another files
            val line = File("database.txt").bufferedReader().lines()
                    .filter { it.takeWhile { it != ' ' } == randomNode.elementType.toString() }.findFirst()
            if (!line.isPresent) return
            val files = line.get().dropLast(1).takeLastWhile { it != '[' }.split(", ")
            val randomFile = files.random()
            val psi = psiFactory.createFile(File("${CompilerArgs.baseDir}/$randomFile").readText())
            val targetNode = psi.node.getAllChildrenNodes().filter { it.elementType == randomNode.elementType }.random()
            //if (targetNode.psi.getAllPSIChildrenOfType<KtNameReferenceExpression>().isNotEmpty()) continue
            if (targetNode.psi is KtConstantExpression) return
            MutationProcessor.replaceNodeReturnNode(randomNode, targetNode, target.file)
        }

}