package com.stepanov.bbf.bugfinder.mutator.transformations.klib

import com.stepanov.bbf.bugfinder.mutator.transformations.FTarget
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GClass
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GFunction
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GProperty
import com.stepanov.bbf.bugfinder.util.getRandomVariableName
import com.stepanov.bbf.reduktor.parser.PSICreator.psiFactory
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import com.stepanov.bbf.reduktor.util.replaceThis
import org.jetbrains.kotlin.psi.*
import kotlin.random.Random

class RenameTypeParameter: BinaryCompatibleTransformation(1) {
    override fun transform(target: FTarget) {
        val file = target.file.psiFile
        val allParameterizedClasses = file.getAllPSIChildrenOfType<KtClass>().filter { it.typeParameters.isNotEmpty() }
        val allParameterizedFunctions = file.getAllPSIChildrenOfType<KtFunction>().filter { it.typeParameters.isNotEmpty() }

        if (allParameterizedFunctions.isNotEmpty() && Random.nextBoolean()) {
            val function = allParameterizedFunctions.random()
            changeParameterInFunction(function)
            return
        }
        if (allParameterizedClasses.isNotEmpty()) {
            changeParameterInClass(allParameterizedClasses.random())
        }
    }

    private fun changeParameterInClass(clazz: KtClass, oldTypeParam: String? = null, newTypeParam: String? = null) {
        val gClass = GClass.fromPsi(clazz)
        val oldTypePar = if (oldTypeParam == null) gClass.typeParams.random()
            else oldTypeParam
        val newTypePar = if (newTypeParam == null) Random.getRandomVariableName(1).toUpperCase()
            else newTypeParam
        gClass.typeParams = gClass.typeParams.map {
            changeParameterInType(it, oldTypePar, newTypePar)
        }
        gClass.supertypes = gClass.supertypes.map {
            changeParameterInType(it, oldTypePar, newTypePar)
//            val type = it.substringAfter("<").substringBeforeLast(">")
//            val newType = String(type.toCharArray()).replace(oldTypeParam, newTypePar)
//            it.replace(type, newType)
        }.toMutableList()
        val newClass = gClass.toPsiThrowable()
        newClass.getAllPSIChildrenOfType<KtFunction>().forEach {
            changeParameterInFunction(it, oldTypePar, newTypePar)
        }
        newClass.getAllPSIChildrenOfType<KtProperty>().forEach {
            changeParameterInProperty(it, oldTypePar, newTypePar)
        }
        newClass.getAllPSIChildrenOfType<KtClass>().forEach {
            changeParameterInClass(it, oldTypePar, newTypePar)
        }
        clazz.replaceThis(newClass)
    }

    private fun changeParameterInProperty(property: KtProperty, oldPar: String, newPar: String) {
        val gprop = GProperty.fromPsi(property)
        gprop.type = changeParameterInType(gprop.type, oldPar, newPar)
        property.replaceThis(gprop.toPsi())
    }

    private fun changeParameterInType(type: String, oldPar: String, newPar: String): String {
//        if (type == oldPar) {
//            return newPar
//        }
//        val parameterType = type.substringAfter("<").substringBeforeLast(">")
//        val newParameterType = String(type.toCharArray()).replace(oldTypeParam, newTypePar)
//        it.replace(type, newType)
        return type.replace(oldPar, newPar)
    }

    private fun changeParameterInFunction(function: KtFunction, oldTypeParam: String? = null, newTypeParam: String? = null) {
        val gfun = GFunction.fromPsi(function)
        val oldTypePar = if (oldTypeParam == null)
            gfun.typeArgs.random()
        else oldTypeParam
        val newTypePar = if (newTypeParam == null)
            Random.getRandomVariableName(1).toUpperCase()
        else newTypeParam
        if (function is KtConstructor<*>) {
            var newConstructor = function.valueParameters.map {
                val valOrVar = it.valOrVarKeyword?.text ?: ""
                val newType = if (it.typeReference != null)
                        ": ${changeParameterInType(it.typeReference!!.text, oldTypePar, newTypePar)}"
                    else ""
                "$valOrVar ${it.name!!}$newType"
            }.joinToString(", ", prefix = "(", postfix = ")")
            if (function.hasConstructorKeyword()) {
                newConstructor = "constructor$newConstructor"
            }
            val psiConstructor = psiFactory.createPrimaryConstructor(newConstructor)
            function.replaceThis(psiConstructor)
            return
        }

        gfun.typeArgs = gfun.typeArgs.map {
            changeParameterInType(it, oldTypePar, newTypePar)
        }
        gfun.setArgs(function.valueParameters.map {
            if (it.typeReference != null) {
                val newType = changeParameterInType(it.typeReference!!.text, oldTypePar, newTypePar)
                "${it.name}: $newType"
            } else {
                it.text
            }
        })
        gfun.rtvType = changeParameterInType(gfun.rtvType, oldTypePar, newTypePar)
        val newFunction = gfun.toPsi()
        function.replaceThis(newFunction)
    }
}