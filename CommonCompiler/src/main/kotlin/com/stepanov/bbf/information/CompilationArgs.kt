package com.stepanov.bbf.information

import com.stepanov.bbf.util.getSimpleFileNameWithoutExt
import kotlinx.serialization.Serializable

@Serializable
class CompilationArgs(
    private val root: String
) {
    private var outputName = ""
    private var klib: CompilationArgs? = null
    private val files = mutableListOf<String>()
    private var isXPartialLinkage = false
    private var artifactType = "program"
    private var isK2 = false

    override fun toString(): String = """
    //    Compilation arguments
    //    outputName=$outputName
    //    isLinkedWithKlib=${klib != null}
    //    klib=$klib
    //    -Xpartial-linkage=$isXPartialLinkage
    //    -p=$artifactType
    """.trimIndent()

    fun build(): List<String> {
        val result = mutableListOf<String>()


        when(artifactType) {
            "library" -> result.addAll(listOf("-p", "library"))
            else -> {}
        }
        if (klib != null) {
            result.addAll(listOf("-l", klib!!.outputName))
        }
        if (isXPartialLinkage)
            result.add("-Xpartial-linkage")
        if (isK2)
            error("Kotlin/Native doesn't support K2")
        if (outputName.isNotBlank())
            result.addAll(listOf("-o", outputName))
        result.addAll(files.map { root + it })
        return result
    }

    fun addPartialLinkage() {
        isXPartialLinkage = true
    }

    fun addKlib(addedKlib: CompilationArgs) {
        klib = addedKlib
    }

    fun makeKlib() {
        artifactType = "library"
    }

    fun addFile(file: String) {
        files.add(file)
    }

    fun addFiles(files: List<String>) {
        files.forEach { addFile(it) }
    }

    private fun createOutputName() {
        outputName = files.first().getSimpleFileNameWithoutExt() +
                when(artifactType) {
                    "program" -> ".kexe"
                    "library" -> ".klib"
                    else -> error("unkown artifact type $artifactType")
                }
    }

}