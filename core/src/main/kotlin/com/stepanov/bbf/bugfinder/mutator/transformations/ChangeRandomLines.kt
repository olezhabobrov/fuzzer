package com.stepanov.bbf.bugfinder.mutator.transformations

import com.stepanov.bbf.bugfinder.project.BBFFile
import com.stepanov.bbf.bugfinder.project.Project
import java.util.*

class ChangeRandomLines(project: Project, file: BBFFile):
    Transformation(project, file,
        1, 100) {

    override fun transform() {
        TODO("Not really interested. Have no idea how to make it work for the project" +
                " and not just single file")
    }

    private fun getText(text: MutableList<String>) = text.joinToString(separator = "\n")

    private val shuffleConst = file.text.lines().size * 4
}