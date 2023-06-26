package com.stepanov.bbf.bugfinder.mutator.transformations.klib

import com.stepanov.bbf.bugfinder.generator.targetsgenerators.RandomInstancesGenerator
import com.stepanov.bbf.bugfinder.mutator.transformations.FTarget
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GFunction
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GParameter
import com.stepanov.bbf.reduktor.util.replaceThis
import org.jetbrains.kotlin.resolve.BindingContext

class AddDefaultValue: BinaryCompatibleTransformation(1) {

    override fun transform(target: FTarget) {
        val file = target.file
        val functions = target.file.getAllFunctions().filter {
            it.valueParameters.isNotEmpty()
        }
        if (functions.isEmpty())
            return
        val function = functions.random()
        val gfun = GFunction.fromPsi(function)
        val parameterAndType = function.valueParameters.map {
            val type = file.ctx!!.get(BindingContext.TYPE, it.typeReference)
            it to type
        }.filter { it.second != null }
            .filter { !GParameter.fromPsi(it.first).hasDefault() }.randomOrNull()
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