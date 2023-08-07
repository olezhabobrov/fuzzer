package com.stepanov.bbf.bugfinder.mutator.transformations.klib

import com.stepanov.bbf.bugfinder.mutator.transformations.tce.StdLibraryGenerator
import com.stepanov.bbf.bugfinder.util.filterDuplicatesBy
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.descriptors.FunctionDescriptor
import org.jetbrains.kotlin.descriptors.Modality
import org.jetbrains.kotlin.descriptors.PropertyDescriptor
import org.jetbrains.kotlin.resolve.DeclarationsChecker.Companion.hasAccessorImplementation
import org.jetbrains.kotlin.types.KotlinType
import org.jetbrains.kotlin.types.typeUtil.supertypes

fun getAllMembers(supertypes: List<KotlinType>) =
    supertypes.flatMap { StdLibraryGenerator.getMembersToOverride(it) }
        .filterDuplicatesBy {
            if (it is FunctionDescriptor) "${it.name}${it.valueParameters.map { it.name.asString() + it.returnType.toString() }}"
            else it.name.asString()
        }

fun ClassDescriptor.getAllMembers() = getAllMembers(this.defaultType.supertypes().toList())


fun ClassDescriptor.getAbstractMembers() = getAllMembers(this.defaultType.supertypes().toList())
    .filter {
    when(it) {
        is FunctionDescriptor -> it.modality == Modality.ABSTRACT
        is PropertyDescriptor -> !it.hasAccessorImplementation()
        else -> error("is not function oder property")
    }
}

