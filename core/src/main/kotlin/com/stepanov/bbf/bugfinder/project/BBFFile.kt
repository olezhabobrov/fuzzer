package com.stepanov.bbf.bugfinder.project

import com.stepanov.bbf.information.CompilerArgs
import com.stepanov.bbf.reduktor.parser.PSICreator
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.resolve.BindingContext
import java.io.File

class BBFFile(
    var psiFile: KtFile,
    val env: KotlinCoreEnvironment,
    var isKlib: Boolean = false
) {
    var ctx: BindingContext? = null
    val name = psiFile.name

    fun getLanguage(): LANGUAGE {
        return when {
            name.endsWith(".java") -> LANGUAGE.JAVA
            name.endsWith(".kt") -> LANGUAGE.KOTLIN
            else -> LANGUAGE.UNKNOWN
        }
    }

    override fun toString(): String =
        "// FILE: ${name.substringAfter(CompilerArgs.pathToTmpDir).substring(1)}\n\n${psiFile.text}"

    val text: String
        get() = psiFile.text

    fun updateCtx(): BindingContext? {
        File(name).writeText(text)
//        psiFile = KtPsiFactory(env.project).createFile(name, text)
        ctx = PSICreator.updateBindingContext(psiFile, env)
        return ctx
    }

    init {
        updateCtx()
    }

    override fun hashCode(): Int = text.hashCode()

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is BBFFile) {
            return false
        }
        return other.text == text
    }

    fun getAllEntities(): List<KtTypeParameterListOwner> {
        val allClasses = psiFile.getAllPSIChildrenOfType<KtClassOrObject>()
        val allProperties = psiFile.getAllPSIChildrenOfType<KtProperty>()
        val allFunctions = psiFile.getAllPSIChildrenOfType<KtFunction>()
        return (allFunctions + allProperties + allClasses)
    }

    fun getAllFunctions(): List<KtFunction> {
        return psiFile.getAllPSIChildrenOfType<KtFunction>().filter { it !is KtConstructor<*> }
    }
}