package com.stepanov.bbf.bugfinder.mutator.transformations.klib

import com.stepanov.bbf.bugfinder.mutator.transformations.FTarget
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GClass
import com.stepanov.bbf.bugfinder.util.findPsi
import com.stepanov.bbf.bugfinder.util.replaceThis
import org.jetbrains.kotlin.descriptors.ClassKind
import org.jetbrains.kotlin.js.descriptorUtils.hasPrimaryConstructor
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.resolve.descriptorUtil.getSuperClassNotAny

class ChangeValueClass: BinaryCompatibleTransformation(1) {
    override fun transform(target: FTarget) {
        val file = target.file
        val classPsi = file.getAllClassDescriptors().filter {
            it.hasPrimaryConstructor()
        }.filter {
            val primaryConstructor = it.constructors.find { it.isPrimary } ?: return@filter false
            if (primaryConstructor.valueParameters.size != 1)
                return@filter false
            if (it.getSuperClassNotAny()?.kind == ClassKind.CLASS)
                return@filter false
            val value = primaryConstructor.valueParameters.first()
            value.findPsi()?.text?.contains("val") ?: false
        }.randomOrNull()?.findPsi() as? KtClassOrObject ?: return
        val gclass = GClass.fromPsi(classPsi)
        if (gclass.isValue()) {
            gclass.removeValue()
        } else {
            gclass.addValue()
        }
        val newPsi = gclass.toPsi() ?: return
        classPsi.replaceThis(newPsi)
    }
}