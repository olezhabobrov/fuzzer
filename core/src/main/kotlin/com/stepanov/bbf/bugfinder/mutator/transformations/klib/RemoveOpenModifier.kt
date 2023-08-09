package com.stepanov.bbf.bugfinder.mutator.transformations.klib

import com.stepanov.bbf.bugfinder.mutator.transformations.FTarget
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GStructure
import com.stepanov.bbf.bugfinder.util.findPsi
import com.stepanov.bbf.bugfinder.util.getMembers
import com.stepanov.bbf.reduktor.util.replaceThis
import org.jetbrains.kotlin.descriptors.ClassKind
import org.jetbrains.kotlin.descriptors.FunctionDescriptor
import org.jetbrains.kotlin.descriptors.Modality
import org.jetbrains.kotlin.descriptors.PropertyDescriptor
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner

class RemoveOpenModifier: BinaryIncompatibleTransformation(1) {
    override fun transform(target: FTarget) {
        val clazz = target.file.getAllClassDescriptors()
            .filter { it.modality != Modality.FINAL && it.kind != ClassKind.INTERFACE }
            .filter { descriptor ->
                descriptor.defaultType.getMembers().any {
                (it is FunctionDescriptor && it.modality == Modality.OPEN) ||
                        (it is PropertyDescriptor && it.modality == Modality.OPEN)
                }
            }.randomOrNull() ?: return
        val memberPsi = clazz.defaultType.getMembers().find {
            (it is FunctionDescriptor && it.modality == Modality.OPEN) ||
                    (it is PropertyDescriptor && it.modality == Modality.OPEN) }
            ?.findPsi() as? KtTypeParameterListOwner?: return
        removeOpen(memberPsi)
    }

    private fun removeOpen(entity: KtTypeParameterListOwner) {
        val gStructure = GStructure.fromPsi(entity)
        gStructure.removeOpen()
        entity.replaceThis(gStructure.toPsi()!!)
    }
}