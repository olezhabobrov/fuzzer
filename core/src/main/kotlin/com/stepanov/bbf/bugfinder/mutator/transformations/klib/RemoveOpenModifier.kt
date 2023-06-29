package com.stepanov.bbf.bugfinder.mutator.transformations.klib

import com.stepanov.bbf.bugfinder.mutator.transformations.FTarget
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GStructure
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import com.stepanov.bbf.reduktor.util.replaceThis
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtFunction
import org.jetbrains.kotlin.psi.KtParameter
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner

class RemoveOpenModifier: BinaryIncompatibleTransformation(1) {
    override fun transform(target: FTarget) {
        val randomEntity = target.file.getAllEntities().filter {
            val gStructure = GStructure.fromPsi(it)
            gStructure.isOpen()
        }.randomOrNull() ?: return
        if (randomEntity !is KtClass) {
            removeOpen(randomEntity)
        } else {
            val openNestedEntities = (randomEntity.getAllPSIChildrenOfType<KtProperty>() +
                    randomEntity.getAllPSIChildrenOfType<KtFunction>()).filter {
                        val gStructure = GStructure.fromPsi(it)
                        gStructure.isOpen()
            }
            openNestedEntities.forEach {
                removeOpen(it)
            }
            removeOpen(randomEntity)
        }
    }

    private fun removeOpen(entity: KtTypeParameterListOwner) {
        val gStructure = GStructure.fromPsi(entity)
        gStructure.removeOpen()
        entity.replaceThis(gStructure.toPsi()!!)
    }
}