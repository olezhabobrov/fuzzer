package com.stepanov.bbf.bugfinder.mutator.transformations.klib

import com.stepanov.bbf.bugfinder.generator.targetsgenerators.ClassInvocator
import com.stepanov.bbf.bugfinder.generator.targetsgenerators.FunInvocator
import com.stepanov.bbf.bugfinder.mutator.transformations.FTarget
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GClass
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GStructure
import com.stepanov.bbf.bugfinder.mutator.transformations.filterDuplicates
import com.stepanov.bbf.bugfinder.mutator.transformations.tce.StdLibraryGenerator
import com.stepanov.bbf.bugfinder.project.BBFFile
import com.stepanov.bbf.bugfinder.util.*
import com.stepanov.bbf.reduktor.parser.PSICreator.psiFactory
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import com.stepanov.bbf.reduktor.util.replaceThis
import com.stepanov.bbf.reduktor.util.uniqueString
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.descriptors.ClassKind
import org.jetbrains.kotlin.descriptors.Modality
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.KtFunction
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.types.typeUtil.supertypes
import kotlin.collections.flatMap
import kotlin.random.Random

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

class RemoveSupertypes: BinaryIncompatibleTransformation(1) {
    override fun transform(target: FTarget) {
        val file = target.file
        val clazz = file.getAllClassDescriptors().filter {
            it.defaultType.supertypesWithoutAny().isNotEmpty()
        }.randomOrNull() ?: return
        val deletedSupertype = clazz.defaultType.supertypesWithoutAny().random()
        val otherMembers = clazz.defaultType.supertypesWithoutAny().filter { it != deletedSupertype }
            .flatMap { StdLibraryGenerator.getMembersToOverride(it) }
            .map { it.uniqueString() }
            .filterDuplicatesBy { it }
        val allMembersOfDeleted = StdLibraryGenerator.getMembersToOverride(deletedSupertype)
            .map {it.uniqueString()}
        val toDelete = clazz.defaultType.getMembers().filter { declaration ->
            (declaration.uniqueString() in allMembersOfDeleted
                && declaration.uniqueString() !in otherMembers)
        }.map { it.name }
        val psi = clazz.findPsi() as? KtClassOrObject ?: return
        val psiToDelete = (psi.getAllPSIChildrenOfType<KtFunction>() +
                psi.getAllPSIChildrenOfType<KtProperty>())
            .filter {it.nameAsName in toDelete }
        psiToDelete.forEach { member ->
            // either delete member or delete override modifier
            if (Random.nextBoolean()) {
                member.replaceThis(psiFactory.createWhiteSpace(""))
            } else {
                val gStructure = GStructure.fromPsi(member)
                gStructure.removeOverride()
                if (clazz.modality != Modality.FINAL) {
                    if (Random.nextBoolean()) {
                        if (gStructure.isImplemented())
                            gStructure.addOpen()
                        else
                            gStructure.addAbstract()
                    }
                }
                val newPsi = gStructure.toPsi() ?: return@forEach
                member.replaceThis(newPsi)
            }
        }
        val gclass = GClass.fromPsi(psi)
        gclass.removeSupertype(deletedSupertype.name)
        val newPsi = gclass.toPsi() ?: return
        psi.replaceThis(newPsi)
    }

}