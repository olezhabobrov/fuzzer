package com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures

import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.util.getRandomVariableName
import com.stepanov.bbf.reduktor.parser.PSICreator.psiFactory
import org.jetbrains.kotlin.psi.KtFunction
import kotlin.random.Random

data class GFunction(
    override var modifiers: MutableList<String> = mutableListOf(),
    var typeArgs: List<String> = listOf(),
    var extensionReceiver: String = "",
    var name: String = Random.getRandomVariableName(5),
    var args: List<String> = listOf(),
    var rtvType: String = "",
    var body: String = ""
): GStructure() {
    override fun toPsi(): PsiElement {
        val m = modifiers.let { if (it.all { it.isEmpty() }) "" else it.joinToString(" ") }
        val e = if (extensionReceiver.isEmpty()) "" else " $extensionReceiver."
        val sta = if (typeArgs.isEmpty()) "" else typeArgs.joinToString(prefix = "<", postfix = "> ")
        val strArgs = args.joinToString()
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
                rtvType = function.typeReference?.text ?: ""
                body =
                    when {
                        function.bodyBlockExpression == null -> ""
                        function.bodyBlockExpression!!.text.trim().startsWith("{") ->
                            function.bodyBlockExpression!!.text
                                .substringAfter('{').substringBeforeLast('}')
                        else -> function.bodyBlockExpression!!.text
                    }
            }
            return gfun
        }
    }
}