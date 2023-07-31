package com.stepanov.bbf.bugfinder.reducer

import com.github.difflib.DiffUtils
import com.github.difflib.patch.DeltaType
import com.github.difflib.text.DiffRowGenerator
import com.stepanov.bbf.information.CompilerArgs
import com.stepanov.bbf.messages.CompilationDescription
import java.io.File


object ResultsFilter {
    private fun highlightDifferences(originalText: String, changedText: String): String {
        val originalLines = originalText.lines()
        val changedLines = changedText.lines()
        val patch = DiffUtils.diff(originalLines, changedLines)
        val result = mutableListOf<String>()

        var lastLine = 0
        for (delta in patch.deltas) {
            // Add unchanged lines before this delta
            for (i in lastLine until delta.source.position) {
                result.add("     ${originalLines[i]}")
            }
            lastLine = delta.source.position + delta.source.lines.size

            when (delta.type) {
                DeltaType.INSERT -> {
                    delta.target.lines.forEach { result.add("new: $it") }
                }
                DeltaType.DELETE -> {
                    delta.source.lines.forEach { result.add("old: $it") }
                }
                DeltaType.CHANGE -> {
                    delta.source.lines.forEach { result.add("old: $it") }
                    delta.target.lines.forEach { result.add("new: $it") }
                }

                else -> {
                    TODO("WHY")
                }

            }
        }

        // Add remaining unchanged lines
        for (i in lastLine until originalLines.size) {
            result.add("     ${originalLines[i]}")
        }
        return result.joinToString("\n")
    }

    fun filter() {
        File(CompilerArgs.resultsDir).walkTopDown().forEach {file ->
            if (file.name.contains("KLIB")) {
                val text = file.readText()
                if (text.contains("oldKlib.kt") && text.contains("newKlib.kt")) {
                    val newKlibText = text.substringAfter("// newKlib.kt\n")
                        .substringBefore("Combined output")
                        .substringBefore("// oldKlib.kt")
                        .substringBefore("// main.kt")
                    val oldKlibText = text.substringAfter("// oldKlib.kt\n")
                        .substringBefore("Combined output")
                        .substringBefore("// newKlib.kt")
                        .substringBefore("// main.kt")
                    val mainText = text.substringAfter("// main.kt\n")
                        .substringBefore("Combined output")
                        .substringBefore("// oldKlib.kt")
                        .substringBefore("// newKlib.kt")
                    val prefix = text.substringBefore("files") + "\n"
                    val postfix = "Combined output" + text.substringAfter("Combined output")
                    val diff = highlightDifferences(oldKlibText, newKlibText)
                    val newText = prefix + "// klib:\n" + diff + "\n" + mainText + postfix
                    file.writeText(newText)
                }
            }

        }

        File(CompilerArgs.resultsDir).walkTopDown().forEach { file ->
            if (file.exists() && file.isFile) {
                if (file.readLines().size > 1000) {
                    file.delete()
                    return@forEach
                }

                if (file.name.contains("_ORIGINAL")) {
                    file.delete()
                    return@forEach
                }

                val text = file.readText()

                if (text.contains("Unresolved reference") ||
                    text.contains("This type has a constructor, and thus must be initialized here")) {
                    file.delete()
                    return@forEach
                }

                if (Regex("Type mismatch: inferred type is \\w+\\? but \\w+ was expected").containsMatchIn(text)) {
                    file.delete()
                    return@forEach
                }

                if (text.contains("COMPILER_CRASHED")) {
                    val stackTrace = extractStackTrace(file)
                    if (stackTrace.count { it == '\n' } > 5) { // in empty stack trace not really interested right now
                        File(CompilerArgs.resultsDir).walkTopDown().filter { it.isFile }.forEach { other ->
                            if (other.name != file.name) {
                                val st2 = extractStackTrace(other)
                                if (
                                    deleteSourceFile(stackTrace) == deleteSourceFile(st2)
                                ) {
                                    println("${other.name} -> ${file.name}")
                                    other.delete()
                                }
                            }
                        }
                    }
                }
            }
        }
        File(CompilerArgs.resultsDir).listFiles()?.forEach { file ->
            if (file != null && file.exists() && file.isFile) {
                val text = file.readText()
                if (text.contains(CompilationDescription.INVOCATOR_FAIL.toString())) {
                    moveFile(file, CompilationDescription.INVOCATOR_FAIL.toString())
                }
                if (text.contains(CompilationDescription.COMPATIBLE_NOT_LINKING.toString())) {
                    moveFile(file, CompilationDescription.COMPATIBLE_NOT_LINKING.toString())
                }
                if (text.contains(CompilationDescription.INCOMPATIBLE_LINKING.toString())) {
                    moveFile(file, CompilationDescription.INCOMPATIBLE_LINKING.toString())
                }
                if (text.contains(CompilationDescription.COMPILER_CRASHED.toString())) {
                    moveFile(file, CompilationDescription.COMPILER_CRASHED.toString())
                }
                if (text.contains(CompilationDescription.UNKOWN_BEHAVIOUR.toString())) {
                    moveFile(file, CompilationDescription.UNKOWN_BEHAVIOUR.toString())
                }
            }
        }
    }

    private fun moveFile(file: File, dirName: String) {
        val dir = File(CompilerArgs.resultsDir, dirName)
        if (!dir.exists()) {
            dir.mkdir()
        }
        val newFile = File(dir, file.name)
        newFile.writeText(file.readText())
        file.delete()
    }

    private fun extractStackTrace(file: File) =
        (if (file.name.contains("FRONTEND"))
            file.readText().substringAfterLast("causeThrowable")
        else
            file.readText().substringAfterLast("===================="))
            .substringAfter("\tat")
            .split("\n").take(10).joinToString("\n")

    private fun deleteSourceFile(code: String) = code.substringBefore("com.stepanov.bbf")
}