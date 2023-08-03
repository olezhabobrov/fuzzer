package com.stepanov.bbf.bugfinder.generator.targetsgenerators

import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GClass
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GFunction
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GProperty
import com.stepanov.bbf.bugfinder.mutator.transformations.tce.StdLibraryGenerator
import com.stepanov.bbf.bugfinder.project.BBFFile
import com.stepanov.bbf.bugfinder.util.filterDuplicatesBy
import com.stepanov.bbf.bugfinder.util.findPsi
import com.stepanov.bbf.bugfinder.util.getPublicConstructors
import com.stepanov.bbf.bugfinder.util.getRandomClassName
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.descriptors.ClassKind
import org.jetbrains.kotlin.descriptors.FunctionDescriptor
import org.jetbrains.kotlin.descriptors.Modality
import org.jetbrains.kotlin.descriptors.PropertyDescriptor
import org.jetbrains.kotlin.psi.KtFunction
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.resolve.DeclarationsChecker.Companion.hasAccessorImplementation
import kotlin.random.Random

class ClassImplementer(val file: BBFFile) {

    fun randomImplementationOfClasses(supertypes: List<ClassDescriptor>): String {
        val gclass = GClass()
        with(gclass) {
            name = Random.getRandomClassName()
            classWord = "class"
            gclass.supertypes = callConstructors(supertypes).random().toMutableList()
            body = implementAllMembers(supertypes).joinToString(separator = "\n")
        }
        return gclass.toString()
    }

    fun allImplementationsOfClasses(supertypes: List<ClassDescriptor>, depth: Int = 0): List<String> {
        var onlyAbstractImpl = false
        return callConstructors(supertypes, depth).flatMap { constructors ->
            val gclass = GClass()
            with(gclass) {
                classWord = "object"
                gclass.supertypes = constructors.toMutableList()
                body = implementAllMembers(supertypes).joinToString(separator = "\n")
            }
            gclass.toString()
            val gclass2 = GClass()
            with(gclass2) {
                classWord = "object"
                gclass2.supertypes = constructors.toMutableList()
                body = implementAbstractMembers(supertypes).joinToString(separator = "\n")
            }
            if (gclass.toString() != gclass2.toString() && !onlyAbstractImpl) {
                onlyAbstractImpl=true
                listOf(gclass.toString(), gclass2.toString())
            }
            else
                listOf(gclass.toString())
        }
    }

    fun callConstructors(supertypes: List<ClassDescriptor>, depth: Int = 0): List<List<String>>  {
        val abstractClassesInvocations =
            supertypes.firstOrNull { it.kind != ClassKind.INTERFACE }?.let { descriptor ->
                descriptor.getPublicConstructors().flatMap { constructor ->
                    FunInvocator(file).invokeParameterBrackets(constructor, depth).map {
                        "${descriptor.name.asString()}$it"
                    }
                }
            }
        if (abstractClassesInvocations == null) {
            return listOf(supertypes.map { it.name.asString() })
        }
        return abstractClassesInvocations.map { abstractClass ->
            supertypes.filter { it.kind == ClassKind.INTERFACE }.map { it.name.asString() } + abstractClass
        }
    }

    fun implementAllMembers(supertypes: List<ClassDescriptor>): List<String> {
        val allMembers = getAllMembers(supertypes).map {
            val psi = it.findPsi()
            if (psi == null)
                null
            else
                when (psi) {
                    is KtFunction -> GFunction.fromPsi(psi)
                    is KtProperty -> GProperty.fromPsi(psi)
                    else -> null
                }
        }
//        if (allMembers.contains(null))
//            error("Shouldn't have null")
        return allMembers.filterNotNull().map {
            it.addOverride()
            it.addDefaultImplementation()
            it.toString()
        }
    }

    fun implementAbstractMembers(supertypes: List<ClassDescriptor>): List<String> =
        getAllMembers(supertypes).filter {
            when(it) {
                is FunctionDescriptor -> it.modality == Modality.ABSTRACT
                is PropertyDescriptor -> !it.hasAccessorImplementation()
                else -> error("is not function oder property")
            }
        }.map {
            val psi = it.findPsi()
            if (psi == null)
                null
            else
                when (psi) {
                    is KtFunction -> GFunction.fromPsi(psi)
                    is KtProperty -> GProperty.fromPsi(psi)
                    else -> null
                }
        }.filterNotNull().map {
            it.addOverride()
            it.addDefaultImplementation()
            it.toString()
        }


    private fun trivialConstructorCall(descriptor: ClassDescriptor): String =
        descriptor.getPublicConstructors().random().let {
            descriptor.name.asString() +
                    it.valueParameters.joinToString(prefix = "(", postfix = ")", separator = ",") { "TODO()" }
        }

    private fun getAllMembers(supertypes: List<ClassDescriptor>) =
        supertypes.map { it.defaultType }.flatMap { StdLibraryGenerator.getMembersToOverride(it) }
            .filterDuplicatesBy {
                if (it is FunctionDescriptor) "${it.name}${it.valueParameters.map { it.name.asString() + it.returnType.toString() }}"
                else it.name.asString()
            }
}