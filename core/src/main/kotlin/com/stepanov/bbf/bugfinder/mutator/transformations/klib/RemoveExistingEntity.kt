package com.stepanov.bbf.bugfinder.mutator.transformations.klib

import com.stepanov.bbf.bugfinder.mutator.transformations.FTarget
import com.stepanov.bbf.reduktor.parser.PSICreator.psiFactory
import com.stepanov.bbf.reduktor.util.replaceThis

class RemoveExistingEntity: BinaryIncompatibleTransformation(1) {
    override fun transform(target: FTarget) {
        val file = target.file
        val allEntities = file.getAllEntities()
        val randomEntity = allEntities.random()
        randomEntity.replaceThis(psiFactory.createWhiteSpace())
    }
}