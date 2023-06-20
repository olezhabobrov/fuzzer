package com.stepanov.bbf.bugfinder.mutator.transformations.klib

import com.stepanov.bbf.bugfinder.mutator.transformations.FTarget
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GStructure
import com.stepanov.bbf.bugfinder.util.replaceThis
import org.jetbrains.kotlin.psi.psiUtil.isPublic
import kotlin.random.Random

class MakeVisibilityWeaker: BinaryCompatibleTransformation(1) {

    override fun transform(target: FTarget) {
        val file = target.file
        val allEntities = file.getAllEntities().filter { !it.isPublic }
        if (allEntities.isEmpty())
            return
        val randomEntity = allEntities.random()
        val gStructure = GStructure.fromPsi(randomEntity)
        val newVisibility = getWeakerVisibility(gStructure)
        gStructure.changeVisibility(newVisibility)
        val newEntity = gStructure.toPsi() ?: return
        randomEntity.replaceThis(newEntity)
    }

    private fun getWeakerVisibility(gStructure: GStructure): String {
        when (gStructure.getVisibility()) {
            "private" -> {
                if (Random.nextInt(100) < 70)
                    return "public"
                if (Random.nextBoolean())
                    return "protected"
                return "internal"
            }
            "protected" -> {
                if (Random.nextInt(100) < 70)
                    return "public"
                return "internal"
            }
            else -> return "public"
        }
    }
}