package com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures

import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.util.getRandomVariableName
import com.stepanov.bbf.reduktor.parser.PSICreator.psiFactory
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.psi.KtFunction
import org.jetbrains.kotlin.psi.KtParameter
import kotlin.random.Random

data class GFunction(
    override var modifiers: MutableList<String> = mutableListOf(),
    var typeArgs: List<String> = listOf(),
    var extensionReceiver: String = "",
    var name: String = Random.getRandomVariableName(5),
    var args: List<String> = listOf(),
    var argsParams: MutableList<GParameter> = mutableListOf(),
    var rtvType: String = "",
    var body: String = ""
): GStructure() {
    override fun toPsi(): PsiElement {
        val m = modifiers.let { if (it.all { it.isEmpty() }) "" else it.joinToString(" ") }
        val e = if (extensionReceiver.isEmpty()) "" else " $extensionReceiver."
        val sta = if (typeArgs.isEmpty()) "" else typeArgs.joinToString(prefix = "<", postfix = "> ")
        val strArgs = argsParams.joinToString(", ")
        val rt = if (rtvType.isNotBlank())
            ": $rtvType"
        else
            ""
        return psiFactory.createFunction("$m fun $sta $e$name($strArgs)$rt $body")
    }

    companion object {
        fun fromPsi(function: KtFunction): GFunction {
            val gfun = GFunction()
            with (gfun) {
                modifiers = function.modifierList?.text?.split(" ")?.toMutableList() ?: mutableListOf()
                typeArgs = function.typeParameters.let { if (it.isEmpty()) listOf() else it.map { it.text } }
                // TODO: extensionReceiver = function.????
                name = function.name ?: ""
                args = function.valueParameters.let { if (it.isEmpty()) listOf() else it.map { it.text } }
                argsParams = function.valueParameters.let { it.map { GParameter.fromPsi(it) } }.toMutableList()
                rtvType = function.typeReference?.text ?: ""
                body =
                    when {
                        function.bodyBlockExpression == null -> ""
                        else -> function.bodyBlockExpression!!.text
                    }
            }
            return gfun
        }
    }

    fun addDefaultToArg(parameter: KtParameter, defaultValue: String) {
        argsParams.find { it.name == parameter.name }!!.defaultValue = defaultValue
        updateArgs()
    }

    fun updateArgs() {
        args = argsParams.map { it.toString() }
    }

    fun isTailrec() = modifiers.contains("tailrec")

    fun removeTailrec() {
        modifiers.remove("tailrec")
    }

    fun addTailrec() {
        modifiers.add("tailrec")
    }

    fun isOperator() = modifiers.contains("operator")

    fun removeOperator() = modifiers.remove("operator")

    fun addOperator() = modifiers.add("operator")

    fun isInfix() = modifiers.contains("infix")

    fun removeInfix() {
        modifiers.remove("infix")
    }

    fun addInfix() {
        modifiers.add("infix")
    }

}