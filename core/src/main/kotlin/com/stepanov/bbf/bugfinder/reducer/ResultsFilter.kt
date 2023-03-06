package com.stepanov.bbf.bugfinder.reducer

import com.stepanov.bbf.information.CompilerArgs
import java.io.File

object ResultsFilter {
    fun filter() {
        File(CompilerArgs.resultsDir + "/NativeCompiler")?.listFiles()?.forEach { file ->
            if (file.readLines().size > 1000) {
                file.delete()
            }
        }
    }
}