package com.stepanov.bbf.messages

import kotlinx.serialization.Serializable

@Serializable
data class ProjectMessage(
    val files: List<FileData>,
    val dir: String = "projectTmp/",
) {

    val mainFile
        get() = files.first { !it.isKlib }

    val klib
        get() = files.first { it.isKlib }

    override fun hashCode(): Int {
        return files.sumOf { (_, text) ->
            text.hashCode()
        }
    }

    override fun equals(other: Any?): Boolean {
        if (other !is ProjectMessage)
            return false
        return files.sortedBy { it.text }.zip(other.files.sortedBy { it.text }).all { (first, second) ->
            first.text == second.text && first.isKlib == second.isKlib
        }
    }

    fun moveAllCodeInOneFile() =
        StringBuilder().apply {
            appendLine("// files")
            files.forEach {
                appendLine("// ${it.name}")
                appendLine("// isKlib=${it.isKlib}")
                appendLine(it.text)
            }
        }.toString()

    fun findByName(name: String): FileData? {
        return files.find { it.name == name }
    }

    fun hasMain() = moveAllCodeInOneFile().contains("fun main")
}

@Serializable
data class FileData(val name: String, val text: String, val isKlib: Boolean = false)