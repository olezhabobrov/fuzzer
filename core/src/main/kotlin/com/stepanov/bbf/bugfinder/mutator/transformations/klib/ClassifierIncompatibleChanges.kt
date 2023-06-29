package com.stepanov.bbf.bugfinder.mutator.transformations.klib

import com.stepanov.bbf.bugfinder.mutator.transformations.FTarget
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GClass
import com.stepanov.bbf.bugfinder.util.getTrue
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import com.stepanov.bbf.reduktor.util.replaceThis
import org.jetbrains.kotlin.psi.KtClass
import kotlin.random.Random

class ClassifierIncompatibleChanges: BinaryIncompatibleTransformation(1) {
    override fun transform(target: FTarget) {
        val file = target.file
        val allClasses = file.psiFile.getAllPSIChildrenOfType<KtClass>()
        if (allClasses.isNotEmpty()) {
            val randomClass = allClasses.random()
            val gclass = GClass.fromPsi(randomClass)
            when (Random.nextInt(121)) {
                in 0..15 -> swapEnum(gclass)
                in 16..30 -> swapAnnotation(gclass)
                in 31..45 -> swapValue(gclass)
                in 46..60 -> swapInner(gclass)
                in 61..75 -> swapInterface(gclass)
                in 76..90 -> swapObject(gclass)
                in 91..105 -> makeAbstract(gclass)
                in 106..120 -> makeSealed(gclass)
            }
            randomClass.replaceThis(gclass.toPsiThrowable())
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