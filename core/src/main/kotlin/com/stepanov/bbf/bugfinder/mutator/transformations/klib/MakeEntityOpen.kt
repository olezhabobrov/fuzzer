package com.stepanov.bbf.bugfinder.mutator.transformations.klib

import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.mutator.transformations.FTarget
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GClass
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GStructure
import com.stepanov.bbf.bugfinder.util.getAllPSIChildrenOfTypeOfFirstLevel
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import com.stepanov.bbf.reduktor.util.replaceThis
import org.jetbrains.kotlin.psi.*
import kotlin.random.Random

class MakeEntityOpen: BinaryCompatibleTransformation(1) {

    override fun transform(target: FTarget) {
        replaceWithAnchor(target.file.psiFile)
    }

    fun replaceWithAnchor(anchor: PsiElement) {
        val finalClasses = anchor.getAllPSIChildrenOfType<KtClass>().filter {
            val gclass = GClass.fromPsi(it)
            !gclass.canBeExtended() && gclass.isClass() && !gclass.isInner()
        }
        val openClasses = anchor.getAllPSIChildrenOfTypeOfFirstLevel<KtClass>().filter {
            val gclass = GClass.fromPsi(it)
            gclass.isOpen() || gclass.isAbstract()
        }
        if ((finalClasses + openClasses).isEmpty())
            return
        if (finalClasses.isEmpty()) {
            changeInOpenClass(openClasses.random())
            return
        }
        if (openClasses.isEmpty()) {
            makeFinalClassOpen(finalClasses.random())
            return
        }
        if (Random.nextBoolean())
            makeFinalClassOpen(finalClasses.random())
        else
            changeInOpenClass(openClasses.random())
    }

    private fun makeFinalClassOpen(randomClass: KtClass) {
        val newClass = makeOpen(randomClass)
        if (newClass != null)
            randomClass.replaceThis(newClass)
    }

    private fun changeInOpenClass(randomClass: KtClass) {
        if (randomClass.body == null)
            return
        val properties = randomClass.body!!.getAllPSIChildrenOfTypeOfFirstLevel<KtProperty>()
        val functions = randomClass.body!!.getAllPSIChildrenOfTypeOfFirstLevel<KtFunction>()
        val allFinalEntities = (properties + functions).filter {
            !GStructure.fromPsi(it).isOpen() && it !is KtConstructor<*>
        }
        if (allFinalEntities.isNotEmpty()) {
            val entity = allFinalEntities.random()
            val openEntity = GStructure.fromPsi(entity).also { it.makeOpen() }.toPsi()
            if (openEntity != null)
                entity.replaceThis(openEntity)
        }
    }

    private fun makeOpen(entity: KtTypeParameterListOwner): PsiElement? {
        val gStructure = GStructure.fromPsi(entity)
        gStructure.makeOpen()
        return gStructure.toPsi()
    }
}