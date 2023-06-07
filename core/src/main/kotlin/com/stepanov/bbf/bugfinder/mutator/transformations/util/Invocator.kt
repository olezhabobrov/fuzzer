package com.stepanov.bbf.bugfinder.mutator.transformations.util

import com.stepanov.bbf.bugfinder.generator.targetsgenerators.RandomInstancesGenerator
import com.stepanov.bbf.bugfinder.mutator.transformations.FTarget
import com.stepanov.bbf.bugfinder.project.BBFFile
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.psi.psiUtil.isPublic

object Invocator {

    fun addInvocationOfAllCallable(target: FTarget) {

        val mainFile = target.project.mainFile
        val klibFile = target.project.klib

        val children = klibFile.psiFile.children


        invokeAllClasses(klibFile, mainFile)
        val ctx = klibFile.ctx
        val expressions = klibFile.psiFile.getAllPSIChildrenOfType<KtCallExpression>()
        val functions = klibFile.psiFile.getAllPSIChildrenOfType<KtNamedFunction>()
        val classes = klibFile.psiFile.getAllPSIChildrenOfType<KtClassOrObject>()
        val result = RandomInstancesGenerator(klibFile).generateInstancesOfClass(classes[4])
        val clazz = classes[0]
//        val constructor = clazz.createPrimaryConstructorIfAbsent()
//        val parameterList = clazz.createPrimaryConstructorParameterListIfAbsent()
        TODO()
    }

    fun invokeAllClasses(klib: BBFFile, mainFile: BBFFile) {
        val result = klib.psiFile.getAllPSIChildrenOfType<KtClassOrObject>().flatMap {  clazz ->
            RandomInstancesGenerator(klib).generateInstancesOfClass(clazz)
        }
        TODO()
    }

}