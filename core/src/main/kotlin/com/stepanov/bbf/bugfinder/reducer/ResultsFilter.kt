package com.stepanov.bbf.bugfinder.reducer

import com.stepanov.bbf.information.CompilerArgs
import com.stepanov.bbf.messages.CompilationDescription
import java.io.File

object ResultsFilter {
    fun filter() {
//        File(CompilerArgs.resultsDir).listFiles()!!.filter { it.isFile }.forEach { file ->
//            if (file.readText().contains("Exception while analyzing expression")) {
//                file.renameTo(File("tmp/results/" + file.nameWithoutExtension + "_FRONTEND" + ".kt"))
//            } else {
//                file.renameTo(File("tmp/results/" + file.nameWithoutExtension + "_BACKEND" + ".kt"))
//            }
//        }
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

                if (text.contains("Cannot access")) {
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