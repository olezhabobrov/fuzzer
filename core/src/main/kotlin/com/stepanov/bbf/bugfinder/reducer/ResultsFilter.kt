package com.stepanov.bbf.bugfinder.reducer

import com.stepanov.bbf.information.CompilerArgs
import java.io.File

object ResultsFilter {
    fun filter() {
        File(CompilerArgs.resultsDir + "/NativeCompiler").listFiles()?.forEach { file ->
            if (file.exists()) {
                if (file.readLines().size > 1000) {
                    file.delete()
                    return@forEach
                }

                if (file.name.contains("_ORIGINAL")) {
                    file.delete()
                    return@forEach
                }

                val stackTrace = extractStackTrace(file.readText())
                if (stackTrace.contains("Source files")) { // in empty stack trace not really interested right now
                    File(CompilerArgs.resultsDir + "/NativeCompiler").listFiles()?.forEach { other ->
                        if (other.name != file.name) {
                            val st2 = extractStackTrace(other.readText())
                            if (
                                deleteSourceFile(stackTrace) == deleteSourceFile(st2)
                            ) {
                                other.delete()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun extractStackTrace(code: String) =
        code.substringAfterLast("STACKTRACE")

    private fun deleteSourceFile(code: String) = code.substringBefore("Source files")
}