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

fun ClassDescriptor.getAllMembers() = StdLibraryGenerator.getMembersToOverride(this.defaultType)

fun ClassDescriptor.getAbstractMembers() = getAllMembers()
    .filter {
    when(it) {
        is FunctionDescriptor -> it.modality == Modality.ABSTRACT
        is PropertyDescriptor -> !it.hasAccessorImplementation()
        else -> error("is not function oder property")
    }
}

