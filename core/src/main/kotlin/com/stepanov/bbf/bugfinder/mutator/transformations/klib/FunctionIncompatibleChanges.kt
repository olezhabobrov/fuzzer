package com.stepanov.bbf.bugfinder.mutator.transformations.klib

import com.stepanov.bbf.bugfinder.mutator.transformations.FTarget
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GFunction
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import com.stepanov.bbf.reduktor.util.replaceThis
import org.jetbrains.kotlin.psi.KtFunction

class FunctionIncompatibleChanges: BinaryIncompatibleTransformation(1) {
    override fun transform(target: FTarget) {
        val file = target.file
        val allFunctions = file.psiFile.getAllPSIChildrenOfType<KtFunction>()
        val inlineFunction = allFunctions.filter {
            GFunction.fromPsi(it).isInline()
        }.randomOrNull()
        val nonAbstractFunction = allFunctions.filter {
            val gfun = GFunction.fromPsi(it)
            !gfun.isAbstract()
        }.randomOrNull()
        val functionWithDefaultValue = allFunctions.filter {
            GFunction.fromPsi(it).argsParams.any { it.hasDefault() }
        }.randomOrNull()
        val l = mutableListOf<Pair<KtFunction, (KtFunction) -> Unit>>()
        if (inlineFunction != null) {
            l.add(inlineFunction to {f -> makeNonInline(f)})
        }
        if (nonAbstractFunction != null) {
            l.add(nonAbstractFunction to {f -> makeAbstract(f)})
        }
        if (functionWithDefaultValue != null) {
            l.add(functionWithDefaultValue to {f -> removeDefault(f)})
        }
        val op = l.random()
        op.second(op.first)
    }

    private fun makeNonInline(function: KtFunction?) {
        if (function == null)
            return
        val gfun = GFunction.fromPsi(function)
        gfun.removeInline()
        function.replaceThis(gfun.toPsi())
    }

    private fun makeAbstract(function: KtFunction?) {
        if (function == null)
            return
        val gfun = GFunction.fromPsi(function)
        gfun.addAbstract()
        gfun.removeDefaultImplementation()
        function.replaceThis(gfun.toPsi())
    }

    private fun removeDefault(function: KtFunction?){
        if (function == null)
            return
        val gfun = GFunction.fromPsi(function)
        gfun.argsParams.filter { it.hasDefault() }.random().removeDefaultImplementation()
        function.replaceThis(gfun.toPsi())
    }
}