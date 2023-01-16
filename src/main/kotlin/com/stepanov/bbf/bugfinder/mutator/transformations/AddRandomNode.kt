package com.stepanov.bbf.bugfinder.mutator.transformations

import com.stepanov.bbf.bugfinder.executor.project.BBFFile
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.util.*
import com.stepanov.bbf.reduktor.util.getAllChildren
import org.jetbrains.kotlin.psi.KtFile
import java.io.File
import kotlin.random.Random

class AddRandomNode(project: Project, file: BBFFile,
                    amountOfTransformations: Int = 1, probPercentage: Int = 100):
    Transformation(project, file,
        amountOfTransformations, probPercentage) {
    override fun transform() {
        val randConst = Random.nextInt(numOfTries.first, numOfTries.second)
        val filteredNodes = file.psiFile.node.getAllChildrenNodes().filter { it.elementType !in NodeCollector.excludes }
        val nodeDB = File("database.txt").bufferedReader().lines().toArray().toList()
        log.debug("Trying to add some nodes $randConst times")
        println("Trying to add some nodes $randConst times")
        //From same file
        var i = 0
        while (i != 3000) {
            println("i = $i")
            val randomNode = filteredNodes.randomOrNull()?.psi?.copy() ?: continue
            val placeToInsert = file.psiFile.getAllChildren().randomOrNull() ?: continue
            val whiteSpace = if (Random.nextBoolean()) " " else "\n"
            placeToInsert.addAfterThisWithWhitespace(randomNode, whiteSpace).let {
                ++i
            }
        }
    }

    private val numOfTries = if (project.files.size == 1) 50 to 500 else 1000 to 2000
}