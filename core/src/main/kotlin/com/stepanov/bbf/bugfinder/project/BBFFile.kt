package com.stepanov.bbf.bugfinder.project

import com.intellij.psi.PsiFile
import com.stepanov.bbf.information.CompilerArgs
import com.stepanov.bbf.reduktor.parser.PSICreator
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment
import org.jetbrains.kotlin.resolve.BindingContext

class BBFFile(
    val psiFile: PsiFile,
    val env: KotlinCoreEnvironment
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
        ctx = PSICreator.updateBindingContext(psiFile, env)
        return ctx
    }

    init {
        updateCtx()
    }
}