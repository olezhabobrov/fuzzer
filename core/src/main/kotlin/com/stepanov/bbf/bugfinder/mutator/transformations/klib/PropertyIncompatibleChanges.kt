package com.stepanov.bbf.bugfinder.mutator.transformations.klib

import com.stepanov.bbf.bugfinder.mutator.transformations.FTarget
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GProperty
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import com.stepanov.bbf.reduktor.util.replaceThis
import org.jetbrains.kotlin.psi.KtProperty

class PropertyIncompatibleChanges: BinaryIncompatibleTransformation(1) {
    override fun transform(target: FTarget) {
        val file = target.file
        val allProperies = file.psiFile.getAllPSIChildrenOfType<KtProperty>()
        val constProperty = allProperies.filter {
            GProperty.fromPsi(it).isConst()
        }.randomOrNull()
        val inlineProperty = allProperies.filter {
            GProperty.fromPsi(it).isInline()
        }.randomOrNull()
        val mutableProperty = allProperies.filter {
            GProperty.fromPsi(it).isVar()
        }.randomOrNull()
        val openValProperty = allProperies.filter {
            val gprop = GProperty.fromPsi(it)
            gprop.isOpen() && gprop.isVal()
        }.randomOrNull()
        val l = mutableListOf<Pair<KtProperty, ((KtProperty) -> Unit)>>()
        if (constProperty != null) {
            l.add(constProperty to {p ->
                val newProp = GProperty.fromPsi(p).also {it.removeConst()}.toPsi()
                constProperty.replaceThis(newProp)
            })
        }
        if (inlineProperty != null) {
            l.add(inlineProperty to {p ->
                val newProp = GProperty.fromPsi(p).also {it.removeInline()}.toPsi()
                inlineProperty.replaceThis(newProp)
            })
        }
        if (mutableProperty != null) {
            l.add(mutableProperty to {p ->
                val newProp = GProperty.fromPsi(p).also {it.makeVal()}.toPsi()
                mutableProperty.replaceThis(newProp)
            })
        }
        if (openValProperty != null) {
            l.add(openValProperty to {p ->
                val newProp = GProperty.fromPsi(p).also {it.makeVar()}.toPsi()
                openValProperty.replaceThis(newProp)
            })
        }
        val op = l.random()
        op.second(op.first)
    }
}