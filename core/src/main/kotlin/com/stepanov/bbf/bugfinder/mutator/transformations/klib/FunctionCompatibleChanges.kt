package com.stepanov.bbf.bugfinder.mutator.transformations.klib

import com.stepanov.bbf.bugfinder.mutator.transformations.FTarget
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GFunction
import com.stepanov.bbf.bugfinder.project.BBFFile
import com.stepanov.bbf.reduktor.util.replaceThis
import kotlin.random.Random

class FunctionCompatibleChanges: BinaryCompatibleTransformation(1) {

    override fun transform(target: FTarget) {
        val file = target.file
        val functions = file.getAllFunctions()
        if (functions.isNotEmpty()) {
            val function = functions.random()
            val gfun = GFunction.fromPsi(function)
            if (Random.nextBoolean())
                swapInfix(gfun)
            if (Random.nextBoolean())
                swapOperator(gfun)
            if (Random.nextBoolean())
                swapTailrec(gfun)
            function.replaceThis(gfun.toPsi())
            if (Random.nextBoolean())
                nonInlineToInline(file)
        }
    }

    private fun nonInlineToInline(file: BBFFile) {
        val functions = file.getAllFunctions().map { it to GFunction.fromPsi(it) }.filter { !it.second.isInline() }
        if (functions.isNotEmpty()) {
            val (function, gfun) = functions.random()
            gfun.addInline()
            function.replaceThis(gfun.toPsi())
        }
    }

    private fun swapTailrec(gfun: GFunction) {
        if (gfun.isTailrec()) {
            gfun.removeTailrec()
        } else {
            gfun.addTailrec()
        }
    }

    private fun swapOperator(gfun: GFunction) {
        if (gfun.isOperator()) {
            gfun.removeOperator()
        } else {
            gfun.addOperator()
        }
    }

    private fun swapInfix(gfun: GFunction) {
        if (gfun.isInfix()) {
            gfun.removeInfix()
        } else {
            gfun.addInfix()
        }
    }
}