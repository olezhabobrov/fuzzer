package com.stepanov.bbf.bugfinder.generator.targetsgenerators

import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.mutator.MutationProcessor.psiFactory
import com.stepanov.bbf.bugfinder.mutator.transformations.tce.StdLibraryGenerator
import com.stepanov.bbf.bugfinder.project.BBFFile
import com.stepanov.bbf.bugfinder.util.*
import org.apache.log4j.Logger
import org.jetbrains.kotlin.cfg.getDeclarationDescriptorIncludingConstructors
import org.jetbrains.kotlin.descriptors.*
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.psi.psiUtil.isPrivate
import org.jetbrains.kotlin.psi.psiUtil.parents
import org.jetbrains.kotlin.resolve.descriptorUtil.parentsWithSelf
import org.jetbrains.kotlin.resolve.scopes.getDescriptorsFiltered
import org.jetbrains.kotlin.types.*
import org.jetbrains.kotlin.types.typeUtil.asTypeProjection
import org.jetbrains.kotlin.types.typeUtil.isInterface
import org.jetbrains.kotlin.types.typeUtil.isTypeParameter
import kotlin.random.Random

internal class ClassInstanceGenerator(file: BBFFile) : TypeAndValueParametersGenerator(file) {

    private val log = Logger.getLogger("mutatorLogger")
    private val MAX_DEPTH = 10

    private fun generateInstanceOfLocalClass(
        klDescriptor: ClassDescriptor,
        klAsPSI: KtClassOrObject,
        depth: Int = 0
    ): Pair<PsiElement?, KotlinType>? {
        if (klAsPSI.hasModifier(KtTokens.PRIVATE_KEYWORD)) return null
        val parentClasses = klDescriptor.parentsWithSelf.toList().filterIsInstance<ClassDescriptor>().reversed()
        val res = mutableListOf<String>()
        var curClassAsKType = parentClasses.firstOrNull()?.defaultType ?: return null
        for (i in 0 until parentClasses.size - 1) {
            if (parentClasses[i + 1].isInner) {
                val (instance, resultType) = unsafeGenerateRandomInstanceOfClass(curClassAsKType, depth)
                    ?: return null
                if (instance == null) return null
                res.add(instance.text)
                val childClassType = resultType
                    .memberScope
                    .getDescriptorsFiltered { true }
                    .filterIsInstance<ClassDescriptor>()
                    .find { it.name == parentClasses[i + 1].name } ?: break
                val newArgsAmount = childClassType.defaultType.arguments.size - resultType.arguments.size
                val newTypeArgs =
                    if (newArgsAmount <= 0) listOf()
                    else childClassType.defaultType.arguments.take(newArgsAmount) + resultType.arguments
                curClassAsKType = childClassType.defaultType.replace(newTypeArgs)
            } else {
                res.add("${parentClasses[i].name}")
                curClassAsKType = parentClasses[i + 1].defaultType
            }
        }
        val i = unsafeGenerateRandomInstanceOfClass(curClassAsKType, depth) ?: return null
        res.add(i.first?.text ?: "")
        return psiFactory(file).createExpressionIfPossible(res.joinToString(".")) to curClassAsKType
    }

    fun generateRandomInstanceOfUserClass(
        klOrObjType: KotlinType,
        depth: Int = 0
    ): Pair<PsiElement?, KotlinType?>? =
        generateInstancesOfUserClass(klOrObjType, depth).randomOrNull()

    fun generateInstancesOfUserClass(
        klOrObjType: KotlinType,
        depth: Int = 0
    ): List<Pair<PsiElement?, KotlinType?>?> {
        if (depth > MAX_DEPTH) return listOf()
        val classDescriptor = klOrObjType.constructor.declarationDescriptor as? ClassDescriptor ?: return listOf()
        if (classDescriptor.isFunInterface()) return listOf(generateFunInterfaceInstance(classDescriptor, depth))
        if (classDescriptor.name.asString().trim().isEmpty()) return listOf()
        if (classDescriptor.parentsWithSelf.any { it is FunctionDescriptor }) return listOf()
//        log.debug("generating klass ${classDescriptor.name} depth = $depth")
        if (classDescriptor.kind == ClassKind.OBJECT) {
            val fullName =
                classDescriptor.parentsWithSelf.toList()
                    .filterIsInstance<ClassDescriptor>()
                    .reversed()
                    .joinToString(".") { it.name.asString() }
            return listOf(psiFactory(file).createExpressionIfPossible(fullName) to klOrObjType)
        }
        val psiClassOrObj = classDescriptor.findPsi() as? KtClassOrObject ?: return listOf()
        if (classDescriptor.kind == ClassKind.ENUM_CLASS || classDescriptor.kind == ClassKind.ENUM_ENTRY) {
            return listOf(generateEnumInstance(psiClassOrObj) to klOrObjType)
        }
        if (classDescriptor.constructors.isNotEmpty() &&
            classDescriptor.constructors.all { !it.visibility.isPublicAPI }
        ) return listOf(generateImplementation(
            klOrObjType,
            depth
        ))
        //return null
        if (classDescriptor.kind == ClassKind.ANNOTATION_CLASS) return listOf()
        if (psiClassOrObj.parents.any { it is KtClassOrObject }) {
            return listOf(generateInstanceOfLocalClass(classDescriptor, psiClassOrObj, depth))
        }
        if (psiClassOrObj is KtObjectDeclaration) {
            val expr = psiFactory(file).createExpression(psiClassOrObj.name!!)
            return listOf(expr to klOrObjType)
        }
        if (klOrObjType.isInterface() || klOrObjType.isAbstractClass() || classDescriptor.isSealed())
            return listOf(generateImplementation(
            klOrObjType,
            depth
        ))
        return unsafeGenerateInstancesOfClass(klOrObjType, depth)
    }

