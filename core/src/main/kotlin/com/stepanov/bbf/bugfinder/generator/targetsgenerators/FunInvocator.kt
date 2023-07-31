package com.stepanov.bbf.bugfinder.generator.targetsgenerators

import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GStructure
import com.stepanov.bbf.bugfinder.project.BBFFile
import com.stepanov.bbf.bugfinder.util.addPsiToBody
import com.stepanov.bbf.bugfinder.util.findPsi
import com.stepanov.bbf.reduktor.parser.PSICreator
import com.stepanov.bbf.reduktor.util.replaceThis
import org.jetbrains.kotlin.descriptors.*
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner
import org.jetbrains.kotlin.psi.psiUtil.getParentOfType
import org.jetbrains.kotlin.resolve.calls.components.hasDefaultValue
import org.jetbrains.kotlin.types.isNullable

class FunInvocator(val file: BBFFile) {

    fun invokeFunction(descriptor: FunctionDescriptor, depth: Int = 0): List<String> {
        if (!descriptor.visibility.isPublicAPI)
            return listOf()
        if (descriptor.isSuspend)
            return listOf()
        val invocations = invokeParameterBrackets(descriptor).map { valueParameters ->
            "${descriptor.name.asString()}$valueParameters"
        }
        val parent = descriptor.containingDeclaration
        if (parent is ClassDescriptor) {
            val parentInv = ClassInvocator(file).randomClassInvocation(parent, depth + 1)
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

    fun implementMember(member: DeclarationDescriptor, clazz: ClassDescriptor) {
        val psi = member.findPsi() as? KtTypeParameterListOwner ?: return
        val gmember = GStructure.fromPsi(psi)
        gmember.addDefaultImplementation()
        if (clazz.kind == ClassKind.CLASS)
            gmember.addOpen()
        if (psi.getParentOfType<KtClass>(true)?.name == clazz.name.asString()) {
            val newPsi = gmember.toPsi() ?: return
            psi.replaceThis(newPsi)
        } else {
            gmember.addOverride()
            val classPsi = clazz.findPsi() as? KtClass ?: return
            val newPsi = gmember.toPsi() ?: return
            classPsi.addPsiToBody(PSICreator.psiFactory.createWhiteSpace("\n\n"))
            classPsi.addPsiToBody(newPsi)
        }
    }

}