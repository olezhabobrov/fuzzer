package com.stepanov.bbf.messages

import com.stepanov.bbf.information.CompilationConfiguration
import kotlinx.serialization.Serializable

@Serializable
data class ProjectMessage(
    val files: List<Pair<String, String>>,
    val dir: String,
    val configuration: CompilationConfiguration,
    val logInfo: String
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