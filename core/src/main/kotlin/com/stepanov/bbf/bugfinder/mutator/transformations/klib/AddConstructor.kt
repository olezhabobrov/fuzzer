package com.stepanov.bbf.bugfinder.mutator.transformations.klib

import com.stepanov.bbf.bugfinder.generator.targetsgenerators.ClassInvocator
import com.stepanov.bbf.bugfinder.generator.targetsgenerators.FunInvocator
import com.stepanov.bbf.bugfinder.generator.targetsgenerators.typeGenerators.RandomTypeGenerator
import com.stepanov.bbf.bugfinder.mutator.transformations.FTarget
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GConstructor
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GParameter
import com.stepanov.bbf.bugfinder.project.BBFFile
import com.stepanov.bbf.bugfinder.util.addPsiToBody
import com.stepanov.bbf.bugfinder.util.findPsi
import com.stepanov.bbf.bugfinder.util.getTrue
import com.stepanov.bbf.bugfinder.util.replaceThis
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.descriptors.ClassKind
import org.jetbrains.kotlin.psi.*
import kotlin.random.Random

class AddSecondaryConstructor: BinaryCompatibleTransformation(1) {
    override fun transform(target: FTarget) {
        val file = target.file
        val clazz = file.getAllClassDescriptors()
            .filter { it.kind == ClassKind.CLASS }
            .randomOrNull() ?: return
        val gconstructor = GConstructor()
        val classPsi = clazz.findPsi() as? KtClass ?: return
        val hasPrimary = classPsi.getAllPSIChildrenOfType<KtPrimaryConstructor>().isNotEmpty()
        if (hasPrimary) {
            val primaryConstructor = clazz.constructors.find { it.isPrimary } ?: return
            val primaryConstructorInvocation =
                FunInvocator(file).invokeParameterBrackets(primaryConstructor).randomOrNull() ?: return
            gconstructor.delegationCalls.add("this$primaryConstructorInvocation")
        }
        val args = generateArgs(file)
        gconstructor.argsParams = args
        val psi = gconstructor.toPsi()
        classPsi.addPsiToBody(psi)
    }
}

private fun generateArgs(file: BBFFile) =
    MutableList(Random.nextInt(1, 5)) {
        val t = RandomTypeGenerator(file).generateRandomTypeWithCtx() ?: return mutableListOf<GParameter>()
        val param = GParameter()
        param.name = "${'a' + it}"
        param.type = t.toString()
        val decCon = t.constructor.declarationDescriptor as? ClassDescriptor
        if (Random.getTrue(10) && decCon != null) {
            param.defaultValue = ClassInvocator(file).randomClassInvocation(decCon)
        }
        param
    }

class AddPrimaryConstructor: BinaryCompatibleTransformation(1) {
    override fun transform(target: FTarget) {
        val file = target.file
        val clazz = file.getAllClassDescriptors()
            .filter { it.kind == ClassKind.CLASS }
            .filter { descr ->
                val primaryConstructor = descr.constructors.find { it.isPrimary }
                if (primaryConstructor == null)
                    true
                else {
                    val psi = primaryConstructor.findPsi() as? KtConstructor<*>
                    psi?.getAllPSIChildrenOfType<KtParameter>()?.all {
                        it.valOrVarKeyword == null
                    } ?: true
                }
            }
            .randomOrNull() ?: return
        val classPsi = clazz.findPsi() as? KtClass ?: return
        val hasPrimary = clazz.constructors.any { it.isPrimary }
        if (hasPrimary) {
            val primaryConstructor = clazz.constructors.find { it.isPrimary } ?: return
            val primaryConstructorPsi = primaryConstructor.findPsi() as? KtConstructor<*>
                ?: classPsi.createPrimaryConstructorIfAbsent()
            val newPrimConstructor = GConstructor()
            newPrimConstructor.argsParams = generateArgs(file)
            newPrimConstructor.isPrimary = true

            primaryConstructorPsi.replaceThis(newPrimConstructor.toPsi())

            val oldPrimConstructor = GConstructor.fromPsi(primaryConstructorPsi)
            oldPrimConstructor.isPrimary = false
            val delegationCall = mutableListOf<String>().also {  params ->
                newPrimConstructor.argsParams.forEach {
                    params.add("TODO() as ${it.type}")
                }
            }.joinToString(separator = ", ", prefix = "this(", postfix = ")")
            oldPrimConstructor.delegationCalls.add(delegationCall)
            classPsi.addPsiToBody(oldPrimConstructor.toPsi())
        } else {
            val newPrimConstructor = GConstructor()
            newPrimConstructor.argsParams = generateArgs(file)
            newPrimConstructor.isPrimary = true
            clazz.constructors.forEach { descr ->
                val psi = descr.findPsi() as? KtConstructor<*>
                if (psi != null) {
                    val gcon = GConstructor.fromPsi(psi)
                    if (gcon.delegationCalls.isEmpty()) {
                        val delegationCall = mutableListOf<String>().also {  params ->
                            newPrimConstructor.argsParams.forEach {
                                params.add("TODO() as ${it.type}")
                            }
                        }.joinToString(separator = ", ", prefix = "this(", postfix = ")")
                        gcon.delegationCalls.add(delegationCall)
                        psi.replaceThis(gcon.toPsi())
                    }
                }
            }
            classPsi.createPrimaryConstructorIfAbsent().replaceThis(newPrimConstructor.toPsi())
        }
    }

}