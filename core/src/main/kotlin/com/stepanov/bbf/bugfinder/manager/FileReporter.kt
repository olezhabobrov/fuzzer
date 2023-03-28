package com.stepanov.bbf.bugfinder.manager

import com.stepanov.bbf.information.CompilerArgs
import java.io.File
import java.util.*


//TODO Maybe add crashing message in comments
object FileReporter : Reporter {

    private fun currentTime(): String {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH) + 1
        val day = c.get(Calendar.DAY_OF_MONTH)
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)
        val second = c.get(Calendar.SECOND)
        val ms = c.get(Calendar.MILLISECOND)
        return "$year-$month-${day}_$hour-$minute-$second-$ms"
    }

    override fun dump(bug: Bug): String {
        val resDir = CompilerArgs.resultsDir
        val name = currentTime() +
                if (bug.project.files.size == 1) "_FILE" else "_PROJECT"
        val newPath = "$resDir/$name.kt"
        File(newPath.substringBeforeLast('/')).mkdirs()
        val info = StringBuilder()
        val result = bug.result.results.first()
        val compiler = bug.result.compiler
        val version = CompilerArgs.compilerVersion(compiler)
        info.appendLine("//$compiler ver $version")
        result.results.forEach { status ->
            val isFailed = status.hasCompilerCrash()
            val config = status.configuration
            info.appendLine("//" + (if (isFailed) "failed" else "not failed") + " with configuration $config")
        }

        val msg = result.results.first { it.hasCompilerCrash() }.combinedOutput

        val commentedStackTrace =
                "// STACKTRACE:\n${msg.split("\n").joinToString("\n") { "// $it" }}"

        File(newPath).writeText("$info\n${bug.project.moveAllCodeInOneFile()}\n$commentedStackTrace")
        return newPath
    }

}