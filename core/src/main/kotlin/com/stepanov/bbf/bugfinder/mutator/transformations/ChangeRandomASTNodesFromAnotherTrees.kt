package com.stepanov.bbf.bugfinder.mutator.transformations

import com.stepanov.bbf.information.CompilerArgs
import com.stepanov.bbf.bugfinder.executor.project.BBFFile
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.mutator.MutationProcessor

import com.stepanov.bbf.bugfinder.util.NodeCollector
import com.stepanov.bbf.bugfinder.util.getAllChildrenNodes
import org.jetbrains.kotlin.psi.KtConstantExpression
import org.jetbrains.kotlin.psi.KtNamedFunction
import java.io.File
import kotlin.random.Random


class ChangeRandomASTNodesFromAnotherTrees(project: Project, file: BBFFile,
                                           amountOfTransformations: Int = 1, probPercentage: Int = 100):
    Transformation(project, file,
        amountOfTransformations, probPercentage) {

    override fun transform() {
        val randConst = Random.nextInt(numOfTries.first, numOfTries.second)
        val nodes = file.psiFile.node.getAllChildrenNodes().filter { it.elementType !in NodeCollector.excludes }
        log.debug("Trying to change some nodes to nodes from other programs $randConst times")
        for (i in 0..randConst) {
            log.debug("Try №$i of $randConst")
            val randomNode = nodes[Random.nextInt(0, nodes.size - 1)]
            //Do not touch box func
            if (randomNode.psi is KtNamedFunction && randomNode.text.contains("fun box")) continue
            /*if (randomNode.getAllParentsWithoutNode().size < magicConst) continue*/
            //Searching nodes of same type in another files
            val line = File("database.txt").bufferedReader().lines()
                    .filter { it.takeWhile { it != ' ' } == randomNode.elementType.toString() }.findFirst()
            if (!line.isPresent) continue
            val files = line.get().dropLast(1).takeLastWhile { it != '[' }.split(", ")
            val randomFile = files.random()
            val psi = Factory.psiFactory.createFile(File("${CompilerArgs.baseDir}/$randomFile").readText())
            val targetNode = psi.node.getAllChildrenNodes().filter { it.elementType == randomNode.elementType }.random()
            //if (targetNode.psi.getAllPSIChildrenOfType<KtNameReferenceExpression>().isNotEmpty()) continue
            if (targetNode.psi is KtConstantExpression) continue
            MutationProcessor.replaceNodeReturnNode(randomNode, targetNode, file)
        }
    }

    /*val magicConst = 4*/
    private val numOfTries = if (project.files.size == 1) 500 to 1000 else 2000 to 4000
}