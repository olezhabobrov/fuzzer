package com.stepanov.bbf.bugfinder.mutator.transformations.klib

import com.stepanov.bbf.bugfinder.mutator.transformations.FTarget
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GClass
import com.stepanov.bbf.bugfinder.util.findPsi
import com.stepanov.bbf.bugfinder.util.getRandomBoolean
import com.stepanov.bbf.bugfinder.util.replaceThis
import org.jetbrains.kotlin.descriptors.Modality
import org.jetbrains.kotlin.psi.KtClassOrObject

class MakeClassAbstract: BinaryIncompatibleTransformation(1) {
    override fun transform(target: FTarget) {
        val file = target.file
        val clazz = file.getAllClassDescriptors()
            .filter { it.modality != Modality.ABSTRACT }
            .randomOrNull() ?: return
        val psi = clazz.findPsi() as? KtClassOrObject ?: return
        val gclass = GClass.fromPsi(psi)
        gclass.removeOpen()
        if (getRandomBoolean()) {
            gclass.classWord = "interface"
        } else {
            gclass.addAbstract()
        }
        val newPsi = gclass.toPsi() ?: return
        psi.replaceThis(newPsi)
    }
}