package com.stepanov.bbf.bugfinder.mutator.transformations.klib

import com.stepanov.bbf.bugfinder.generator.targetsgenerators.KlibClassGenerator
import com.stepanov.bbf.bugfinder.mutator.transformations.FTarget
import com.stepanov.bbf.bugfinder.util.getTrue
import com.stepanov.bbf.reduktor.parser.PSICreator
import org.jetbrains.kotlin.descriptors.ClassKind
import kotlin.random.Random

class AddFunInterface: BinaryCompatibleTransformation(1) {
    override fun transform(target: FTarget) {
        val file = target.file
        val interfaceWithAbstract = file.getAllClassDescriptors()
            .filter { it.kind == ClassKind.INTERFACE && it.getAbstractMembers().size == 1}
            .randomOrNull()
        val newEntity = if (interfaceWithAbstract != null && Random.getTrue(30)) {
            KlibClassGenerator(file).generateFunInterface(interfaceWithAbstract.name.asString())
        } else {
            KlibClassGenerator(file).generateFunInterface(null)
        } ?: return
        file.psiFile.add(PSICreator.psiFactory.createWhiteSpace("\n"))
        file.psiFile.add(newEntity)
    }
}