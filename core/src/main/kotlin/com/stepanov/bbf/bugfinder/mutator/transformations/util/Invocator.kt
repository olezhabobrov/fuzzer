package com.stepanov.bbf.bugfinder.mutator.transformations.util

import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.generator.targetsgenerators.*
import com.stepanov.bbf.bugfinder.generator.targetsgenerators.FunInvocationGenerator
import com.stepanov.bbf.bugfinder.mutator.transformations.FTarget
import com.stepanov.bbf.bugfinder.mutator.transformations.tce.StdLibraryGenerator
import com.stepanov.bbf.bugfinder.project.BBFFile
import com.stepanov.bbf.bugfinder.util.findPropertyByType
import com.stepanov.bbf.bugfinder.util.getRandomVariableName
import com.stepanov.bbf.bugfinder.util.getType
import com.stepanov.bbf.reduktor.parser.PSICreator.psiFactory
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import org.jetbrains.kotlin.js.descriptorUtils.getJetTypeFqName
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.psi.psiUtil.getParentOfType
import org.jetbrains.kotlin.psi.psiUtil.isPublic
import org.jetbrains.kotlin.types.KotlinType
import org.jetbrains.kotlin.types.isNullable
import kotlin.random.Random

object Invocator {

    fun foo(file: BBFFile) {
        val clazz = file.psiFile.getAllPSIChildrenOfType<KtClassOrObject>().map {
            file.getDescriptorByKtClass(it)
        }.last()!!
        val result = ClassInvocator(file).generateInstances(clazz)
//        val func = file.psiFile.getAllPSIChildrenOfType<KtFunction>().random().let { file.getDescriptorByKtFunction(it) }
//        val result = FunInvocator(file).invokeFunction(func!!)
        TODO()
    }

    fun addInvocationOfAllCallable(target: FTarget) {
        val mainFile = target.project.mainFile
        val klibFile = target.project.klib

        foo(klibFile)
        TODO()
        val classInvocations = invokeAllClasses(klibFile).map {
            val type = it.second
            val name = type?.getJetTypeFqName(true)
            "val ${Random.getRandomVariableName()}: $name = " + it.first!!.text }
        writeToMain(mainFile, classInvocations)
        val functionInvocations = klibFile.psiFile.getAllPSIChildrenOfType<KtNamedFunction>()
            .filter { isPublicAccessible(it) }
            .map {
                FunInvocationGenerator(klibFile).invokeFunction(it, klibFile, mainFile)
        }.filter { it.isNotBlank() }
        writeToMain(mainFile, functionInvocations)
        val propertyInvocations = invokeAllProperties(klibFile, mainFile)
        writeToMain(mainFile, propertyInvocations)
    }

    private fun writeToMain(mainFile: BBFFile, invocations: List<String>) {
        val mainText = mainFile.text.substringAfter("{").substringBeforeLast("}")
        val fileText = invocations
            .joinToString(separator = "\n", prefix = "fun main() {\n$mainText", postfix = "\n}")
        val newKtFile = psiFactory.createFile(fileText)
        mainFile.psiFile = newKtFile
        mainFile.updateCtx()
    }

    fun invokeAllClasses(klib: BBFFile): List<Pair<PsiElement?, KotlinType?>> =
        klib.psiFile.getAllPSIChildrenOfType<KtClassOrObject>().filter { isPublicAccessible(it) }.flatMap {  clazz ->
            RandomInstancesGenerator(klib).generateInstancesOfClass(clazz)
        }.filterNotNull().filter { it.first != null }

    fun invokeAllProperties(klib: BBFFile, mainFile: BBFFile): List<String> {
        val properties = klib.psiFile.getAllPSIChildrenOfType<KtProperty>()
            .filter { isPublicAccessible(it) }
//            .filter { it.parent is KtClassBody && it.isPublic }
        val invocations = mutableListOf<String>()
        properties.forEach { property ->
            val outerTypeT = property.getParentOfType<KtClassOrObject>(true)
            val outerType = outerTypeT?.name ?: ""
            val kotlinType =  property.getType(klib.ctx!!)
            val type = if (kotlinType == null)
                ""
            else
                kotlinType.getJetTypeFqName(true) + if (kotlinType.isNullable()) "?" else ""
            val outerProperty = if (outerType.isNotBlank())
                (mainFile.psiFile.findPropertyByType(outerType)?.name ?: return@forEach) + "."
            else
                ""
            val callExpression = "${outerProperty}${property.name}"
            invocations.add("val ${Random.getRandomVariableName()}: $type = $callExpression")
            if (property.isVar) {
                invocations.add("$callExpression = TODO()")
            }
        }
        return invocations
    }

    private fun isPublicAccessible(element: KtModifierListOwner?): Boolean =
        if (element == null)
            true
        else
            element.isPublic &&
                isPublicAccessible(element.getParentOfType<KtClassOrObject>(true))

}