package com.stepanov.bbf.bugfinder.mutator.transformations.util

import com.stepanov.bbf.bugfinder.generator.targetsgenerators.ClassInvocator
import com.stepanov.bbf.bugfinder.generator.targetsgenerators.FunInvocator
import com.stepanov.bbf.bugfinder.mutator.transformations.FTarget
import com.stepanov.bbf.bugfinder.project.BBFFile
import com.stepanov.bbf.bugfinder.util.*
import com.stepanov.bbf.reduktor.parser.PSICreator.psiFactory
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.js.descriptorUtils.getJetTypeFqName
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.psi.psiUtil.getParentOfType
import org.jetbrains.kotlin.psi.psiUtil.isPublic
import org.jetbrains.kotlin.types.isNullable
import kotlin.random.Random

object Invocator {

    fun addInvocationOfAllCallable(target: FTarget) {
        val mainFile = target.project.mainFile
        val klibFile = target.project.klib
        mainFile.psiFile = psiFactory.createFile(klibFile.text)
        mainFile.updateCtx()

        klibFile.psiFile.getAllPSIChildrenOfType<KtClassOrObject>().map {
            klibFile.getDescriptorByKtClass(it)
        }.filterNotNull().forEach { descriptor ->
            val instances = ClassInvocator(mainFile).generateInstances(descriptor).map {
                "val ${Random.getRandomVariableName()}: " +
                        "${descriptor.defaultType.getTypeName()} = $it"
            }
            writeToMain(mainFile, instances)
            val randomInstance = instances.randomOrNull()?.substringAfter("val ")?.substringBefore(":")
            if (randomInstance != null) {
                descriptor.getAllSuperClassifiersWithoutAnyAndItself().map {
                    "val ${Random.getRandomVariableName()}: " +
                            "${it.defaultType.getTypeName()} = $randomInstance"
                }.toList().let {
                    writeToMain(mainFile, it)
                }
            }
        }
        klibFile.psiFile.getAllPSIChildrenOfType<KtFunction>()
            .filter { it !is KtConstructor<*> && it.name != "main"}
            .map {
                klibFile.getDescriptorByKtFunction(it)
            }.filterNotNull().forEach { descriptor ->
                val instances = FunInvocator(mainFile).invokeFunction(descriptor).map {
                    val type = descriptor.returnType?.getTypeName() ?: "Nothing"
                    "val ${Random.getRandomVariableName()}: $type = $it"
                }
                writeToMain(mainFile, instances)
            }
        klibFile.psiFile.getAllPSIChildrenOfType<KtClassOrObject>()
            .map {
                 klibFile.getDescriptorByKtClass(it)
            }.filterNotNull().forEach { descriptor ->
                val invocations = invokeProperties(descriptor, mainFile)
                writeToMain(mainFile, invocations)
            }
        klibFile.psiFile.getAllPSIChildrenOfType<KtProperty>().filter {
            it.getParentOfType<KtClassOrObject>(true) == null &&
                    it.isPublic
        }.forEach { property ->
            val kotlinType = property.getType(klibFile.ctx!!)
            val type = if (kotlinType == null)
                ""
            else
                kotlinType.getJetTypeFqName(true) + if (kotlinType.isNullable()) "?" else ""
            val callExpression = property.name ?: "Nothing"
            val invocations = mutableListOf<String>()
            invocations.add("val ${Random.getRandomVariableName()}: $type = $callExpression")
            if (property.isVar) {
                invocations.add("$callExpression = TODO()")
            }
            writeToMain(mainFile, invocations)
        }

        mainFile.psiFile =
            psiFactory.createFile("fun main()${mainFile.text.substringAfter("fun main()")}")
    }

    private fun writeToMain(file: BBFFile, invocations: List<String>) {
        var text = file.text
        if (!text.contains("fun main()")) {
            text += "\nfun main() {\n}"
        }
        val klib = text.substringBefore("fun main()")
        val mainText = text.substringAfter("fun main()")
            .substringAfter("{").substringBeforeLast("}")
        val fileText = invocations
            .joinToString(separator = "\n", prefix = "${klib}fun main() {$mainText", postfix = "\n}")
        val newKtFile = psiFactory.createFile(fileText)
        file.psiFile = newKtFile
        file.updateCtx()
    }

    fun invokeProperties(descriptor: ClassDescriptor, file: BBFFile): List<String> {
        val properties = descriptor.defaultType.getPublicProperties()
        val parent = ClassInvocator(file).randomClassInvocation(descriptor)
        val invocations = mutableListOf<String>()
        properties.forEach { property ->
            val type = property.type.getTypeName()
            val outerProperty = if (parent.isNotBlank())
                parent + "."
            else
                ""
            val callExpression = "${outerProperty}${property.name.asString()}"
            invocations.add("val ${Random.getRandomVariableName()}: $type = $callExpression")
            if (property.isVar) {
                invocations.add("$callExpression = TODO()")
            }
        }
        return invocations
    }

}