package com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures

import com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.KtFunction
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner

abstract class GStructure {
    abstract var modifiers: MutableList<String>

    abstract fun toPsi(): PsiElement?

    fun isPrivate() = modifiers.contains("private")
    fun isAbstract() = modifiers.contains("abstract")
    fun isInline() = modifiers.contains("inline")

    fun removeAbstract() {
        modifiers.remove("abstract")
    }

    fun addAbstract() {
        modifiers.add("abstract")
    }

    fun addOpen() {
        modifiers.add("open")
    }

    fun isOpen() = modifiers.contains("open")

    fun getVisibility() =
        when {
            modifiers.contains("public") -> "public"
            modifiers.contains("private") -> "private"
            modifiers.contains("internal") -> "internal"
            modifiers.contains("protected") -> "protected"
            else -> "public"
        }

    fun changeVisibility(newVisibility: String) {
        modifiers.replaceAll {
            when (it) {
                "private", "protected", "internal", "public" -> newVisibility
                else -> it
            }
        }
        if (getVisibility() != newVisibility) {
            modifiers.add(newVisibility)
        }
    }



    fun isImplemented() = when(this) {
        is GFunction -> body.isNotBlank()
        is GProperty -> initializer.isNotBlank() || getter.isNotBlank()
        else -> true
    }

    fun isNotImplemented() = !isImplemented()

    fun addDefaultImplementation() {
        when (this) {
            is GFunction -> body = "{ TODO() }"
            is GProperty -> addDefaultValue()
            else -> error("Is not function or property, shouldn't be so...")
        }
    }

    companion object {
        fun fromPsi(entity: KtTypeParameterListOwner): GStructure {
            return when (entity) {
                is KtClassOrObject -> GClass.fromPsi(entity)
                is KtFunction -> GFunction.fromPsi(entity)
                is KtProperty -> GProperty.fromPsi(entity)
                else -> error("Not property, class or function, wtf is it then?")
            }
        }
    }
}