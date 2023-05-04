package com.stepanov.bbf.bugfinder.reducer

import com.stepanov.bbf.information.CompilerArgs
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

                if (file.readText().contains("java.lang.OutOfMemoryError: Cannot reserve")) {
                    file.delete()
                    return@forEach
                }

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

    private fun extractStackTrace(file: File) =
        (if (file.name.contains("FRONTEND"))
            file.readText().substringAfterLast("causeThrowable")
        else
            file.readText().substringAfterLast("STACKTRACE"))
            .substringAfter("// \tat")
            .split("\n").take(10).joinToString("\n")

    private fun deleteSourceFile(code: String) = code.substringBefore("com.stepanov.bbf")
}