    @Deprecated("Use KotlinType")
    fun generateInstancesOfUserClass(
        klOrObj: KtClassOrObject,
        depth: Int = 0
    ): List<Pair<PsiElement?, KotlinType?>?> {
        if (depth > MAX_DEPTH) return listOf()
//        log.debug("generating klass ${klOrObj.name} depth = $depth text = ${klOrObj.text}")
        if (klOrObj.name == null
            || klOrObj.allConstructors.let { it.isNotEmpty() && it.all { it.isPrivate() } }
            || klOrObj.isAnnotation()
            || klOrObj.hasModifier(KtTokens.SEALED_KEYWORD)
        ) return listOf()
        if (klOrObj.hasModifier(KtTokens.ENUM_KEYWORD) || klOrObj is KtEnumEntry) {
            return listOf(generateEnumInstance(klOrObj) to null)
        }
        val classType =
            (klOrObj.getDeclarationDescriptorIncludingConstructors(file.ctx!!) as? ClassDescriptor)?.defaultType
                ?: rtg.generateKTypeForClass(klOrObj)
                ?: return listOf()
        if (klOrObj.parents.any { it is KtClassOrObject }) {
            return listOf(generateInstanceOfLocalClass(
                classType.constructor.declarationDescriptor as ClassDescriptor,
                klOrObj,
                depth
            ))
        }
        if (klOrObj is KtObjectDeclaration) {
            val expr = psiFactory(file).createExpression(klOrObj.name!!)
            return listOf(expr to classType)
        }
        if (classType.isInterface() || classType.isAbstractClass()) return listOf(generateImplementation(
            classType,
            depth
        ))
        return unsafeGenerateInstancesOfClass(classType, depth)
    }

    private fun generateEnumInstance(klOrObj: KtClassOrObject): KtExpression? {
        val klass =
            if (klOrObj is KtEnumEntry) {
                klOrObj.parents.first { it is KtClassOrObject } as? KtClassOrObject
            } else {
                klOrObj
            } ?: klOrObj
        val randomEnum = klass.body?.enumEntries?.randomOrNull() ?: return null
        return psiFactory(file).createExpression("${klass.name}.${randomEnum.name}")
    }

    private fun generateImplementation(implementedType: KotlinType, depth: Int): Pair<PsiElement?, KotlinType>? {
        return generateAnonymousObjectImplementation(implementedType, depth)
    }

    private fun generateFunInterfaceInstance(
        classDescriptor: ClassDescriptor,
        depth: Int
    ): Pair<PsiElement?, KotlinType?>? {
        val typeParams = classDescriptor.declaredTypeParameters
        val newTypeParameters = generateTypeParameters(typeParams)
        if (newTypeParameters.size != classDescriptor.declaredTypeParameters.size) return null
        val typeSubstitutor = TypeSubstitutor.create(
            classDescriptor.declaredTypeParameters
                .withIndex()
                .associateBy({ it.value.typeConstructor }) {
                    TypeProjectionImpl(newTypeParameters[it.index])
                }
        )
        val subClassDescr = classDescriptor.substitute(typeSubstitutor) as? ClassDescriptor
        val substConDescr = (subClassDescr
            ?.unsubstitutedMemberScope
            ?.getDescriptorsFiltered { true }
            ?.lastOrNull {
                it.name.asString().let { it != "toString" && it != "hashCode" && it != "equals" }
            } as? FunctionDescriptor)
            ?: return null

        val numberOfParams = substConDescr.valueParameters.size
        val substitutedValueParamsAndRTV =
            substConDescr.valueParameters.map { it.returnType } + listOf(substConDescr.returnType)
        val substitutedTypeParamsAsString =
            substitutedValueParamsAndRTV
                .joinToString(", ", "<", ">") {
                    it?.getNameWithoutError() ?: "Any"
                }
        val typeParamsForDeclaration =
            substitutedValueParamsAndRTV
                .dropLast(substitutedValueParamsAndRTV.size - typeParams.size)
                .let {
                    if (it.isEmpty()) {
                        ""
                    } else {
                        it.joinToString(", ", "<", ">") {
                            it?.getNameWithoutError() ?: "Any"
                        }
                    }
                }

        val type = "Function$numberOfParams$substitutedTypeParamsAsString"
        val typeAsKotlinType = rtg.generateType(type) ?: return null
        val generatedConstructor = RandomInstancesGenerator(file).generateValueOfType(typeAsKotlinType, depth + 1)
        val instance = "${classDescriptor.name}${typeParamsForDeclaration}$generatedConstructor"
        val psiInstance = psiFactory(file).createExpressionIfPossible(instance) ?: return null
        return psiInstance to subClassDescr.defaultType.replace(newTypeParameters.map { it.asTypeProjection() })
    }

