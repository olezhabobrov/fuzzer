package com.stepanov.bbf.bugfinder.mutator.transformations

import com.stepanov.bbf.bugfinder.mutator.MutationProcessor
import com.stepanov.bbf.bugfinder.project.BBFFile
import com.stepanov.bbf.bugfinder.util.getAllPSIChildrenOfType
import com.stepanov.bbf.reduktor.parser.PSICreator.psiFactory
import org.jetbrains.kotlin.psi.KtTypeReference

class AddNullabilityTransformer: Transformation(2) {

    override fun transform(target: FTarget) {
        val file = target.file
        file.psiFile.getAllPSIChildrenOfType<KtTypeReference>()
            .filterNot { it.textContains('?') }
            .random()
            .let { addNullability(file, it) }
    }

    fun addNullability(file: BBFFile, ref: KtTypeReference) {
        val newRef = psiFactory.createTypeIfPossible("(${ref.typeElement?.text})?") ?: return
        MutationProcessor.replaceNodeReturnNode(ref, newRef, file)
    }

}