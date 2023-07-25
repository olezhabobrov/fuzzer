package com.stepanov.bbf.bugfinder.generator.targetsgenerators

import com.stepanov.bbf.bugfinder.project.BBFFile
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.descriptors.FunctionDescriptor
import org.jetbrains.kotlin.descriptors.containingPackage
import org.jetbrains.kotlin.resolve.calls.components.hasDefaultValue
import org.jetbrains.kotlin.types.isNullable

class FunInvocator(val file: BBFFile) {

    fun invokeFunction(descriptor: FunctionDescriptor, depth: Int = 0): List<String> {
        val invocations = invokeParameterBrackets(descriptor).map { valueParameters ->
            "${descriptor.name.asString()}$valueParameters"
        }
        val parent = descriptor.containingDeclaration
        if (parent is ClassDescriptor) {
            val parentInv = ClassInvocator(file).randomClassInvocation(parent)
            return invocations.map { "${parentInv}.$it" }
        } else {
            return invocations
        }
    }

    fun invokeParameterBrackets(descriptor: FunctionDescriptor, depth: Int = 0): List<String> {
        val values = generateValueParameters(descriptor, depth)
        if (descriptor.valueParameters.any { values[it.name.asString()] == null }) {
            return listOf()
        }

        val results = mutableListOf<List<String>>()
        results.add(descriptor.valueParameters.map { parameter ->
            val name = parameter.name.toString()
            values[name]!!
        })
        if (descriptor.valueParameters.any { it.hasDefaultValue() }) {
            results.add(
                descriptor.valueParameters.filter {
                    !it.hasDefaultValue()
                }.map {  parameter ->
                    val value = values[parameter.name.asString()]
                    "${parameter.name}=$value"
                }.shuffled()
            )
        }
        if (descriptor.valueParameters.any { it.type.isNullable() }) {
            results.add(
                descriptor.valueParameters.map { parameter ->
                    if (parameter.type.isNullable())
                        "null"
                    else
                        values[parameter.name.asString()]!!
                }
            )
        }
        return results.map { it.joinToString(prefix = "(", postfix = ")", separator = ",") }
    }

    // returns parameterName to value
    fun generateValueParameters(descriptor: FunctionDescriptor, depth: Int = 0): Map<String, String> =
        descriptor.valueParameters.associate { parameter ->
            val descr = parameter.type.constructor.declarationDescriptor as? ClassDescriptor
            if (descr == null)
                return mapOf()
            parameter.name.toString() to ClassInvocator(file).randomClassInvocation(descr, depth + 1)
        }

}