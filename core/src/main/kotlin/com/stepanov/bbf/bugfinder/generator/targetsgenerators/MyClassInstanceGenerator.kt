package com.stepanov.bbf.bugfinder.generator.targetsgenerators

import com.stepanov.bbf.bugfinder.project.BBFFile
import com.stepanov.bbf.bugfinder.util.isAbstractClass
import org.jetbrains.kotlin.descriptors.*
import org.jetbrains.kotlin.psi.KtExpression

class MyClassInstanceGenerator(file: BBFFile) {

    fun generateInstances(descriptor: ClassDescriptor): List<KtExpression> {
        descriptor.modality
        descriptor.kind
        when (descriptor.kind) {
            ClassKind.CLASS -> {
                generateInstancesOfClass(descriptor)
            }
            else -> TODO()
        }

        TODO()
    }

    fun generateInstancesOfClass(descriptor: ClassDescriptor): List<KtExpression> {
        return when (descriptor.modality) {
            Modality.FINAL ->
                invokeAllConstructors(descriptor)
            Modality.ABSTRACT ->
                extendClass(descriptor)
            Modality.OPEN ->
                invokeAllConstructors(descriptor) + extendClass(descriptor)
            Modality.SEALED ->
                listOf() // not interested in extending sealed classes in klib
        }
    }

    private fun invokeAllConstructors(descriptor: ClassDescriptor): List<KtExpression> =
        descriptor.constructors.flatMap { invokeConstructor(descriptor, it) }

    private fun invokeConstructor(descriptor: ClassDescriptor,
                                  constructor: ClassConstructorDescriptor): List<KtExpression> {
//        constructor.para
        constructor.typeParameters // generate types
        constructor.valueParameters // generate instances
        TODO()
    }

    private fun extendClass(descriptor: ClassDescriptor): List<KtExpression> {
        TODO()
    }
}