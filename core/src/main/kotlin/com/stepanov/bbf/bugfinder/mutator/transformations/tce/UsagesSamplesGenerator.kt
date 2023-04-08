package com.stepanov.bbf.bugfinder.mutator.transformations.tce

import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.generator.targetsgenerators.RandomInstancesGenerator
import com.stepanov.bbf.bugfinder.generator.targetsgenerators.typeGenerators.RandomTypeGenerator
import com.stepanov.bbf.bugfinder.project.BBFFile
import com.stepanov.bbf.bugfinder.project.Project
import com.stepanov.bbf.bugfinder.util.findFunByName
import com.stepanov.bbf.bugfinder.util.getAllPSIChildrenOfType
import com.stepanov.bbf.bugfinder.util.getBoxFuncs
import com.stepanov.bbf.reduktor.parser.PSICreator.psiFactory
import org.jetbrains.kotlin.cfg.getDeclarationDescriptorIncludingConstructors
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.descriptors.FunctionDescriptor
import org.jetbrains.kotlin.descriptors.SimpleFunctionDescriptor
import org.jetbrains.kotlin.load.java.descriptors.JavaClassDescriptor
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.psi.psiUtil.parents
import org.jetbrains.kotlin.resolve.bindingContextUtil.getAbbreviatedTypeOrType
import org.jetbrains.kotlin.resolve.calls.callUtil.getType
import org.jetbrains.kotlin.resolve.descriptorUtil.module
import org.jetbrains.kotlin.resolve.scopes.getDescriptorsFiltered
import org.jetbrains.kotlin.types.KotlinType

object UsagesSamplesGenerator {

    fun generate(
        file: BBFFile,
        project: Project?
    ): List<Triple<KtExpression, String, KotlinType?>> {
        val res = mutableListOf<KtExpression>()
        generateForKlasses(file, project, res)
        val withTypes = generateTypes(file, res.joinToString("\n") { it.text })
        generateForFuncs(file, res, withTypes)
        generateForProps(file, res, withTypes)
        val resToStr = res.joinToString("\n") { it.text }
        return generateTypes(file, resToStr)
    }

    private fun generateForProps(
        file: BBFFile,
        res: MutableList<KtExpression>,
        klassInstances: List<Triple<KtExpression, String, KotlinType?>>
    ) {
        val props = file.psiFile.getAllPSIChildrenOfType<KtProperty>()
            .filter { it.isTopLevel }
            .filter { it.name != null }
        for (p in props) {
            if (p.receiverTypeReference == null) {
                res.add(psiFactory.createExpression(p.name!!))
            }
            val receiverTypeReference = p.receiverTypeReference?.getAbbreviatedTypeOrType(file.ctx!!) ?: continue
            val instance = RandomInstancesGenerator(file).generateValueOfType(receiverTypeReference)
            if (instance.isNotEmpty()) {
                res.add(psiFactory.createExpression("$instance.${p.name}"))
            }
        }
    }


    private fun generateForFuncs(
        file: BBFFile,
        res: MutableList<KtExpression>,
        klassInstances: List<Triple<KtExpression, String, KotlinType?>>
    ) {
        for (func in file.psiFile.getAllPSIChildrenOfType<KtNamedFunction>().filter { it.isTopLevel }) {
//            if (func.name?.startsWith("box") == true) continue
//            val (instance, valueParams) = functionGenerator.generateTopLevelFunInvocation(func)
//            instanceGenerator.generateTopLevelFunctionCall(func) ?: continue
//            val valueArgs =
//                if (instance is KtCallExpression) instance.valueArguments
//                else null
//            for ((valueArg, param) in valueArgs?.zip(valueParams) ?: listOf()) {
//                if (param.typeReference == null) continue
//                val anotherExpr = klassInstances.getValueOfType(param.typeReference!!.text)
//                if (anotherExpr != null && Random.nextBoolean()) {
//                    valueArg.replaceThis(anotherExpr.copy())
//                }
//            }
            val funcDescriptor = func.getDeclarationDescriptorIncludingConstructors(file.ctx!!) as? FunctionDescriptor ?: continue
            RandomInstancesGenerator(file).funInvocationGenerator.generateTopLevelFunInvocation(funcDescriptor)?.let { res.add(it) }
        }
    }

