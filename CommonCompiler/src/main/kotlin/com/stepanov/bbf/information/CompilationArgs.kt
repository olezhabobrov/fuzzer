package com.stepanov.bbf.information

import com.stepanov.bbf.util.getWithoutExt
import kotlinx.serialization.Serializable
import java.util.Random

@Serializable
data class CompilationArgs(
    val dir: String = "projectTmp/",
    private var outputName: String = "",
    var klib: CompilationArgs? = null,
    private val files: MutableList<String> = mutableListOf(),
    private var isXPartialLinkage: Boolean = false,
    private var artifactType: String = "program",
    private var isK2: Boolean = false,
) {

    override fun toString(): String = "result:${build()}" + if (klib != null) " klib:$klib" else ""

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
            result.add("-Xpartial-linkage=enable")
        if (isK2)
            error("Kotlin/Native doesn't support K2")
        if (outputName.isNotBlank())
            result.addAll(listOf("-o", outputName))
        result.addAll(files)
        return result
    }

    fun addPartialLinkage(): CompilationArgs = also {
        isXPartialLinkage = true
    }


    fun addKlib(addedKlib: CompilationArgs) = also {
        klib = addedKlib
    }

    fun makeKlib() = also {
        artifactType = "library"
    }

    fun addFile(file: String) = also {
        files.add(dir + file)
    }

    fun addFiles(files: List<String>) = also {
        files.forEach { addFile(it) }
        createOutputName()
    }

    private fun createOutputName() {
        outputName = files.first().getWithoutExt() +
                Random().nextInt()
    }

}