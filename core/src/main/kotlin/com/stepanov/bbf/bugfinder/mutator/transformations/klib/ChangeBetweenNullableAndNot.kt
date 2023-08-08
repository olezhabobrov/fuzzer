package com.stepanov.bbf.bugfinder.mutator.transformations.klib

import com.stepanov.bbf.bugfinder.mutator.transformations.FTarget
import com.stepanov.bbf.bugfinder.util.getAllPSIChildrenOfType
import com.stepanov.bbf.bugfinder.util.replaceThis
import com.stepanov.bbf.reduktor.parser.PSICreator.psiFactory
import org.jetbrains.kotlin.psi.KtParameter

class MakeParameterNotNullable: BinaryIncompatibleTransformation(1) {
    override fun transform(target: FTarget) {
        val file = target.file
        val parameter = file.psiFile.getAllPSIChildrenOfType<KtParameter>()
            .filter {
                it.typeReference?.text?.contains("?") ?: false
            }.randomOrNull() ?: return
        val newParameter = psiFactory.createType(parameter.typeReference!!
            .text.replaceFirst("?", ""))
        parameter.typeReference!!.replaceThis(newParameter)
    }
}

class MakeParameterNullable: BinaryIncompatibleTransformation(1) {
    override fun transform(target: FTarget) {
        val file = target.file
        val parameter = file.psiFile.getAllPSIChildrenOfType<KtParameter>()
            .filter {
                it.typeReference != null
            }
            .filter {
                it.typeReference!!.text?.last() != '?'
            }.randomOrNull() ?: return
        val newParameter = psiFactory.createType(parameter.typeReference!!
            .text + "?")
        parameter.typeReference!!.replaceThis(newParameter)
    }
}