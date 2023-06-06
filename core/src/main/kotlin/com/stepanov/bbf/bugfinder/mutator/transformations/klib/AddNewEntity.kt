package com.stepanov.bbf.bugfinder.mutator.transformations.klib

import com.stepanov.bbf.bugfinder.mutator.transformations.AddRandomClass
import com.stepanov.bbf.bugfinder.mutator.transformations.FTarget
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GClass
import com.stepanov.bbf.bugfinder.util.addPsiToBody
import com.stepanov.bbf.bugfinder.util.getRandomVariableName
import com.stepanov.bbf.reduktor.parser.PSICreator.psiFactory
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import com.stepanov.bbf.util.WeightedList
import org.jetbrains.kotlin.lexer.KtModifierKeywordToken
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.getOrCreateBody
import kotlin.random.Random

class AddNewEntity: BinaryCompatibleTransformation(15) {

    override fun transform(target: FTarget) {
        val file = target.file
        val allClasses = file.psiFile.getAllPSIChildrenOfType<KtClassOrObject>()
        val outerEntity: KtClassOrObject?
        if (allClasses.isEmpty() || Random.nextInt(0, 100) < TOP_LEVEL_PROBABILITY) {
            outerEntity = null
        } else {
            outerEntity = allClasses.random()
        }
        val entityToCreate = WeightedList(listOf(
            "fun" to FUNCTION_PROB,
            "class" to CLASS_PROB,
            "interface" to INTERFACE_PROB,
            "object" to OBJECT_PROB,
        )).getRandom()
        val randomName = Random.getRandomVariableName()
        val newEntity = when(entityToCreate) {
            "fun" -> psiFactory.createFunction("fun $randomName() {}")
            "class" -> GClass().also {
                it.classWord = "class"
                it.name = randomName.capitalize()
            }.toPsiThrowable()
            "interface" -> GClass().also {
                it.classWord = "interface"
                it.name = randomName.capitalize()
            }.toPsiThrowable()
            "object" -> GClass().also {
                it.classWord = "object"
                it.name = randomName.capitalize()
            }.toPsiThrowable()
            else -> error("shouldn't be here: AddNewEntity")
        }
        if (outerEntity != null) {
            outerEntity.addPsiToBody(newEntity)
        } else {
            file.psiFile.add(newEntity)
        }
    }

    val TOP_LEVEL_PROBABILITY = 50
    val INTERFACE_PROB = 20.0
    val CLASS_PROB = 50.0
    val FUNCTION_PROB = 50.0
    val OBJECT_PROB = 20.0
}