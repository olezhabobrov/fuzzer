package com.stepanov.bbf.bugfinder.mutator.transformations.util

import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.generator.targetsgenerators.FunInvocationGenerator
import com.stepanov.bbf.bugfinder.generator.targetsgenerators.RandomInstancesGenerator
import com.stepanov.bbf.bugfinder.mutator.transformations.FTarget
import com.stepanov.bbf.bugfinder.project.BBFFile
import com.stepanov.bbf.bugfinder.util.*
import com.stepanov.bbf.reduktor.parser.PSICreator.psiFactory
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import org.jetbrains.kotlin.js.descriptorUtils.getJetTypeFqName
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.psi.psiUtil.getParentOfType
import org.jetbrains.kotlin.psi.psiUtil.isPublic
import org.jetbrains.kotlin.types.KotlinType
import kotlin.collections.flatMap
import kotlin.random.Random

object Invocator {

    fun addInvocationOfAllCallable(target: FTarget) {
        val mainFile = target.project.mainFile
        val klibFile = target.project.klib
        val classInvocations = invokeAllClasses(klibFile).map {
            val type = it.second
            val name = type?.getJetTypeFqName(false)
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
            val type = property.getType(klib.ctx!!)?.name ?: ""
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

    private fun isPublicAccessible(element: KtModifierListOwner) =
        element.isPublic ||
                element.getParentOfType<KtClassOrObject>(true)?.isPublic ?: false

}