package com.stepanov.bbf.bugfinder.mutator.transformations

import com.stepanov.bbf.bugfinder.executor.project.BBFFile
import com.stepanov.bbf.bugfinder.executor.project.Project
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.psi.psiUtil.parents

import com.stepanov.bbf.bugfinder.util.getAllChildrenOfTheLevel
import com.stepanov.bbf.bugfinder.util.getAllPSIChildrenOfType
import com.stepanov.bbf.bugfinder.util.getRandomBoolean
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory.psiFactory as psiFactory

//Change extension to smth?
class ChangeSmthToExtension(project: Project, file: BBFFile,
                             amountOfTransformations: Int = 1, probPercentage: Int = 100):
    Transformation(project, file,
        amountOfTransformations, probPercentage) {

    override fun transform() {
        file.psiFile.getAllPSIChildrenOfType<KtProperty>()
                .filter { it.parents.any { p -> p is KtClass } && getRandomBoolean(2) }
                .forEach {
                    val kl = it.parents.find { p -> p is KtClass } ?: return@forEach
                    val klass = kl as KtClass
                    val newProp = if (!it.isVar) {
                        psiFactory.createProperty("val ${klass.name}.${it.name} get() = ${it.initializer?.text}")
                    } else {
                        psiFactory.createProperty("var ${klass.name}.${it.name} get() = ${it.initializer?.text} \n set(value) = TODO()")
                    }
                    val children = it.node.getAllChildrenOfTheLevel(1)
                    children.forEach { file.psiFile.node.removeChild(it) }
                    klass.parent.node.addChild(newProp.node, null)
                    klass.parent.node.addChild(psiFactory.createWhiteSpace("\n").node, null)
                }
        // Make functions as extensions
        file.psiFile.getAllPSIChildrenOfType<KtNamedFunction>()
                .filter { it.parents.any { p -> p is KtClass } && getRandomBoolean(2) }
                .forEach {
                    val kl = it.parents.find { p -> p is KtClass } ?: return@forEach
                    val klass = kl as KtClass
                    val splitted = it.text.split("fun ")
                    val newText = "${splitted[0]} fun ${klass.name}.${splitted.subList(1, splitted.size).joinToString()}"
                    val newFun = psiFactory.createFunction(newText)
                    val children = it.node.getAllChildrenOfTheLevel(1)
                    children.forEach { file.psiFile.node.removeChild(it) }
                    klass.parent.node.addChild(newFun.node, null)
                    klass.parent.node.addChild(psiFactory.createWhiteSpace("\n").node, null)
                }
    }
}
