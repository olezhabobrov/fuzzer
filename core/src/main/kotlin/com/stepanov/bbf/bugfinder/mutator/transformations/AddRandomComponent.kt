package com.stepanov.bbf.bugfinder.mutator.transformations

import com.stepanov.bbf.bugfinder.project.BBFFile
import com.stepanov.bbf.bugfinder.project.Project
import com.stepanov.bbf.bugfinder.generator.targetsgenerators.RandomInstancesGenerator
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.generators.RandomFunctionGenerator
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.generators.RandomPropertyGenerator
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GClass
import com.stepanov.bbf.bugfinder.mutator.transformations.util.FileFieldsTable
import com.stepanov.bbf.bugfinder.util.*
import com.stepanov.bbf.reduktor.parser.PSICreator.psiFactory
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import com.stepanov.bbf.reduktor.util.initBodyByTODO
import org.jetbrains.kotlin.cfg.getDeclarationDescriptorIncludingConstructors
import org.jetbrains.kotlin.descriptors.FunctionDescriptor
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.psi.psiUtil.isPrivate
import org.jetbrains.kotlin.resolve.bindingContextUtil.getAbbreviatedTypeOrType
import org.jetbrains.kotlin.types.KotlinType
import org.jetbrains.kotlin.types.typeUtil.isTypeParameter
import kotlin.random.Random

class AddRandomComponent(project: Project, file: BBFFile):
    Transformation(project, file,
        1, 100) {

    private var randomInstanceGenerator = RandomInstancesGenerator(file)
    private var table = FileFieldsTable(file.psiFile, file.ctx!!)

    override fun transform() {
        repeat(Random.nextInt(20, 50)) {
            val randomClass = file.psiFile.getAllPSIChildrenOfType<KtClassOrObject>()/*.first()*/.randomOrNull() ?: return
            val gRandomClass = GClass.fromPsi(randomClass)
            if (Random.nextBoolean()) addRandomProperty(randomClass, gRandomClass)
            else addRandomFunction(randomClass, gRandomClass)
        }
    }

    private fun addRandomTopLevelFunction() {
        val randomFunctionGenerator = RandomFunctionGenerator(file)
        val generatedFunc = randomFunctionGenerator.generate() as? KtNamedFunction ?: return
        //println(generatedFunc.text)
        return
    }

    private fun addRandomProperty(psiClass: KtClassOrObject, gClass: GClass) {
        val randomPropertyGenerator = RandomPropertyGenerator(file, gClass)
        val generatedProp =
            if (Random.getTrue(30)) randomPropertyGenerator.generateInterestingProperty(psiClass)?.first as? KtProperty
            else randomPropertyGenerator.generate() as? KtProperty
        if (generatedProp == null) return
        //println(generatedProp.text + "\n")
        val addedProperty = psiClass.addPsiToBody(generatedProp) as? KtProperty ?: return
        val updatedCtx = file.updateCtx() ?: return
        val propertyType = addedProperty.typeReference?.getAbbreviatedTypeOrType(updatedCtx) ?: return
        val initializer = addedProperty.initializer
        if (initializer != null && initializer.text?.contains("TODO()") == true) {
            generateValueOfType(propertyType)?.let { initializer.replaceThis(it) }
        }
        val getter = addedProperty.getter
        if (getter != null && getter.hasInitializer() && getter.text?.contains("TODO()") == true) {
            generateValueOfType(propertyType)?.let { getter.initializer!!.replaceThis(it) }
        }
        val setter = addedProperty.setter
        if (setter != null && setter.hasInitializer() && !setter.isPrivate()) {
            generateValueOfType(propertyType)?.let { addedProperty.setInitializer(it) }
            generateValueOfType(propertyType)?.let { setter.initializer!!.replaceThis(it) }
        }
    }

    private fun generateValueOfType(type: KotlinType): KtExpression? {
        val newValue = randomInstanceGenerator.generateValueOfType(type)
        return try {
            psiFactory.createExpressionIfPossible(newValue)
        } catch (e: Exception) {
            null
        } catch (e: Error) {
            null
        }
    }

    //TODO open and abstract functions?????
    private fun addRandomFunction(psiClass: KtClassOrObject, gClass: GClass) {
        val randomFunctionGenerator = RandomFunctionGenerator(file, gClass)
        val generatedFunc = randomFunctionGenerator.generate() as? KtNamedFunction ?: return
        //println(generatedFunc.text)
        val addedGeneratedFunc = psiClass.addPsiToBody(generatedFunc) as? KtNamedFunction ?: return
        val updatedCtx = file.updateCtx() ?: return
        table = FileFieldsTable(file.psiFile, updatedCtx)
        val addedFuncDescriptor =
            addedGeneratedFunc.getDeclarationDescriptorIncludingConstructors(updatedCtx) as? FunctionDescriptor
                ?: return
        val tableEntry = table.getEntry(addedFuncDescriptor) ?: return
        val availableFields = table.getAvailableDescriptors(tableEntry)
        val rtvType = addedFuncDescriptor.returnType ?: return
        val funBody =
            if (rtvType.isTypeParameter()) {
                val randomFieldWithSameType =
                    availableFields
                        .filter { it.type.isTypeParameter() && rtvType.getNameWithoutError() == it.type.getNameWithoutError() }
                        .randomOrNull()
                        ?.value as? KtNamedDeclaration
                randomFieldWithSameType?.name ?: ""
            } else randomInstanceGenerator.generateValueOfType(rtvType)
        //println("BODY = $funBody")
        if (funBody.isEmpty()) return
        try {
            addedGeneratedFunc.initBodyByValue(psiFactory, funBody)
        } catch (e: Exception) {
            addedGeneratedFunc.initBodyByTODO(psiFactory)
        } catch (e: Error) {
            addedGeneratedFunc.initBodyByTODO(psiFactory)
        }
    }
}