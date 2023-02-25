package com.stepanov.bbf.bugfinder.util

import com.stepanov.bbf.bugfinder.project.Project
import com.stepanov.bbf.bugfinder.generator.targetsgenerators.typeGenerators.RandomTypeGenerator

object DefaultKotlinTypes {
    private val rtg: RandomTypeGenerator

    init {
        val project = Project("""
            fun box() {
                return "OK"
            }
        """.trimIndent())
        rtg = RandomTypeGenerator(project.files.first())
    }

    val unitType = rtg.generateType("Unit")!!
    val anyType = rtg.generateType("Any")
    val nullableAny = rtg.generateType("Any?")
    val intType = rtg.generateType("Int")!!

}