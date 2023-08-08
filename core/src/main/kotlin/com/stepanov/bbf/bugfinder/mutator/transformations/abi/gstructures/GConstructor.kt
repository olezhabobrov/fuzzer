package com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures

import com.intellij.psi.PsiElement
import com.stepanov.bbf.reduktor.parser.PSICreator.psiFactory
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import org.jetbrains.kotlin.psi.KtConstructor
import org.jetbrains.kotlin.psi.KtConstructorDelegationCall
import org.jetbrains.kotlin.psi.KtParameter
import org.jetbrains.kotlin.psi.KtPrimaryConstructor

class GConstructor(override var modifiers: MutableList<String> = mutableListOf(),
                   var argsParams: MutableList<GParameter> = mutableListOf(),
                   var body: String = "",
                   var delegationCalls: MutableList<String> = mutableListOf(),
                   var isPrimary: Boolean = false): GStructure() {
    override fun toPsi(): PsiElement {
        return if (isPrimary)
            psiFactory.createPrimaryConstructor(toString())
        else
            psiFactory.createSecondaryConstructor(toString())
    }

    override fun toString(): String {
        val args = argsParams.joinToString(", ")
        val constructorWord = if (isPrimary) "" else "constructor"
        val delegations = if (delegationCalls.isEmpty()) "" else
            ": " + delegationCalls.joinToString(", ")
        return "$constructorWord($args)$delegations $body"
    }

    fun addCallToConstructor(gConstructor: GConstructor) {
        val delegationCall = mutableListOf<String>().also { params ->
            gConstructor.argsParams.forEach {
                params.add("TODO() as ${it.type}")
            }
        }.joinToString(separator = ", ", prefix = "this(", postfix = ")")
        delegationCalls.add(delegationCall)
    }

    companion object {
        fun fromPsi(constructor: KtConstructor<*>): GConstructor {
            val gcon = GConstructor()
            with(gcon) {
                modifiers = constructor.modifierList?.text?.split(" ")?.toMutableList() ?: mutableListOf()
                argsParams = constructor.valueParameters.map { GParameter.fromPsi(it) }.toMutableList()
                delegationCalls = constructor.getAllPSIChildrenOfType<KtConstructorDelegationCall>().map {
                    it.text
                }.filter { it.isNotBlank() }.toMutableList()
                body = constructor.bodyBlockExpression?.text ?: ""
                if (constructor is KtPrimaryConstructor)
                    isPrimary = true
            }
            return gcon
        }
    }

    fun addDefaultToArg(parameter: KtParameter, defaultValue: String) {
        argsParams.find { it.name == parameter.name }!!.defaultValue = defaultValue
    }

}