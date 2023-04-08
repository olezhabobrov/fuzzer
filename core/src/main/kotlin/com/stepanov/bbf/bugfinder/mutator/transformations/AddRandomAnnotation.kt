package com.stepanov.bbf.bugfinder.mutator.transformations

import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.generator.targetsgenerators.RandomInstancesGenerator
import com.stepanov.bbf.bugfinder.mutator.transformations.tce.StdLibraryGenerator
import com.stepanov.bbf.bugfinder.project.BBFFile
import com.stepanov.bbf.bugfinder.util.addImport
import com.stepanov.bbf.bugfinder.util.addToTheTop
import com.stepanov.bbf.bugfinder.util.getAllPSIChildrenOfTwoTypes
import com.stepanov.bbf.reduktor.parser.PSICreator.psiFactory
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import org.jetbrains.kotlin.backend.common.serialization.findPackage
import org.jetbrains.kotlin.descriptors.ClassKind
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.psi.psiUtil.parents
import org.jetbrains.kotlin.resolve.descriptorUtil.classId
import org.jetbrains.kotlin.types.KotlinType
import kotlin.random.Random

class AddRandomAnnotation: Transformation(20) {


    private val annClassesFromLib =
        StdLibraryGenerator.klasses
            .filter {
                it.kind == ClassKind.ANNOTATION_CLASS
                        && it.visibility.isPublicAPI
                        && !it.findPackage().toString().contains("js")
            }

    private fun insert(file: BBFFile, t: PsiElement, type: KotlinType?, kl: KtClass?) {
//        log.debug("trying to insert ${type ?: kl!!.name}")
        val randomInstancesGenerator = RandomInstancesGenerator(file)
        val instance =
            type?.let { randomInstancesGenerator.generateValueOfType(type) }
                ?: randomInstancesGenerator.generateRandomInstanceOfClass(kl!!)?.first?.text
        //println("instance = $instance")
        if (instance == null || instance.isEmpty()) return
        val newNode: PsiElement
        if (t == file.psiFile) {
            val psiInstance = psiFactory.createFileAnnotation(instance)
            newNode = file.psiFile.addToTheTop(psiInstance)
        } else {
            val psiInstance = psiFactory.createAnnotationEntry("@$instance")
            val psiWhiteSpace = psiFactory.createWhiteSpace("\n")
            newNode = t.addAfter(psiInstance, null)
            newNode.addBefore(psiWhiteSpace, null)
        }
    }

    fun tryToInsertAnn(file: BBFFile) {
        val annClassFromFile = file.psiFile.getAllPSIChildrenOfType<KtClass>().filter { it.isAnnotation() }.randomOrNull()
        if (annClassFromFile != null && Random.nextBoolean()) {
            val target =
                annClassFromFile.annotationEntries
                    .find { it.shortName?.asString() == "Target" }
                    ?.valueArguments
                    ?.mapNotNull {
                        it.asElement().text?.substringAfter('.')
                    } ?: possibleTargets
            val potTargets = target
                .flatMap { getTargetsByTarget(file, it) }
                .toSet()
                .filter { it != annClassFromFile && !it.parents.contains(annClassFromFile) }
            //if (potTargets.size > 10) potTargets = potTargets.filter { Random.nextBoolean() }
            insert(file, potTargets.random(), null, annClassFromFile)
            return
        }

        val annClassFromLib = annClassesFromLib.random()
        val classId = annClassFromLib.classId?.packageFqName?.asString()
        if (classId != null && classId != "kotlin") {
            (file.psiFile as KtFile).addImport(annClassFromLib.classId!!.asString().replace('/', '.').substringBeforeLast('.'), true)
        }
        val targetValueArgs =
            annClassFromLib.annotations.findAnnotation(FqName("kotlin.annotation.Target"))?.allValueArguments
        val target = targetValueArgs?.values?.firstOrNull()?.value as List<*>? ?: possibleTargets
        val potTargets = target.flatMap { getTargetsByTarget(file, it.toString().substringAfter('.')) }
        val randomTarget = potTargets.randomOrNull() ?: return
        insert(file, randomTarget, annClassFromLib.defaultType, null)
        return
    }

    override fun transform(target: FTarget) {
        tryToInsertAnn(target.file)
    }


    private fun getTargetsByTarget(file: BBFFile, target: String): List<PsiElement> =
        when (target) {
            "CLASS" -> file.psiFile.getAllPSIChildrenOfType<KtClass>()
            "ANNOTATION_CLASS" -> file.psiFile.getAllPSIChildrenOfType<KtClass>().filter { it.isAnnotation() }
            "TYPE_PARAMETER" -> file.psiFile.getAllPSIChildrenOfType<KtTypeParameter>()
            "PROPERTY" -> file.psiFile.getAllPSIChildrenOfType<KtProperty>()
            "FIELD" -> file.psiFile.getAllPSIChildrenOfType<KtClass>().flatMap { it.getProperties() }
            "LOCAL_VARIABLE" -> file.psiFile.getAllPSIChildrenOfType<KtNamedFunction>()
                .flatMap { it.getAllPSIChildrenOfType<KtProperty>() }
            "VALUE_PARAMETER" -> file.psiFile.getAllPSIChildrenOfType<KtValueArgument>()
            "CONSTRUCTOR" -> file.psiFile.getAllPSIChildrenOfTwoTypes<KtPrimaryConstructor, KtSecondaryConstructor>()
            "FUNCTION" -> file.psiFile.getAllPSIChildrenOfType<KtNamedFunction>().filter { it.isTopLevel }
            "PROPERTY_GETTER" -> file.psiFile.getAllPSIChildrenOfType<KtProperty>().mapNotNull { it.getter }
            "PROPERTY_SETTER" -> file.psiFile.getAllPSIChildrenOfType<KtProperty>().mapNotNull { it.setter }
            "TYPE" -> file.psiFile.getAllPSIChildrenOfType<KtTypeReference>()
            "EXPRESSION" -> file.psiFile.getAllPSIChildrenOfType<KtExpression>()
            "FILE" -> listOf(file.psiFile)
            "TYPEALIAS" -> file.psiFile.getAllPSIChildrenOfType<KtTypeAlias>()
            else -> listOf()
        }

    private val possibleTargets = listOf(
        "CLASS", "ANNOTATION_CLASS", "TYPE_PARAMETER", "PROPERTY",
        "FIELD", "LOCAL_VARIABLE", "VALUE_PARAMETER", "CONSTRUCTOR", "FUNCTION",
        "PROPERTY_GETTER", "PROPERTY_SETTER", "TYPE", "EXPRESSION", "FILE", "TYPEALIAS"
    )

}