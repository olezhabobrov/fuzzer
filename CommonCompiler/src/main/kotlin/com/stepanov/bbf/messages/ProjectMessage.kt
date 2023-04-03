package com.stepanov.bbf.messages

import kotlinx.serialization.Serializable

@Serializable
data class ProjectMessage(
    val files: List<Pair<String, String>>, // name to code
    val dir: String = "projectTmp/",
    val isSplit: Boolean = false,
) {

    override fun hashCode(): Int {
        return files.sumOf { (name, text) -> name.hashCode() * text.hashCode() }
    }

    override fun equals(other: Any?): Boolean {
        if (other !is ProjectMessage)
            return false
        return files.sortedBy { it.first }.zip(other.files.sortedBy { it.first }).all { (first, second) ->
            first.second == second.second
        }
    }

    fun moveAllCodeInOneFile() =
        StringBuilder().apply {
//            append(configuration.toString());
            files.forEach { appendLine(it.second) }
        }.toString()
}