    //TODO fix for interfaces with type parameters
    private fun generateAnonymousObjectImplementation(
        interfaceType: KotlinType,
        depth: Int
    ): Pair<PsiElement?, KotlinType>? {
        val membersToOverride =
            StdLibraryGenerator.getMembersToOverride(interfaceType)
                .filterDuplicatesBy {
                    if (it is FunctionDescriptor) "${it.name}${it.valueParameters.map { it.name.asString() + it.returnType.toString() }}"
                    else (it as PropertyDescriptor).name.asString()
                }
        val res = StringBuilder()
        val name = interfaceType.constructor.declarationDescriptor?.name ?: return null
        res.appendLine("object: $name {")
        for (member in membersToOverride) {
            val memberToString = "$member".substringBefore(':').erase("abstract ")
            if (member is PropertyDescriptor) {
                val rtv = member.type
                val initialValue =
                    RandomInstancesGenerator(file).generateValueOfType(rtv, depth + 1)
                        .let { if (it.isEmpty()) "TODO" else it }
                res.appendLine("override $memberToString: $rtv = $initialValue")
            } else if (member is FunctionDescriptor) {
                val funName = member.name
                val params = member.valueParameters.joinToString(separator = ", ") { param ->
                    "${param.name}: ${param.type}"
                }
                val rtv = member.returnType?.toString() ?: continue
                res.appendLine("override fun $funName($params): $rtv { TODO() }")
            }
        }
        res.appendLine("}")
        return try {
            val expr = psiFactory(file).createObject(res.toString())
            expr to interfaceType
        } catch (e: Exception) {
            null
        }
    }

    private fun unsafeGenerateRandomInstanceOfClass(classType: KotlinType, depth: Int): Pair<PsiElement?, KotlinType>? =
        unsafeGenerateInstancesOfClass(classType, depth).randomOrNull()

    private fun unsafeGenerateInstancesOfClass(classType: KotlinType, depth: Int): List<Pair<PsiElement?, KotlinType>?> {
        val classDescriptor =
            classType.constructor.declarationDescriptor as? ClassDescriptor ?: return listOf()
        if (classDescriptor.kind == ClassKind.OBJECT) {
            return listOf( psiFactory(file).createExpression(classType.name!!) to classType)
        }
        val constructors =
            classDescriptor.constructors.filter { it.visibility.isPublicAPI }
        return constructors.map { constructor ->
            generateWithConstructor(constructor, classType, depth)
        }.filter { it != null }
    }

    private fun generateWithConstructor(constructor: ClassConstructorDescriptor, classType: KotlinType, depth: Int): Pair<PsiElement?, KotlinType>? {
        var newTypeParameters =
            if (classType.arguments.all { !it.type.isTypeParameter() }) {
                classType.arguments.map { it.type }
            } else {
                generateTypeParameters(constructor.typeParameters)
            }
        newTypeParameters = newTypeParameters
            .zip(classType.arguments.map { it.type })
            .map { if (it.second.isTypeParameter()) it.first else it.second }

        if (newTypeParameters.size != constructor.typeParameters.size) return null
        val typeSubstitutor = TypeSubstitutor.create(
            constructor.typeParameters
                .withIndex()
                .associateBy({ it.value.typeConstructor }) {
                    TypeProjectionImpl(newTypeParameters[it.index])
                }
        )
        val substitutedConstructorDescriptor = constructor.substitute(typeSubstitutor) ?: return null
        val classWOTPType = substitutedConstructorDescriptor.returnType
        val generatedValueParams = generateValueParameters(substitutedConstructorDescriptor.valueParameters, depth)
        if (generatedValueParams.size != substitutedConstructorDescriptor.valueParameters.size) return null
        val name = classWOTPType.constructor.declarationDescriptor?.name ?: "$classWOTPType".substringBefore('<')
            .substringBefore('(')
        val numOfTP = constructor.typeParameters.size
        val typeArgs = classWOTPType.arguments.take(numOfTP)
            .let { if (it.isEmpty()) "" else it.joinToString(prefix = "<", postfix = ">") }
        val generatedExp =
            psiFactory(file).createExpressionIfPossible("$name$typeArgs(${generatedValueParams.joinToString()})")
        return generatedExp to classWOTPType
    }


}