    private fun generateForKlasses(
        file: BBFFile,
        project: Project? = null,
        res: MutableList<KtExpression>
    ): List<KtExpression> {
        val currentModule = file.psiFile.getBoxFuncs()?.first().getDeclarationDescriptorIncludingConstructors(file.ctx!!)?.module
        val userClasses = if (project != null && currentModule != null) {
            StdLibraryGenerator.getUserClassesDescriptorsFromProject(project, currentModule)
        } else {
            val javaClassesFromCurrentModule = file.psiFile.getAllPSIChildrenOfType<KtExpression>()
                .mapNotNull { it.getType(file.ctx!!)?.constructor?.declarationDescriptor }
                .toSet()
                .filter { it is JavaClassDescriptor && it.module == currentModule }
            val classes = file.psiFile.getAllPSIChildrenOfType<KtClassOrObject>()
                .filter { it.name != null }
                .filterNot { it.parents.any { it is KtNamedFunction } }//.filter { it.isTopLevel() }
            val classesDescriptors =
                classes.mapNotNull { it.getDeclarationDescriptorIncludingConstructors(file.ctx!!) as? ClassDescriptor }
            classesDescriptors + javaClassesFromCurrentModule
        }
        for (klassDescriptor in userClasses) {
            val openFuncsAndProps = mutableListOf<String>()
            val genRes =
                RandomInstancesGenerator(file).classInstanceGenerator.generateRandomInstanceOfUserClass(klassDescriptor.defaultType)
                    ?: (null to null)
            val instanceOfKlass = genRes.first ?: continue
            res.add(instanceOfKlass as KtExpression)
            var klassType = genRes.second
            if (klassType == null) {
                klassType = RandomTypeGenerator(file).generateType(instanceOfKlass.text.substringBefore('(')) ?: continue
            }
            filterOpenFuncsAndPropsFromDecl(file, instanceOfKlass, klassType).forEach { openFuncsAndProps.add(it.first) }
            openFuncsAndProps
                .mapNotNull {
                    if (it.startsWith("CoBj"))
                        psiFactory.createExpressionIfPossible("${klassDescriptor.name}.${it.substringAfter("CoBj")}")
                    else
                        psiFactory.createExpressionIfPossible(it)
                }
                .forEach { res.add(it) }
        }
        return res
    }

    private fun filterOpenFuncsAndPropsFromDecl(
        file: BBFFile,
        parentInstance: PsiElement,
        classType: KotlinType
    ): List<Pair<String, KotlinType?>> {
        val openFuncsAndProps = mutableListOf<Pair<String, KotlinType?>>()
        for (desc in classType.memberScope.getDescriptorsFiltered { true }) {
            if (desc is SimpleFunctionDescriptor && desc.visibility.isPublicAPI && desc.extensionReceiverParameter == null) {
                if (desc.name.asString() !in noNeedFunctions)
                    RandomInstancesGenerator(file).generateFunctionCall(desc)?.let {
                        openFuncsAndProps.add(
                            "${parentInstance.text}.${it.text}" to desc.returnType
                        )
                    }
            }
//            else if (desc is PropertyDescriptor && desc.visibility.isPublicAPI) {
//                openFuncsAndProps.add("${parentInstance.text}.${desc.name}" to desc.type)
//            }
        }
        return openFuncsAndProps
    }

    private fun generateTypes(file: BBFFile, resToStr: String): List<Triple<KtExpression, String, KotlinType?>> {
        val func = psiFactory.createFunction("fun usageExamples(){\n$resToStr\n}")
        val newFile = psiFactory.createFile(file.text + "\n\n" + func.text)
        val ctx = file.updateCtx() ?: return listOf()
        val myFunc = newFile.findFunByName("usageExamples")!!
        return myFunc.getAllPSIChildrenOfType<KtExpression>()
            .filter { it.parent == myFunc.bodyBlockExpression }
            .map { Triple(it, it.text, it.getType(ctx)) }
            .filter { it.third != null }
    }

    private fun List<Triple<KtExpression, String, KotlinType?>>.getValueOfType(type: String): KtExpression? =
        this.filter { it.third?.let { it.toString() == type || it.toString() == type.substringBefore('?') } ?: false }
            .randomOrNull()?.first

    private val noNeedFunctions = listOf("equals", "hashCode", "toString")
}