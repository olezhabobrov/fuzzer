package com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures

import com.intellij.psi.PsiElement
import com.stepanov.bbf.reduktor.parser.PSICreator.psiFactory
import org.jetbrains.kotlin.descriptors.ParameterDescriptor
import org.jetbrains.kotlin.descriptors.ValueParameterDescriptor
import org.jetbrains.kotlin.js.descriptorUtils.getJetTypeFqName
import org.jetbrains.kotlin.psi.KtParameter

class GParameter(
    override var modifiers: MutableList<String> = mutableListOf(),
    var name: String = "",
    var type: String = "",
    var valOrVar: String = "",
    var defaultValue: String = ""

) : GStructure() {

    override fun toString(): String {
        val m = modifiers.let { if (it.all { it.isEmpty() }) "" else (it.joinToString(" ") + " ") }
        val t = if (type.isBlank()) "" else ": $type"
        val dv = if (defaultValue.isBlank()) "" else " = $defaultValue"
        val vv = if (valOrVar.isBlank()) "" else "$valOrVar "
        return "$m$vv$name$t$dv"
    }

    override fun toPsi(): PsiElement {
        return psiFactory.createParameter(toString())
    }

    fun hasDefault() = defaultValue.isNotBlank()

    companion object {
        fun fromPsi(property: KtParameter): GParameter {
            val parameter = GParameter()
            with (parameter) {
                name = property.name ?: error("wtf why doesn't parameter have a name?")
                type = property.typeReference?.text ?: ""
                modifiers = property.modifierList?.text?.split(" ")?.toMutableList() ?: mutableListOf()
                valOrVar = property.valOrVarKeyword?.text ?: ""
                defaultValue = property.defaultValue?.text ?: ""
            }
            return parameter
        }

        fun fromString(parameter: String): GParameter {
            val gparam = GParameter()
            with (gparam) {
                name = parameter
                    .substringBefore(":")
                    .substringAfterLast(" ")
                type = parameter
                    .substringAfterLast(":")
                    .replace(" ", "")
                modifiers = parameter
                    .substringBefore(":")
                    .split(" ")
                    .filter {
                        it.isNotBlank() &&
                                !it.contains("var") &&
                                !it.contains("val")
                    }.dropLast(1).toMutableList()
                valOrVar = when {
                    parameter.contains("val") -> "val"
                    parameter.contains("var") -> "var"
                    else -> ""
                }
                defaultValue = parameter
                    .substringAfterLast("=")
                    .replace(" ", "")
            }
            return gparam
        }

        fun fromDescriptor(parameter: ValueParameterDescriptor): GParameter {
            val gparameter = GParameter()
            with (gparameter) {
                name = parameter.name.asString()
                type = parameter.type.getJetTypeFqName(true)
//                modifiers = property.modifierList?.text?.split(" ")?.toMutableList() ?: mutableListOf()
//                valOrVar
//                parameter.def
            //                defaultValue = property.defaultValue?.text ?: ""
            }
            return gparameter
        }
    }
}