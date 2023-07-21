package com.stepanov.bbf.bugfinder.generator.targetsgenerators

import com.stepanov.bbf.bugfinder.project.BBFFile
import com.stepanov.bbf.bugfinder.util.generateDefValuesAsString
import com.stepanov.bbf.bugfinder.util.isPrimitiveTypeOrNullablePrimitiveTypeOrString
import org.jetbrains.kotlin.descriptors.ClassConstructorDescriptor
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.descriptors.ClassKind
import org.jetbrains.kotlin.descriptors.Modality

class ClassInvocator(val file: BBFFile) {

    fun randomClassInvocation(descriptor: ClassDescriptor, depth: Int = 0): String =
        if (depth >= DEPTH)
            ""
        else
            file.findImplementation(descriptor.defaultType) ?:
            generateInstances(descriptor, depth).random()

    fun generateInstances(descriptor: ClassDescriptor, depth: Int = 0): List<String> {
        val type = descriptor.defaultType
        val primitiveValue = generateDefValuesAsString(type.toString())
        if (primitiveValue.isNotBlank())
            return listOf(primitiveValue)

//        descriptor.defaultType.isPrimitiveType()
//        descriptor.defaultType
//        descriptor.modality
//        descriptor.kind
        when (descriptor.kind) {
            ClassKind.CLASS -> {
                return generateInstancesOfClass(descriptor, depth)
            }
            ClassKind.OBJECT -> return listOf(descriptor.name.asString())
            else -> TODO()
        }

    }

    fun generateInstancesOfClass(descriptor: ClassDescriptor, depth: Int = 0): List<String> {
        return when (descriptor.modality) {
            Modality.FINAL ->
                invokeAllConstructors(descriptor, depth)
//            Modality.ABSTRACT ->
//                extendClass(descriptor)
//            Modality.OPEN ->
//                invokeAllConstructors(descriptor) + extendClass(descriptor)
            else -> TODO()
//            Modality.SEALED ->
//                listOf() // not interested in extending sealed classes in klib
        }
    }

    private fun invokeAllConstructors(descriptor: ClassDescriptor, depth: Int): List<String> =
        descriptor.constructors.flatMap { invokeConstructor(descriptor, it, depth) }

    private fun invokeConstructor(descriptor: ClassDescriptor,
                                  constructor: ClassConstructorDescriptor,
                                  depth: Int): List<String> {
        val parameters = FunInvocator(file).invokeParameterBrackets(constructor, depth)
        return parameters.map { "${descriptor.name}$it" }
//        constructor.valueParameters.forEach {
//            it.hasDefaultValue()
//        }
//        constructor.typeParameters // generate types
//        constructor.valueParameters // generate instances
//        TODO()
    }

    private fun extendClass(descriptor: ClassDescriptor): List<String> {
        TODO()
    }

    private fun generateAnonObjects(descriptor: ClassDescriptor): List<String> {
        TODO()
    }



    private val DEPTH = 10
}