package com.stepanov.bbf.bugfinder.mutator.transformations.klib

import com.stepanov.bbf.bugfinder.mutator.transformations.FTarget
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GClass
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GProperty
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GStructure
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import com.stepanov.bbf.reduktor.util.replaceThis
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner
import kotlin.random.Random

class AddImplToAbstract: BinaryCompatibleTransformation(1) {

    override fun transform(target: FTarget) {
        val file = target.file
        val allAbstractEntities = file.psiFile.getAllPSIChildrenOfType<KtClass>().filter {
            val gclass = GClass.fromPsi(it)
            gclass.isSealed() || gclass.isAbstract()
        }.flatMap {
            getAllUnimplementedFunctionsAndProperties(it)
        }

        val allInterfaceFunctions = file.psiFile.getAllPSIChildrenOfType<KtClass>().filter {
            val gclass = GClass.fromPsi(it)
            gclass.isInterface()
        }.flatMap {
            getAllUnimplementedFunctionsAndProperties(it)
        }
        if (allInterfaceFunctions.isEmpty() && allAbstractEntities.isEmpty())
            return
        if (allAbstractEntities.isEmpty()) {
            val entity = allInterfaceFunctions.random()
            addImpl(entity, false)
            return
        }
        if (allInterfaceFunctions.isEmpty()) {
            val entity = allAbstractEntities.random()
            addImpl(entity, true)
            return
        }
        if (Random.nextBoolean()) {
            val entity = allAbstractEntities.random()
            addImpl(entity, true)
        } else {
            val entity = allInterfaceFunctions.random()
            addImpl(entity, false)
            return
        }
    }

    private fun addImpl(entity: KtTypeParameterListOwner, removeAbstract: Boolean) {
        val gstructure = GStructure.fromPsi(entity)
        gstructure.addDefaultImplementation()
        if (removeAbstract) {
            gstructure.removeAbstract()
            gstructure.addOpen()
        }
        val newEntity = gstructure.toPsi()
        if (newEntity != null) {
            entity.replaceThis(newEntity)
        }
    }

    private fun getAllUnimplementedFunctionsAndProperties(clazz: KtClass): List<KtTypeParameterListOwner> {
        val functions = clazz.getAllPSIChildrenOfType<KtNamedFunction>().filter {
            !it.hasBody()
        }
        val properties =
            clazz.getAllPSIChildrenOfType<KtProperty>().filter {
            val gProperty = GProperty.fromPsi(it)
            gProperty.isNotImplemented()
        }
        return (functions + properties)
    }
}