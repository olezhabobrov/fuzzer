package com.stepanov.bbf.bugfinder.mutator.transformations.klib

import com.stepanov.bbf.bugfinder.generator.targetsgenerators.ClassInvocator
import com.stepanov.bbf.bugfinder.mutator.transformations.FTarget
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GConstructor
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GFunction
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import com.stepanov.bbf.reduktor.util.replaceThis
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.psi.KtConstructor
import org.jetbrains.kotlin.psi.KtFunction
import org.jetbrains.kotlin.psi.psiUtil.isPublic
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
            val descriptor = type?.constructor?.declarationDescriptor as? ClassDescriptor ?: return
            val generatedValue = ClassInvocator(file).randomClassInvocation(descriptor)
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
        }.filter { it.second != null }.randomOrNull() ?: return
        val (parameter, type) = parameterAndType
        val descriptor = type?.constructor?.declarationDescriptor as? ClassDescriptor ?: return
        val generatedValue = ClassInvocator(file).randomClassInvocation(descriptor)
        if (generatedValue.isBlank())
            return
        gfun.addDefaultToArg(parameter, generatedValue)
        val newFunction = gfun.toPsi()
        function.replaceThis(newFunction)
    }
}

class RemoveDefaultValue: BinaryIncompatibleTransformation(1) {
    override fun transform(target: FTarget) {
        val file = target.file
        val (func, gStructure) = file.psiFile.getAllPSIChildrenOfType<KtFunction>()
            .filter { it.isPublic }
            .map { it to (
                if (it is KtConstructor<*>)
                    GConstructor.fromPsi(it)
                else
                    GFunction.fromPsi(it))
            }.filter {
                val x = it.second
                if (x is GFunction)
                    x.argsParams.any { it.hasDefault() }
                else if (x is GConstructor)
                    x.argsParams.any { it.hasDefault() }
                else false
            }.randomOrNull() ?: return
        if (gStructure is GFunction) {
            gStructure.argsParams.filter { it.hasDefault() }.random().defaultValue = ""
        }
        if (gStructure is GConstructor) {
            gStructure.argsParams.filter { it.hasDefault() }.random().defaultValue = ""
        }
        val newElement = gStructure.toPsi() ?: return
        func.replaceThis(newElement)
    }

}