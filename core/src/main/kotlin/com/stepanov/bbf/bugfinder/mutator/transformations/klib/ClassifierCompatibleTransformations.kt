package com.stepanov.bbf.bugfinder.mutator.transformations.klib

import com.stepanov.bbf.bugfinder.mutator.transformations.FTarget
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GClass
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GStructure
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import com.stepanov.bbf.reduktor.util.replaceThis
import org.jetbrains.kotlin.psi.*
import kotlin.random.Random

class ClassifierCompatibleTransformations: BinaryCompatibleTransformation(1) {
    override fun transform(target: FTarget) {
        val file = target.file
        if (Random.nextBoolean())
            makeClassDataClass(file.psiFile)
        if (Random.nextBoolean())
            fromAbstractToOpen(file.psiFile)
        if (Random.nextBoolean())
            changeSupertypeOrder(file.psiFile)

    }

    private fun makeClassDataClass(file: KtFile) {
        val (clazz, gclass) = file.getAllPSIChildrenOfType<KtClass>().map { it to GClass.fromPsi(it) }
            .filter { !it.second.canBeExtended() && !it.second.isData() }
            .filter {
                val b = it.first.primaryConstructor?.valueParameters?.all { it.hasValOrVar() }
                val c = it.first.primaryConstructor?.valueParameters?.isNotEmpty()
                // cringe haha
                // thanks God nobody's gonna see that
                if (c == null) {
                    false
                } else
                    b != null && b && c
            }.randomOrNull() ?: return
        gclass.addData()
        val newClass = gclass.toPsiThrowable()
        clazz.replaceThis(newClass)
    }

    private fun changeSupertypeOrder(file: KtFile) {
        file.getAllPSIChildrenOfType<KtClassOrObject>().map { it to GClass.fromPsi(it) }
            .filter { it.second.supertypes.size > 1 }
            .map {
                it.second.supertypes.shuffle()
                it
            }.forEach {
                it.first.replaceThis(it.second.toPsiThrowable())
            }
    }

    private fun fromAbstractToOpen(file: KtFile) {
        val allAbstractClasses = file.getAllPSIChildrenOfType<KtClass>().filter {
            val gclass = GClass.fromPsi(it)
            gclass.isSealed() || gclass.isAbstract()
        }
        if (allAbstractClasses.isNotEmpty()) {
            val clazz = allAbstractClasses.random()
            (clazz.getAllPSIChildrenOfType<KtNamedFunction>() +
                    clazz.getAllPSIChildrenOfType<KtProperty>()).map {
                it to GStructure.fromPsi(it)
            }.filter { it.second.isAbstract() }.forEach {
                it.second.removeAbstract()
                it.second.addDefaultImplementation()
                it.second.addOpen()
                val newEntity = it.second.toPsi()
                if (newEntity != null)
                    it.first.replaceThis(newEntity)
            }
            val newClass = GClass.fromPsi(clazz).also {
                it.removeAbstract()
                it.addOpen()
            }.toPsiThrowable()
            clazz.replaceThis(newClass)
        }
    }
}