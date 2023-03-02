package com.stepanov.bbf.messages

import kotlinx.serialization.Serializable

@Serializable
data class ProjectMessage(
    val files: List<Pair<String, String>>,
    val outputDir: String,
    val additionalConf: String,
    val logInfo: String
) {
    override fun hashCode(): Int {
        return files.sumOf { (name, text) -> name.hashCode() * text.hashCode() }
    }

    override fun equals(other: Any?): Boolean {
        if (other !is ProjectMessage)
            return false
        return hashCode() == other.hashCode()
    }

    fun moveAllCodeInOneFile() =
        StringBuilder().apply {
//            append(configuration.toString());
            files.forEach { appendLine(it.second) }
        }.toString()
}