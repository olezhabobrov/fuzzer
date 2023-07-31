package com.stepanov.bbf.bugfinder.mutator.transformations.klib

import com.stepanov.bbf.bugfinder.generator.targetsgenerators.FunInvocator
import com.stepanov.bbf.bugfinder.mutator.transformations.FTarget
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GStructure
import com.stepanov.bbf.bugfinder.mutator.transformations.tce.StdLibraryGenerator
import com.stepanov.bbf.bugfinder.util.addPsiToBody
import com.stepanov.bbf.bugfinder.util.findPsi
import com.stepanov.bbf.reduktor.parser.PSICreator.psiFactory
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import com.stepanov.bbf.reduktor.util.remove
import com.stepanov.bbf.reduktor.util.replaceThis
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.descriptors.ClassKind
import org.jetbrains.kotlin.descriptors.Modality
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.KtFunction
import org.jetbrains.kotlin.psi.KtProperty
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
        FunInvocator(file).implementMember(member, clazz)
    }
}

class RemoveDefaultImplementation: BinaryIncompatibleTransformation(1) {
    override fun transform(target: FTarget) {
        val file = target.file
        val clazz = file.getAllClassDescriptors()
            .filter { it.modality == Modality.ABSTRACT }
            .randomOrNull() ?: return
        val clazzPsi = clazz.findPsi() as? KtClassOrObject ?: return
        val (psi, gstructure) = (clazzPsi.getAllPSIChildrenOfType<KtFunction>() +
                clazzPsi.getAllPSIChildrenOfType<KtProperty>())
            .map { it to GStructure.fromPsi(it) }
            .filter { it.second.isImplemented() }
            .randomOrNull() ?: return
        if (gstructure.modifiers.contains("override")) {
            psi.remove()
        } else {
            gstructure.removeDefaultImplementation()
            if (clazz.kind == ClassKind.CLASS)
                gstructure.addAbstract()
            val newPsi = gstructure.toPsi() ?: return
            psi.replaceThis(newPsi)
        }
    }
}