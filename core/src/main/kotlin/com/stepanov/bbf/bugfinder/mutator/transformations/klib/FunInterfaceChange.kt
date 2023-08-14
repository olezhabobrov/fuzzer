package com.stepanov.bbf.bugfinder.mutator.transformations.klib

import com.stepanov.bbf.bugfinder.mutator.transformations.FTarget
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GClass
import com.stepanov.bbf.bugfinder.util.findPsi
import com.stepanov.bbf.bugfinder.util.replaceThis
import org.jetbrains.kotlin.descriptors.ClassKind
import org.jetbrains.kotlin.psi.KtClassOrObject

class FunInterfaceChange: BinaryCompatibleTransformation(1) {
    override fun transform(target: FTarget) {
        val file = target.file
        val clazz = file.getAllClassDescriptors()
            .filter { descr ->
                descr.kind == ClassKind.INTERFACE &&
                        descr.getAbstractMembers().size == 1
            }.randomOrNull()?.findPsi() as? KtClassOrObject ?: return
        val gclass = GClass.fromPsi(clazz)
        if (gclass.isFunInterface()) {
            gclass.removeFun()
        } else {
            gclass.addFun()
        }
        clazz.replaceThis(gclass.toPsiThrowable())
    }
}