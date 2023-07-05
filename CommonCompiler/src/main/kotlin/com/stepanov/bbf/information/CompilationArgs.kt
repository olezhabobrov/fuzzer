package com.stepanov.bbf.information

import com.stepanov.bbf.util.getWithoutExt
import kotlinx.serialization.Serializable
import java.util.Random

@Serializable
data class CompilationArgs(
    val number: Int,
    val dir: String = "projectTmp/",
    private var outputName: String = "",
    var klib: String? = null,
    var xinclude: String? = null,
    private val files: MutableList<String> = mutableListOf(),
    private var isXPartialLinkage: Boolean = false,
    private var artifactType: String = "program",
    private var isK2: Boolean = false,
) {

    override fun toString(): String = "result:${build()}"

    fun build(): List<String> {
        val result = mutableListOf<String>()

        when(artifactType) {
            "library" -> result.addAll(listOf("-p", "library"))
            else -> {}
        }
        if (xinclude != null)
            result.add("-Xinclude=$xinclude")
        if (klib != null) {
            result.addAll(listOf("-l", klib!!))
        }
        if (isXPartialLinkage) {
//            result.add("-Xpartial-linkage")
            result.add("-Xpartial-linkage-loglevel=error")
        }
        if (isK2)
            result.addAll(listOf("-language-version", "2.0"))
        if (outputName.isNotBlank())
            result.addAll(listOf("-o", outputName))
        result.addAll(files)
        return result
    }

    fun addPartialLinkage(): CompilationArgs = also {
        isXPartialLinkage = true
    }

    fun addKlib(klibName: String) = also {
        klib = klibName
    }

    fun addXinclude(incl: String) = also {
        xinclude = incl
    }

    fun useK2(): CompilationArgs = also {
        isK2 = true
    }

    fun makeKlib() = also {
        artifactType = "library"
    }

    fun addFile(file: String) = also {
        files.add(dir + file)
    }

    fun addFiles(files: List<String>) = also {
        files.forEach { addFile(it) }
    }

    fun createOutputName(output: String) = also {
        outputName = output
    }

    fun withOldKlib() = klib != null && klib!! == "oldKlib.kt"

    fun withNewKlib() = klib != null && klib!! == "newKlib.kt"

}