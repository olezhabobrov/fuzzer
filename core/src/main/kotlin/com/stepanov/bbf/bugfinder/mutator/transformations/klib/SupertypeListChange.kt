package com.stepanov.bbf.bugfinder.mutator.transformations.klib

import com.stepanov.bbf.bugfinder.generator.targetsgenerators.ClassInvocator
import com.stepanov.bbf.bugfinder.generator.targetsgenerators.FunInvocator
import com.stepanov.bbf.bugfinder.mutator.transformations.FTarget
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GClass
import com.stepanov.bbf.bugfinder.mutator.transformations.tce.StdLibraryGenerator
import com.stepanov.bbf.bugfinder.project.BBFFile
import com.stepanov.bbf.bugfinder.util.filterDuplicatesBy
import com.stepanov.bbf.bugfinder.util.findPsi
import com.stepanov.bbf.bugfinder.util.name
import com.stepanov.bbf.bugfinder.util.supertypesWithoutAny
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import com.stepanov.bbf.reduktor.util.replaceThis
import com.stepanov.bbf.reduktor.util.uniqueString
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.descriptors.ClassKind
import org.jetbrains.kotlin.descriptors.Modality
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.types.typeUtil.supertypes

class AddSupertype: BinaryCompatibleTransformation(1) {
    override fun transform(target: FTarget) {
        val file = target.file
        val newSupertype = file.psiFile.getAllPSIChildrenOfType<KtClass>()
            .map { file.getDescriptorByKtClass(it) }
            .filterNotNull()
            .filter { it.containingDeclaration !is ClassDescriptor }
            .filter { it.modality != Modality.FINAL }
            .randomOrNull() ?: return
        val clazz = file.psiFile.getAllPSIChildrenOfType<KtClassOrObject>()
            .map { file.getDescriptorByKtClass(it) }
            .filterNotNull()
            .filter {
                it.defaultType.supertypes().all { it.name != newSupertype.name.asString() }
            }
            .filter { it.name.asString() != newSupertype.name.asString() }
            .filter {
                if (newSupertype.kind == ClassKind.CLASS)
                    it.kind == ClassKind.CLASS
                else
                    true
            }
            .filter {
                if (newSupertype.kind == ClassKind.INTERFACE)
                    true
                else {
                    !it.defaultType.supertypesWithoutAny().any {
                        val descriptor = it.constructor.declarationDescriptor as? ClassDescriptor
                        if (descriptor == null)
                            true
                        else
                            descriptor.kind == ClassKind.CLASS
                    }
                }
            }.randomOrNull() ?: return
        if (clazz.modality != Modality.ABSTRACT)
            implementAbstractMembers(file, clazz, newSupertype)
        val oldPsi = clazz.findPsi() as? KtClassOrObject ?: return
        val gclass = GClass.fromPsi(oldPsi)
        val invocation = ClassInvocator(file).randomConstructorInvocation(newSupertype)
        if (invocation.isBlank())
            return
        gclass.supertypes.add(invocation)
        val newPsi = gclass.toPsi() ?: return

        oldPsi.replaceThis(newPsi)
    }

    private fun implementAbstractMembers(file: BBFFile, clazz: ClassDescriptor, supertype: ClassDescriptor) {
        val classMembers = StdLibraryGenerator.getMembersToOverride(clazz.defaultType)
        val membersToOverride = StdLibraryGenerator.getMembersToOverride(supertype.defaultType)
            .filter { descr -> classMembers.all { it.uniqueString() != descr.uniqueString() } }
        membersToOverride.forEach {
            FunInvocator(file).implementMember(it, clazz)
        }
    }
}