package com.stepanov.bbf.bugfinder.project

import com.stepanov.bbf.bugfinder.util.getRandomVariableName
import com.stepanov.bbf.bugfinder.util.isSubTypeOf
import com.stepanov.bbf.information.CompilerArgs
import com.stepanov.bbf.reduktor.parser.PSICreator
import com.stepanov.bbf.reduktor.parser.PSICreator.psiFactory
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.descriptors.FunctionDescriptor
import org.jetbrains.kotlin.descriptors.VariableDescriptor
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.psi.psiUtil.getParentOfType
import org.jetbrains.kotlin.resolve.BindingContext
import java.io.File
import org.jetbrains.kotlin.types.KotlinType
import kotlin.random.Random

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

    fun getDescriptorByKtClass(clazz: KtClassOrObject): ClassDescriptor? {
        return ctx!![BindingContext.CLASS, clazz]
    }

    fun getDescriptorByKtFunction(function: KtFunction): FunctionDescriptor? {
        return ctx!![BindingContext.FUNCTION, function]
    }

    fun getTypeOfExpression(expression: KtExpression): KotlinType? {
        return ctx!![BindingContext.EXPECTED_EXPRESSION_TYPE, expression]
    }

    fun getVariableDescriptor(property: KtProperty): VariableDescriptor? {
        return ctx!![BindingContext.VARIABLE, property]
    }

    // returns name of found property in main
    fun findImplementation(type: KotlinType): String? {
        val allDeclaredProperties = psiFile.getAllPSIChildrenOfType<KtProperty>().filter {
            it.getParentOfType<KtClassOrObject>(true) == null
        }
        val applicableProperties = allDeclaredProperties.filter {
            getVariableDescriptor(it)?.type?.isSubTypeOf(type) ?: false
        }
        if (applicableProperties.isNotEmpty()) {
            return applicableProperties.random().name
        }
        return null
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

    fun getAllProperties(): List<KtProperty> {
        return psiFile.getAllPSIChildrenOfType()
    }

    fun addExpression(exprString: String) {
        val mainText = text.substringAfter("{").substringBeforeLast("}")
        val fileText = "fun main() {\n$mainText\n$exprString\n}"
        val newKtFile = psiFactory.createFile(fileText)
        psiFile = newKtFile
        updateCtx()
    }
}