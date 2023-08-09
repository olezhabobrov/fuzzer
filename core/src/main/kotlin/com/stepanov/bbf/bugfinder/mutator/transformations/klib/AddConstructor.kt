package com.stepanov.bbf.bugfinder.mutator.transformations.klib

import com.stepanov.bbf.bugfinder.generator.targetsgenerators.ConstructorGenerator
import com.stepanov.bbf.bugfinder.mutator.transformations.FTarget
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GConstructor
import com.stepanov.bbf.bugfinder.util.findPsi
import com.stepanov.bbf.bugfinder.util.replaceThis
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import org.jetbrains.kotlin.descriptors.ClassKind
import org.jetbrains.kotlin.psi.KtConstructor
import org.jetbrains.kotlin.psi.KtParameter

class AddSecondaryConstructor: BinaryCompatibleTransformation(1) {
    override fun transform(target: FTarget) {
        val file = target.file
        val clazz = file.getAllClassDescriptors()
            .filter { it.kind == ClassKind.CLASS }
            .randomOrNull() ?: return
        ConstructorGenerator(file).generateAndAddSecondaryConstructor(clazz)
    }
}

class AddPrimaryConstructor: BinaryCompatibleTransformation(1) {
    override fun transform(target: FTarget) {
        val file = target.file
        val clazz = file.getAllClassDescriptors()
            .filter { it.kind == ClassKind.CLASS }
            .filter { descr ->
                val primaryConstructor = descr.constructors.find { it.isPrimary }
                if (primaryConstructor == null)
                    true
                else {
                    val psi = primaryConstructor.findPsi() as? KtConstructor<*>
                    psi?.getAllPSIChildrenOfType<KtParameter>()?.all {
                        it.valOrVarKeyword == null
                    } ?: true
                }
            }
            .randomOrNull() ?: return
        ConstructorGenerator(file).generateAndAddPrimaryConstructor(clazz)
    }
}

class ChangePrimaryAndSecondaryConstructors: BinaryCompatibleTransformation(1) {
    override fun transform(target: FTarget) {
        val file = target.file
        val clazz = file.getAllClassDescriptors().filter { descr ->
            val prim = descr.constructors.find { it.isPrimary }?.findPsi() as? KtConstructor<*>
            if (prim == null)
                false
            else {
                descr.constructors.size >= 2 && GConstructor.fromPsi(prim).argsParams.all { it.valOrVar == "" }
            }
        }.randomOrNull() ?: return
        val primaryConstructor = clazz.constructors.find { it.isPrimary }!!
        val secondaryConstructor = clazz.constructors.filter { !it.isPrimary }.random()
        val primPsi = primaryConstructor.findPsi() as? KtConstructor<*> ?: return
        val secondPsi = secondaryConstructor.findPsi() as? KtConstructor<*> ?: return
        val gprim = GConstructor.fromPsi(primPsi)
        val gsec = GConstructor.fromPsi(secondPsi)
        gprim.isPrimary = false
        gsec.isPrimary = true
        gprim.addCallToConstructor(gsec)
        gsec.delegationCalls.clear()
        val newPrim = gsec.toPsi()
        val newSec = gprim.toPsi()
        primPsi.replaceThis(newPrim)
        secondPsi.replaceThis(newSec)
    }
}