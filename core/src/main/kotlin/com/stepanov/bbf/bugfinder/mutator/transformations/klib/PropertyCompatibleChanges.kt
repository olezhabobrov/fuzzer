package com.stepanov.bbf.bugfinder.mutator.transformations.klib

import com.stepanov.bbf.bugfinder.mutator.transformations.FTarget
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GProperty
import com.stepanov.bbf.reduktor.util.replaceThis
import kotlin.random.Random

class PropertyCompatibleChanges: BinaryCompatibleTransformation(1) {
    override fun transform(target: FTarget) {
        val file = target.file
        val properties = file.getAllProperties()
        if (properties.isNotEmpty()) {
            val property = properties.random()
            val gprop = GProperty.fromPsi(property)
            if (Random.nextBoolean())
                swapLateinit(gprop)
            if (Random.nextBoolean())
                makeConst(gprop)
            if (Random.nextBoolean())
                makeInline(gprop)
            if (Random.nextBoolean())
                makeVal(gprop)
            property.replaceThis(gprop.toPsi())
        }
    }

    private fun swapLateinit(gProperty: GProperty) {
        if (gProperty.isLateinit()) {
            gProperty.removeLateinit()
        } else {
            gProperty.addLateinit()
        }
    }

    private fun makeConst(gProperty: GProperty) {
        if (!gProperty.isConst())
            gProperty.addConst()
    }

    private fun makeInline(gProperty: GProperty) {
        if (!gProperty.isInline())
            gProperty.addInline()
    }

    private fun makeVal(gProperty: GProperty) {
        if (!gProperty.isOpen() && gProperty.isVal()) {
            gProperty.makeVar()
        }
    }
}