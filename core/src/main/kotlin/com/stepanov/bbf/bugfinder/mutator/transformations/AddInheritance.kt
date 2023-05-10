package com.stepanov.bbf.bugfinder.mutator.transformations

import com.stepanov.bbf.bugfinder.generator.targetsgenerators.RandomInstancesGenerator
import com.stepanov.bbf.bugfinder.generator.targetsgenerators.typeGenerators.RandomTypeGenerator
import com.stepanov.bbf.bugfinder.mutator.MutationProcessor
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.generators.ClassBodyGenerator
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GClass
import com.stepanov.bbf.bugfinder.mutator.transformations.tce.StdLibraryGenerator
import com.stepanov.bbf.bugfinder.project.BBFFile
import com.stepanov.bbf.bugfinder.util.findPsi
import com.stepanov.bbf.bugfinder.util.getBoxFuncs
import com.stepanov.bbf.bugfinder.util.getNameWithoutError
import com.stepanov.bbf.bugfinder.util.getTrue
import com.stepanov.bbf.reduktor.parser.PSICreator.psiFactory
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import com.stepanov.bbf.reduktor.util.replaceThis
import org.jetbrains.kotlin.cfg.getDeclarationDescriptorIncludingConstructors
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.descriptors.ClassKind
import org.jetbrains.kotlin.descriptors.Modality
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.psi.psiUtil.parents
import org.jetbrains.kotlin.resolve.bindingContextUtil.getAbbreviatedTypeOrType
import org.jetbrains.kotlin.resolve.descriptorUtil.module
import org.jetbrains.kotlin.resolve.scopes.getDescriptorsFiltered
import org.jetbrains.kotlin.types.replace
import org.jetbrains.kotlin.types.typeUtil.asTypeProjection
import kotlin.random.Random

//Don't forget to add import for projects
//Deal with TypeParameters

class AddInheritance: Transformation(100) {

    override fun transform(target: FTarget) {
        val file = target.file
        val project = target.project
        val rtg = RandomTypeGenerator(file)

        val ktFile = file.psiFile
        val ctx = file.updateCtx() ?: return
        val currentModule =
            ktFile.getBoxFuncs()?.firstOrNull().getDeclarationDescriptorIncludingConstructors(ctx)?.module ?: return
        val userClassesDescriptors = StdLibraryGenerator.getUserClassesDescriptorsFromProject(project, currentModule)
        val randomClass = file.psiFile.getAllPSIChildrenOfType<KtClassOrObject>().randomOrNull() ?: return
        val randomClassDescriptor =
            randomClass.getDeclarationDescriptorIncludingConstructors(ctx) as? ClassDescriptor ?: return
        val randomClassCopy = randomClass.copy() as KtClassOrObject
        val randomClassTypeParams = randomClassDescriptor.typeConstructor.parameters
        val randomGClass = GClass.fromPsi(randomClass)
        val userClassForInheritance = userClassesDescriptors
            .filter {
                it.kind == ClassKind.INTERFACE || it.modality != Modality.FINAL
            }.randomOrNull() ?: return

        val typeConstructor = userClassForInheritance.typeConstructor
        val typeParams =
            typeConstructor.parameters.map {
                if (randomClassTypeParams.isNotEmpty() && Random.getTrue(30)) {
                    randomClassTypeParams.random().defaultType
                } else {
                    rtg.generateRandomTypeWithCtx(it.upperBounds.randomOrNull())
                }
            }
        if (typeParams.any { it == null }) return
        val replacedUserClassForInheritance =
            userClassForInheritance.defaultType.replace(typeParams.map { it!!.asTypeProjection() })
        randomClassCopy.addSuperTypeListEntry(psiFactory.createSuperTypeEntry(replacedUserClassForInheritance.getNameWithoutError()))
        val bodyGenerator = ClassBodyGenerator(file, randomGClass, 0)
        val overridings = bodyGenerator
            .getMembersToOverride(randomGClass, listOf(replacedUserClassForInheritance))
        val intersectionByNames =
            randomClassDescriptor.unsubstitutedMemberScope.getDescriptorsFiltered { true }
                .map { it.name.asString() }.intersect(overridings.map { it.name.asString() })
        val filteredOverridings = overridings.filter { it.name.asString() !in intersectionByNames }
        randomClassDescriptor.unsubstitutedMemberScope
            .getDescriptorsFiltered { true }
            .forEach { descr ->
                if (descr.name.asString() in intersectionByNames) {
                    val psi = descr.findPsi() ?: return@forEach
                    if (psi is KtModifierListOwner && !psi.hasModifier(KtTokens.OVERRIDE_KEYWORD)) {
                        psi.addModifier(KtTokens.OVERRIDE_KEYWORD)
                    }
                }
            }
        val overridingAsString = bodyGenerator.generateOverrides(
            randomGClass,
            listOf(replacedUserClassForInheritance),
            filteredOverridings
        )
        val classBody = randomClassCopy.body
        classBody?.rBrace?.delete()
        val newClassAsString =
            if (classBody == null) {
                "${randomClassCopy.text} { $overridingAsString\n}"
            } else {
                "${randomClassCopy.text}\n$overridingAsString\n}"
            }
        val newClassAsPSI =
            try {
                psiFactory.createClass(newClassAsString)
            } catch (e: Exception) {
                null
            } catch (e: Error) {
                null
            } ?: return
        randomClass.replaceThis(newClassAsPSI)
        replaceTODO(file, newClassAsPSI)
    }

    private fun replaceTODO(file: BBFFile, randomClass: KtClassOrObject) {
        val ctx = file.updateCtx() ?: return
        val todos =
            randomClass.getAllPSIChildrenOfType<KtCallExpression>().filter { it.calleeExpression?.text == "TODO" }
        for (todo in todos) {
            val parent = todo.parents.firstOrNull { it is KtProperty || it is KtNamedFunction } ?: continue
            val returnType =
                if (parent is KtProperty) {
                    parent.typeReference?.getAbbreviatedTypeOrType(ctx)
                } else {
                    (parent as KtNamedFunction).typeReference?.getAbbreviatedTypeOrType(ctx)
                } ?: continue
            //Getting value of needed type
            val value = RandomInstancesGenerator(file).generateValueOfTypeAsExpression(returnType) ?: continue
            MutationProcessor.replaceNodeReturnNode(todo, value, file)
        }
        return
    }

}