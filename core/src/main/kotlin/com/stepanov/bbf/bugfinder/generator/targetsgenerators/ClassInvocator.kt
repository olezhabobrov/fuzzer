package com.stepanov.bbf.bugfinder.generator.targetsgenerators

import com.stepanov.bbf.bugfinder.project.BBFFile
import com.stepanov.bbf.bugfinder.util.generateDefValuesAsString
import org.jetbrains.kotlin.descriptors.*

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

        return when (descriptor.kind) {
            ClassKind.CLASS -> {
                generateInstancesOfClass(descriptor, depth)
            }
            ClassKind.OBJECT -> listOf(descriptor.name.asString())
            ClassKind.INTERFACE -> implementOpenMembers(descriptor, depth)
            else -> TODO()
        }
    }

    fun implementOpenMembers(descriptor: ClassDescriptor, depth: Int = 0): List<String> =
        ClassImplementer(file).allImplementationsOfClasses(listOf(descriptor), depth)

    fun generateInstancesOfClass(descriptor: ClassDescriptor, depth: Int = 0): List<String> {
        return when (descriptor.modality) {
            Modality.FINAL ->
                invokeAllConstructors(descriptor, depth)
            Modality.ABSTRACT ->
                implementOpenMembers(descriptor)
            Modality.OPEN ->
                invokeAllConstructors(descriptor, depth) + implementOpenMembers(descriptor, depth)
            Modality.SEALED ->
                listOf() // not interested in extending sealed classes in klib
        }
    }

    private fun invokeAllConstructors(descriptor: ClassDescriptor, depth: Int): List<String> =
        descriptor.constructors.filter { it.visibility == DescriptorVisibilities.PUBLIC }
            .flatMap { invokeConstructor(descriptor, it, depth) }

    private fun invokeConstructor(descriptor: ClassDescriptor,
                                  constructor: ClassConstructorDescriptor,
                                  depth: Int): List<String> {
        val parameters = FunInvocator(file).invokeParameterBrackets(constructor, depth)
        return parameters.map { "${descriptor.name}$it" }
    }


    private val DEPTH = 10
}