package com.stepanov.bbf.bugfinder.mutator.transformations.util

import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.generator.targetsgenerators.FunInvocationGenerator
import com.stepanov.bbf.bugfinder.generator.targetsgenerators.RandomInstancesGenerator
import com.stepanov.bbf.bugfinder.mutator.transformations.FTarget
import com.stepanov.bbf.bugfinder.project.BBFFile
import com.stepanov.bbf.reduktor.parser.PSICreator.psiFactory
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.KtNamedFunction

object Invocator {

    fun addInvocationOfAllCallable(target: FTarget) {
        val mainFile = target.project.mainFile
        val klibFile = target.project.klib
        val functionInvocations = klibFile.psiFile.getAllPSIChildrenOfType<KtNamedFunction>().map {
             FunInvocationGenerator(klibFile).invokeFunction(it, klibFile)
        }.filter { it.isNotBlank() }
        val classInvocations = invokeAllClasses(klibFile).map { it.text }
        writeToMain(mainFile, classInvocations + functionInvocations)
    }

    private fun writeToMain(mainFile: BBFFile, invocations: List<String>) {
        val fileText = invocations
            .joinToString(separator = "\n", prefix = "fun main() {\n", postfix = "\n}")
        val newKtFile = psiFactory.createFile(fileText)
        mainFile.psiFile = newKtFile
    }

    fun invokeAllClasses(klib: BBFFile): List<PsiElement> =
        klib.psiFile.getAllPSIChildrenOfType<KtClassOrObject>().flatMap {  clazz ->
            RandomInstancesGenerator(klib).generateInstancesOfClass(clazz)
        }.filterNotNull().map { it.first }.filterNotNull()

}