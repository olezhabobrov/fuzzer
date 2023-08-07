package com.stepanov.bbf.bugfinder.mutator.transformations.klib

import com.stepanov.bbf.bugfinder.generator.targetsgenerators.ClassInvocator
import com.stepanov.bbf.bugfinder.generator.targetsgenerators.FunInvocator
import com.stepanov.bbf.bugfinder.generator.targetsgenerators.typeGenerators.RandomTypeGenerator
import com.stepanov.bbf.bugfinder.mutator.transformations.FTarget
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GConstructor
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GParameter
import com.stepanov.bbf.bugfinder.util.addPsiToBody
import com.stepanov.bbf.bugfinder.util.findPsi
import com.stepanov.bbf.bugfinder.util.getAllPSIChildrenOfType
import com.stepanov.bbf.bugfinder.util.getTrue
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.descriptors.ClassKind
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtPrimaryConstructor
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
        val args =
                MutableList(Random.nextInt(1, 5)) {
                    val t = RandomTypeGenerator(file).generateRandomTypeWithCtx() ?: return
                    val param = GParameter()
                    param.name = "${'a' + it}"
                    param.type = t.toString()
                    val decCon = t.constructor.declarationDescriptor as? ClassDescriptor
                    if (Random.getTrue(10) && decCon != null) {
                        param.defaultValue = ClassInvocator(file).randomClassInvocation(decCon)
                    }
                    param
                }
        gconstructor.argsParams = args
        val psi = gconstructor.toPsi()
        classPsi.addPsiToBody(psi)
    }
}