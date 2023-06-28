package com.stepanov.bbf.bugfinder.mutator.transformations.klib

import com.stepanov.bbf.bugfinder.mutator.transformations.FTarget
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GClass
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GFunction
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import org.jetbrains.kotlin.psi.KtClass
import kotlin.random.Random

class ClassifierIncompatibleChanges: BinaryIncompatibleTransformation(1) {
    override fun transform(target: FTarget) {
        val file = target.file
        val allClasses = file.psiFile.getAllPSIChildrenOfType<KtClass>()
        if (allClasses.isNotEmpty()) {
            val randomClass = allClasses.random()
            val gclass = GClass.fromPsi(randomClass)
            if (Random.nextBoolean())
                swapEnum(gclass)
            if (Random.nextBoolean())
                swapAnnotation(gclass)
            if (Random.nextBoolean())
                swapValue(gclass)
            if (Random.nextBoolean())
                swapInner(gclass)
            if (Random.nextBoolean())
                swapInterface(gclass)
            if (Random.nextBoolean())
                swapObject(gclass)
            if (Random.nextBoolean())
                makeAbstract(gclass)
            if (Random.nextBoolean())
                makeSealed(gclass)
        }
    }

    private fun swapEnum(gclass: GClass) {
        if (gclass.isEnum()) {
            gclass.removeEnum()
        } else {
            gclass.addEnum()
        }
    }

    private fun swapAnnotation(gclass: GClass) {
        if (gclass.isAnnotation()) {
            gclass.removeAnnotation()
        } else {
            gclass.addAnnotation()
        }
    }


    private fun swapValue(gclass: GClass) {
        if (gclass.isValue()) {
            gclass.removeValue()
        } else {
            gclass.addValue()
        }
    }


    private fun swapInterface(gclass: GClass) {
        if (gclass.isInterface()) {
            gclass.classWord = "class"
        } else {
            gclass.classWord = "interface"
        }
    }


    private fun swapObject(gclass: GClass) {
        if (gclass.isObject()) {
            gclass.classWord = "class"
        } else {
            gclass.classWord = "object"
        }
    }


    private fun swapInner(gclass: GClass) {
        if (gclass.isInner()) {
            gclass.removeInner()
        } else {
            gclass.addInner()
        }
    }


    private fun makeAbstract(gclass: GClass) {
        if (!gclass.isAbstract()) {
            gclass.addAbstract()
        }
    }


    private fun makeSealed(gclass: GClass) {
        if (!gclass.isSealed()) {
            gclass.addSealed()
        }
    }
}