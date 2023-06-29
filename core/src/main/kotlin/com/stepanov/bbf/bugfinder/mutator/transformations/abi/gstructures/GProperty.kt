package com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures

import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.generator.targetsgenerators.typeGenerators.RandomTypeGenerator
import com.stepanov.bbf.bugfinder.project.BBFFile
import com.stepanov.bbf.reduktor.parser.PSICreator.psiFactory
import org.jetbrains.kotlin.psi.KtProperty
import kotlin.random.Random

class GProperty(
    override var modifiers: MutableList<String> = mutableListOf(),
    var type: String = "",
    var getter: String = "",
    var setter: String = "",
    var valOrVar: String = "",
    var initializer: String = "",
    var name: String = "",
    ) : GStructure() {

    override fun toString(): String {
        val m = modifiers.let { if (it.all { it.isEmpty() }) "" else it.joinToString(" ") }
        val t = if (type.isBlank()) "" else ": $type"
        val initializer = if (initializer.isBlank()) "" else "= $initializer"
        return """
            $m $valOrVar $name $t $initializer
            $getter
            $setter
        """.trimIndent()
    }

    override fun toPsi(): PsiElement {
        return psiFactory.createProperty(toString())
    }

    fun addDefaultValue() {
        if (Random.nextBoolean()) {
            getter = "get() { TODO() }"
            return
        }
        initializer = "TODO()"
    }

    fun removeDefaultValue() {
        initializer = ""
        getter = ""
        setter = ""
    }

    companion object {
        fun fromPsi(property: KtProperty): GProperty {
            val gProperty = GProperty()
            with (gProperty) {
                name = property.name ?: error("wtf why doesn't property have a name?")
                type = property.typeReference?.text ?: ""
                modifiers = property.modifierList?.text?.split(" ")?.toMutableList() ?: mutableListOf()
                getter = property.getter?.text ?: ""
                setter = property.setter?.text ?: ""
                valOrVar = property.valOrVarKeyword.text ?: ""
                initializer = property.initializer?.text ?: ""
            }
            return gProperty
        }
    }

    fun isLateinit() = modifiers.contains("lateinit")

    fun addLateinit() {
        modifiers.add("lateinit")
    }

    fun removeLateinit() {
        modifiers.remove("lateinit")
    }

    fun isConst() = modifiers.contains("const")

    fun addConst() {
        modifiers.add("const")
    }

    fun removeConst() {
        modifiers.remove("const")
    }

    fun isVar() = valOrVar == "var"

    fun makeVal() {
        valOrVar = "val"
    }
}