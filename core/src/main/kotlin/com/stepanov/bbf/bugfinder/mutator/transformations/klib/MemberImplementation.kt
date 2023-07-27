package com.stepanov.bbf.bugfinder.mutator.transformations.klib

import com.stepanov.bbf.bugfinder.mutator.transformations.FTarget
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GStructure
import com.stepanov.bbf.bugfinder.mutator.transformations.tce.StdLibraryGenerator
import com.stepanov.bbf.bugfinder.util.addPsiToBody
import com.stepanov.bbf.bugfinder.util.findPsi
import com.stepanov.bbf.reduktor.parser.PSICreator.psiFactory
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import com.stepanov.bbf.reduktor.util.replaceThis
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.descriptors.ClassKind
import org.jetbrains.kotlin.descriptors.Modality
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner
import org.jetbrains.kotlin.psi.psiUtil.getParentOfType

class AddImplementationOfMember: BinaryCompatibleTransformation(1) {
    override fun transform(target: FTarget) {
        val file = target.file
        val clazz = file.psiFile.getAllPSIChildrenOfType<KtClass>()
            .map { file.getDescriptorByKtClass(it) }
            .filterNotNull()
            .filter { it.containingDeclaration !is ClassDescriptor }
            .filter { it.modality == Modality.ABSTRACT }
            .filter { it.visibility.isPublicAPI }
            .randomOrNull() ?: return
        val member = StdLibraryGenerator.getMembersToOverride(clazz.defaultType)
            .filter {
                val psi = it.findPsi() as? KtTypeParameterListOwner
                if (psi == null)
                    false
                else {
                    if (psi.getParentOfType<KtClass>(true)?.name != clazz.name.asString())
                        true
                    else
                        GStructure.fromPsi(psi).isNotImplemented()
                }
            }
            .randomOrNull() ?: return
        val psi = member.findPsi() as? KtTypeParameterListOwner ?: return
        val gmember = GStructure.fromPsi(psi)
        gmember.addDefaultImplementation()
        if (clazz.kind == ClassKind.CLASS)
            gmember.addOpen()
        if (psi.getParentOfType<KtClass>(true)?.name == clazz.name.asString()) {
            val newPsi = gmember.toPsi() ?: return
            psi.replaceThis(newPsi)
        } else {
            gmember.addOverride()
            val classPsi = clazz.findPsi() as? KtClass ?: return
            val newPsi = gmember.toPsi() ?: return
            classPsi.addPsiToBody(psiFactory.createWhiteSpace("\n\n"))
            classPsi.addPsiToBody(newPsi)
        }
    }
}

class RemoveDefaultImplementation: BinaryIncompatibleTransformation(1) {
    override fun transform(target: FTarget) {
        TODO("Not yet implemented")
    }

}