package com.stepanov.bbf.bugfinder.generator.targetsgenerators

import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.*
import com.stepanov.bbf.bugfinder.mutator.transformations.tce.StdLibraryGenerator
import com.stepanov.bbf.bugfinder.project.BBFFile
import com.stepanov.bbf.bugfinder.util.*
import com.stepanov.bbf.reduktor.util.uniqueString
import com.stepanov.bbf.util.WeightedList
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.descriptors.ClassKind
import org.jetbrains.kotlin.descriptors.Modality
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner
import kotlin.collections.flatMap
import kotlin.random.Random

class KlibClassGenerator(val file: BBFFile) {

    fun generateValueClass(): PsiElement? {
        val gclass = GClass()
        with (gclass) {
            classWord = "class"
            name = Random.getRandomClassName()
            addValue()
            val params = ConstructorGenerator(file).generateArgsForValueClass()
            constructorArgs = params.map { it.toString() }
            generateSecondaryConstructors(params).forEach {
                body += it + "\n\n"
            }
            val mySupertypes = getRandomSupertypes().filter { it.kind == ClassKind.INTERFACE }
            supertypes = mySupertypes
                .map { it.name.asString() }.toMutableList()
            body += implementMembers(mySupertypes, gclass).joinToString(separator = "\n\n")
        }
        return gclass.toPsi()
    }

    fun generate(): PsiElement? {
        val gclass = GClass()
        with (gclass) {
            classWord = WeightedList(listOf(
                "class" to 50.0,
                "interface" to 40.0,
                "object" to 20.0
            )).getRandom()!!
            modifiers = generateModifiers(gclass)
            name = Random.getRandomClassName()
            if (isClass()) {
                val params = ConstructorGenerator(file).generateArgs(true)
                constructorArgs = params.map { it.toString() }
                generateSecondaryConstructors(params).forEach {
                    body += it + "\n\n"
                }
            }
            val mySupertypes = getRandomSupertypes()
            supertypes = mySupertypes.map {
                ClassInvocator(file).randomConstructorInvocation(it)
            }.toMutableList()
            body += implementMembers(mySupertypes, gclass).joinToString(separator = "\n\n")
            body += "\n\n" + addMembers(gclass).joinToString("\n\n")
        }

        return gclass.toPsi()
    }

    private fun implementMembers(supertypes: List<ClassDescriptor>, gClass: GClass): List<String> {
        val members = supertypes.flatMap { StdLibraryGenerator.getMembersToOverride(it.defaultType) }
            .filterDuplicatesBy { it.uniqueString() }
        val result = mutableListOf<String>()
        members.forEach { member ->
            if ((gClass.isInterface() || gClass.isAbstract()) && Random.getTrue(70))
                return@forEach
            val psi = member.findPsi() as? KtTypeParameterListOwner ?: return@forEach
            val gmember = GStructure.fromPsi(psi)
            gmember.addDefaultImplementation()
            gmember.addOverride()
            if ((gClass.isOpen() || gClass.isAbstract()) && Random.getTrue(50))
                gmember.addOpen()
            result.add(gmember.toString())
        }
        return result
    }

    private fun addMembers(gClass: GClass): List<String> {
        if (gClass.isAbstract()) {
            val gfun = GFunction()
            with(gfun) {
                name = Random.getRandomVariableName()
                addAbstract()
            }
            return listOf(gfun.toString())
        }
        return listOf()
    }

    private fun generateSecondaryConstructors(primaryArgs: List<GParameter>): List<String> {
        val amount = WeightedList(listOf(
            0 to 30.0,
            1 to 60.0,
            2 to 30.0
        )).getRandom()!!
        return List(amount) {
            val gcon = GConstructor()
            gcon.argsParams = ConstructorGenerator(file).generateArgs()
            if (primaryArgs.isNotEmpty()) {
                val delegationCall = mutableListOf<String>().also { params ->
                    primaryArgs.forEach {
                        params.add("TODO() as ${it.type}")
                    }
                }.joinToString(separator = ", ", prefix = "this(", postfix = ")")
                gcon.delegationCalls.add(delegationCall)
            }
            gcon.toString()
        }
    }

    private fun generateModifiers(gClass: GClass): MutableList<String> {
        if (!gClass.isClass())
            return mutableListOf()
        val modality = WeightedList(listOf(
            "open" to 60.0,
            "abstract" to 50.0,
            "" to 30.0
            )).getRandom()!!
        return mutableListOf<String>().also {
            if (modality.isNotBlank())
                it.add(modality)
        }
    }

    private fun getRandomSupertypes(): List<ClassDescriptor> {
        val classes = file.getAllClassDescriptors()
            .filter { it.kind == ClassKind.CLASS && it.modality != Modality.FINAL}
        val interfaces = file.getAllClassDescriptors()
            .filter { it.kind == ClassKind.INTERFACE }
        val result = mutableListOf<ClassDescriptor>()
        if (classes.isNotEmpty() && Random.getTrue(60))
            result.add(classes.random())
        if (interfaces.isNotEmpty() && Random.getTrue(60)) {
            var amount = 1
            if (interfaces.size > 1 && Random.getTrue(50))
                amount = 2
            if (interfaces.size > 2 && Random.getTrue(30))
                amount = 3
            result.addAll(interfaces.shuffled().take(amount))
        }
        return result
    }
}