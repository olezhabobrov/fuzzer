package com.stepanov.bbf.bugfinder.mutator.transformations.klib

import com.stepanov.bbf.bugfinder.generator.targetsgenerators.ClassInvocator
import com.stepanov.bbf.bugfinder.generator.targetsgenerators.ConstructorGenerator
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
        ConstructorGenerator(file).generateAndAddSecondaryConstructor(clazz)
    }
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
        ConstructorGenerator(file).generateAndAddPrimaryConstructor(clazz)
    }

}