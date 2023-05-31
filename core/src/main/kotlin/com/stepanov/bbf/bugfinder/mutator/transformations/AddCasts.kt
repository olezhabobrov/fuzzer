package com.stepanov.bbf.bugfinder.mutator.transformations

import com.stepanov.bbf.bugfinder.generator.targetsgenerators.typeGenerators.RandomTypeGenerator
import com.stepanov.bbf.bugfinder.mutator.MutationProcessor
import com.stepanov.bbf.bugfinder.mutator.transformations.tce.StdLibraryGenerator
import com.stepanov.bbf.bugfinder.project.BBFFile
import com.stepanov.bbf.bugfinder.util.*
import com.stepanov.bbf.reduktor.parser.PSICreator.psiFactory
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import org.jetbrains.kotlin.cfg.getDeclarationDescriptorIncludingConstructors
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.resolve.calls.callUtil.getType
import org.jetbrains.kotlin.resolve.descriptorUtil.module
import org.jetbrains.kotlin.types.KotlinType
import kotlin.random.Random


class AddCasts:
    Transformation(50) {
    override fun transform(target: FTarget) {
        val file = target.file
        val project = target.project
        val rtg = RandomTypeGenerator(file)

        val ktFile = file.psiFile
        val ctx = file.updateCtx() ?: return
        val currentModule =
            ktFile.getBoxFuncs()?.first().getDeclarationDescriptorIncludingConstructors(ctx)?.module ?: return
        val uninterestingTypes = listOf("Nothing", "Unit")
        val userClassesDescriptors =
            StdLibraryGenerator.getUserClassesDescriptorsFromProject(project, currentModule)
        val numberOfTries =
            ktFile.getAllPSIChildrenOfType<KtExpression>()
                .map { it to it.getType(ctx) }
                .filter { it.second != null && "${it.second}" !in uninterestingTypes }
                .size / 3

        repeat(numberOfTries) {
            val typedExpressions = ktFile.getAllPSIChildrenOfType<KtExpression>()
                .map { it to it.getType(ctx) }
                .filter { it.second != null && "${it.second}" !in uninterestingTypes }.randomOrNull() ?: return
            val randomTypeToCast =
                if (Random.getTrue(10)) {
                    val randomType = rtg.generateRandomTypeWithCtx()
                    randomType?.constructor?.declarationDescriptor as? ClassDescriptor
                } else {
                    chooseRandomTypeToCast(typedExpressions.second!!, userClassesDescriptors)
                } ?: return@repeat
            tryToCast(file, typedExpressions.first, typedExpressions.second!!, randomTypeToCast)
        }
    }

    private fun chooseRandomTypeToCast(
        typeOfRandomExpression: KotlinType,
        userClassesDescriptors: List<ClassDescriptor>
    ): ClassDescriptor? {
        val descriptorOfRandomType =
            typeOfRandomExpression.constructor.declarationDescriptor as? ClassDescriptor ?: return null
        val isUserType = userClassesDescriptors.any { it.name == descriptorOfRandomType.name }
        val descendants =
            if (isUserType) {
                userClassesDescriptors.filter { userClassDescriptor ->
                    userClassDescriptor.getAllSuperClassifiersWithoutAnyAndItself()
                        .any { it.name == descriptorOfRandomType.name }
                }
            } else {
                StdLibraryGenerator.getAllDescendantsFromStdLibrary(descriptorOfRandomType)
            }
        val supertypes = typeOfRandomExpression
            .supertypesWithoutAny()
            .toList()
            .mapNotNull { it.constructor.declarationDescriptor as? ClassDescriptor }
        val supertypesNames = supertypes.map { it.name }
        val listWithPotentialSiblings =
            if (isUserType) {
                userClassesDescriptors
            } else {
                StdLibraryGenerator.klasses
            }
        val siblings = listWithPotentialSiblings.filter {
            val superClassClassifiersNames =
                it.getAllSuperClassifiersWithoutAnyAndItself()
                    .toList()
                    .map { it.name }
            superClassClassifiersNames.intersect(supertypesNames).isNotEmpty()
        }
        val compatibleDescendants = (descendants + supertypes + siblings)
            .filter { it.visibility.isPublicAPI }
        return compatibleDescendants.randomOrNull()
    }

    private fun tryToCast(
        file: BBFFile,
        expression: KtExpression,
        typeOfExpression: KotlinType,
        castToTypeDescriptor: ClassDescriptor
    ): Boolean {
        val typeArgsOfExpressionAsString = throwTypeParams(file, typeOfExpression, castToTypeDescriptor)
        val castExpressionAsString = "${castToTypeDescriptor.name.asString()}$typeArgsOfExpressionAsString"
        val newExpression =
            try {
                if (Random.getTrue(5)) {
                    psiFactory.createExpressionIfPossible("${expression.text} as T")
                } else {
                    psiFactory.createExpressionIfPossible("${expression.text} as $castExpressionAsString")
                }
            } catch (e: Exception) {
                null
            }
        val replacementResult =
            if (newExpression == null) false else MutationProcessor.replaceNodeReturnNode(expression, newExpression, file)
        if (!replacementResult) {
            val newExpressionInBrackets =
                try {
                    psiFactory.createExpressionIfPossible("(${expression.text}) as $castExpressionAsString")
                } catch (e: Exception) {
                    null
                } ?: return false
            return MutationProcessor.replaceNodeReturnNode(expression, newExpressionInBrackets, file)
        } else {
            return true
        }
    }

    private fun throwTypeParams(file: BBFFile, fromType: KotlinType, toType: ClassDescriptor): String {
        val toTypeTypeParams = toType.typeConstructor.parameters
        if (toTypeTypeParams.isEmpty()) return ""
        if (fromType.arguments.size == toTypeTypeParams.size) return fromType.arguments.joinToString(
            prefix = "<",
            postfix = ">"
        )
        val fromTypeTypeArgs = fromType.arguments
        val fromTypeTypeParams = fromType.constructor.parameters
        val typeParamsToArgs = fromTypeTypeParams.zip(fromTypeTypeArgs).associate { it.first.name to it.second }
        val toTypeTypeArgsAsString = toTypeTypeParams.map { tp ->
            typeParamsToArgs[tp.name] ?: RandomTypeGenerator(file).generateRandomTypeWithCtx(tp.upperBounds.firstOrNull())
        }.joinToString(prefix = "<", postfix = ">")
        return toTypeTypeArgsAsString
    }

}