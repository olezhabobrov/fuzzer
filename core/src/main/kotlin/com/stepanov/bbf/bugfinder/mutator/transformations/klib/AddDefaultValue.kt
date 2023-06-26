package com.stepanov.bbf.bugfinder.mutator.transformations.klib

import com.stepanov.bbf.bugfinder.generator.targetsgenerators.RandomInstancesGenerator
import com.stepanov.bbf.bugfinder.mutator.transformations.FTarget
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GConstructor
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GFunction
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import com.stepanov.bbf.reduktor.util.replaceThis
import org.jetbrains.kotlin.psi.KtConstructor
import org.jetbrains.kotlin.resolve.BindingContext
import kotlin.random.Random

class AddDefaultValue: BinaryCompatibleTransformation(1) {

    override fun transform(target: FTarget) {
        val file = target.file
        val constructors = file.psiFile.getAllPSIChildrenOfType<KtConstructor<*>>().filter {
            it.valueParameters.isNotEmpty()
        }
        if (constructors.isEmpty())
            return
        if (Random.nextBoolean()) {
            val constructor = constructors.random()
            val gcon = GConstructor.fromPsi(constructor)
            val parameterAndType = constructor.valueParameters.map {
                val type = file.ctx!!.get(BindingContext.TYPE, it.typeReference)
                it to type
            }.filter { it.second != null }.randomOrNull()
            if (parameterAndType == null)
                return
            val (parameter, type) = parameterAndType
            val generatedValue = RandomInstancesGenerator(file).generateValueOfType(type!!)
            if (generatedValue.isBlank())
                return
            gcon.addDefaultToArg(parameter, generatedValue)
            val newCon = gcon.toPsi()
            constructor.replaceThis(newCon)
            return
        }
        val functions = file.getAllFunctions().filter {
            it.valueParameters.isNotEmpty()
        }
        if (functions.isEmpty())
            return
        val function = functions.random()
        val gfun = GFunction.fromPsi(function)
        val parameterAndType = function.valueParameters.map {
            val type = file.ctx!!.get(BindingContext.TYPE, it.typeReference)
            it to type
        }.filter { it.second != null }.randomOrNull()
        if (parameterAndType == null)
            return
        val (parameter, type) = parameterAndType
        val generatedValue = RandomInstancesGenerator(file).generateValueOfType(type!!)
        if (generatedValue.isBlank())
            return
        gfun.addDefaultToArg(parameter, generatedValue)
        val newFunction = gfun.toPsi()
        function.replaceThis(newFunction)
    }

}