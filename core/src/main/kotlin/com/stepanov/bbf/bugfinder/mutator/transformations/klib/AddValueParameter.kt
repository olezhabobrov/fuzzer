package com.stepanov.bbf.bugfinder.mutator.transformations.klib

import com.stepanov.bbf.bugfinder.generator.targetsgenerators.ClassInvocator
import com.stepanov.bbf.bugfinder.generator.targetsgenerators.typeGenerators.RandomTypeGenerator
import com.stepanov.bbf.bugfinder.mutator.transformations.FTarget
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GConstructor
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GFunction
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GParameter
import com.stepanov.bbf.bugfinder.project.BBFFile
import com.stepanov.bbf.bugfinder.util.classDescriptor
import com.stepanov.bbf.bugfinder.util.getRandomVariableName
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import com.stepanov.bbf.reduktor.util.replaceThis
import org.jetbrains.kotlin.psi.KtConstructor
import org.jetbrains.kotlin.psi.KtFunction
import org.jetbrains.kotlin.psi.psiUtil.isPublic
import kotlin.random.Random

class AddValueParameter: BinaryIncompatibleTransformation(1) {
    override fun transform(target: FTarget) {
        addParameter(target.file, false)
    }
}

class AddValueParameterWithDefaultValue: BinaryCompatibleTransformation(1) {
    override fun transform(target: FTarget) {
        addParameter(target.file, true)
    }
}

private fun getAllFunctions(file: BBFFile) =
    file.psiFile.getAllPSIChildrenOfType<KtFunction>()
        .filter { it.isPublic }

private fun generateParameter(file: BBFFile, withDefault: Boolean): GParameter =
    GParameter().also {
        with(it) {
            name = Random.getRandomVariableName()
            type = RandomTypeGenerator(file).getPublicType()
            if (withDefault) {
                val ktype = RandomTypeGenerator(file).generateType(type)
                val descriptor = ktype?.classDescriptor()
                if (ktype == null || descriptor == null)
                    defaultValue = "TODO()"
                else
                    defaultValue = ClassInvocator(file).randomClassInvocation(descriptor)
            }
        }
    }

private fun addParameter(file: BBFFile, withDefault: Boolean) {
    val func = getAllFunctions(file).random()
    val param = generateParameter(file, withDefault)
    if (func is KtConstructor<*>) {
        val gcon = GConstructor.fromPsi(func)
        gcon.argsParams.add(param)
        func.replaceThis(gcon.toPsi())
    } else {
        val gfun = GFunction.fromPsi(func)
        gfun.argsParams.add(param)
        func.replaceThis(gfun.toPsi())
    }
}
