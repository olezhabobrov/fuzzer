package com.stepanov.bbf.bugfinder.mutator.transformations

import com.stepanov.bbf.bugfinder.project.BBFFile
import com.stepanov.bbf.bugfinder.project.Project
import java.util.*
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory.psiFactory as psiFactory

class RemoveRandomLines(project: Project, file: BBFFile,
                        amountOfTransformations: Int = 1, probPercentage: Int = 100):
    Transformation(project, file,
        amountOfTransformations, probPercentage)  {

    override fun transform() {
        val text = file.text.lines().toMutableList()
        for (i in 0..Random().nextInt(removeConst)) {
            val numLine = Random().nextInt(text.size)
            text[numLine] = ""
            log.debug("SUPER UNSAFE transformation: may be incorrect syntax")
        }
        file.changePsiFile(psiFactory.createFile(getText(text)))
    }

    private fun getText(text: MutableList<String>) = text.joinToString(separator = "\n")

    private val removeConst = file.text.lines().size * 2
}