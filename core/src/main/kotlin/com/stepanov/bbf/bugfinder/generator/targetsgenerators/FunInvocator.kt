package com.stepanov.bbf.bugfinder.generator.targetsgenerators

import com.stepanov.bbf.bugfinder.project.BBFFile
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.descriptors.FunctionDescriptor

class FunInvocator(val file: BBFFile) {

    fun generateParametersWOdefault(descriptor: FunctionDescriptor, depth: Int = 0): String {
        TODO()
    }

    // returns parameterName to value
    fun generateValueParameters(descriptor: FunctionDescriptor, depth: Int = 0): Map<String, String> =
        descriptor.valueParameters.associate { parameter ->
            val descr = parameter.type.constructor.declarationDescriptor as? ClassDescriptor
            if (descr == null)
                return mapOf()
            parameter.name.toString() to ClassInvocator(file).randomClassInvocation(descr, depth + 1)
        }

    fun invokeParameterBrackets(descriptor: FunctionDescriptor, depth: Int = 0): List<String> {
        val values = generateValueParameters(descriptor, depth)
        val usual = descriptor.valueParameters.joinToString(prefix = "(", postfix = ")", separator = ",") { parameter ->
            val name = parameter.name.toString()
            val value = values[name]
            if (value == null)
                error("Shouldn't be null")
            value
        }
        // TODO: smth with default values
        return listOf(usual)
    }

}