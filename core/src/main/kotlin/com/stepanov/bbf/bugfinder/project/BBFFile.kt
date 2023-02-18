package com.stepanov.bbf.bugfinder.project

import com.intellij.psi.PsiFile
import com.stepanov.bbf.information.CompilerArgs
import com.stepanov.bbf.bugfinder.util.filterNotLines
import com.stepanov.bbf.reduktor.parser.PSICreator
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment
import org.jetbrains.kotlin.resolve.BindingContext
import java.io.File

data class BBFFile(
    val name: String,
    var psiFile: PsiFile,
    val env: KotlinCoreEnvironment,
    val ctx: BindingContext
) {

    fun getLanguage(): LANGUAGE {
        return when {
            name.endsWith(".java") -> LANGUAGE.JAVA
            name.endsWith(".kt") -> LANGUAGE.KOTLIN
            else -> LANGUAGE.UNKNOWN
        }
    }

    fun copy() = BBFFile(name, psiFile.copy() as PsiFile)

    override fun toString(): String =
        "// FILE: ${name.substringAfter(CompilerArgs.pathToTmpDir).substring(1)}\n\n${psiFile.text}"

    val text: String
        get() = psiFile.text
}

internal class BBFFileFactory(
    private val text: String,
    private val configuration: Header
) {

    fun createBBFFiles(name: String = "tmp"): List<BBFFile>? {
        try {
            val splitCode = splitCodeByFiles(text)
            val names = splitCode.map { it.lines().find { it.startsWith(Directives.file) } ?: "" }
            val codeWithoutComments = splitCode.map { it.filterNotLines { it.startsWith("// ") }.trim() }
            val pathToTmp = CompilerArgs.pathToTmpDir
            return if (names.any { it.isEmpty() }) codeWithoutComments.mapIndexed { i, code ->
                val fileName = "$pathToTmp/$name$i.kt"
                File(fileName).writeText(code)
                val env = PSICreator.getEnvForFile(fileName)
                val ktFile = createKtFile(env)
                //val fileToCtx = createKtFileWithCtx("${Directives.file}$fileName\n$code")
                BBFFile(fileName, ktFile)
            }
            else names.zip(codeWithoutComments).map {
                val fileName = "$pathToTmp/${it.first.substringAfter(Directives.file)}"
                File(fileName).writeText(it.second)
                val ktFile = createKtFile(it.second)
                BBFFile(fileName, ktFile)
            }
        } catch (e: Throwable) {
            println(e)
            println(e.stackTraceToString())
            return null
        }
    }

    private fun splitByFragments(text: String, splitter: String): List<String> {
        val lines = text.lines()
        val fragments = mutableListOf<String>()
        val firstCommentsSection = lines.takeWhile { it.trim().isEmpty() || it.startsWith("//") }
        if (firstCommentsSection.any { it.startsWith(splitter) }) {
            val curFragment = mutableListOf<String>()
            for (i in firstCommentsSection.size until lines.size) {
                val line = lines[i]
                if (!line.startsWith(splitter)) {
                    curFragment.add(line)
                } else {
                    fragments.add(curFragment.joinToString("\n"))
                    curFragment.clear()
                    curFragment.add(line)
                }
            }
            fragments.add(curFragment.joinToString("\n"))
        } else fragments.add(text.lines().filterNot { it.startsWith("// ") }.joinToString("\n"))
        if (configuration.withDirectives.contains(Directives.coroutinesDirective)) handleCoroutines(fragments)
        val firstFragment = firstCommentsSection.joinToString("\n") + "\n" + fragments[0]
        return listOf(firstFragment) + fragments.subList(1, fragments.size)
    }

    private fun handleCoroutines(fragments: MutableList<String>) {
        val coroutinesPackage = "COROUTINES_PACKAGE"
        val ktCoroutinesPackage = "kotlin.coroutines"
        val helpersImportDirective = "import helpers.*"
        val nameOfHelpersFile = "CoroutineUtil.kt"
        val pathToHelpersFile = "${CompilerArgs.pathToTmpDir}/lib/CoroutineUtil.kt"
        val textOfFile = "${Directives.file}$nameOfHelpersFile\n${File(pathToHelpersFile).readText()}"
        for (i in fragments.indices) {
            fragments[i] = fragments[i]
                .replace(coroutinesPackage, ktCoroutinesPackage)
                .replace("import kotlin.coroutines.experimental", "import kotlin.coroutines")

        }
        //Handle helpers
        if (fragments.any { it.contains(helpersImportDirective) }) fragments.add(textOfFile)
    }

    private fun splitCodeByFiles(text: String): List<String> {
//        val modules = splitByFragments(text, Directives.module)
//        return if (modules.size > 1)
//            modules
//        else
        return splitByFragments(text, Directives.file)
    }

    private fun createKtFile(env: KotlinCoreEnvironment): PsiFile = PSICreator.getPSIForEnv(env)

}