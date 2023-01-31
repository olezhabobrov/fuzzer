package com.stepanov.bbf.bugfinder.vertx.serverMessages

import com.stepanov.bbf.bugfinder.executor.project.Header
import com.stepanov.bbf.bugfinder.executor.project.Project

data class ProjectMessage(
//    val header: Header,
    val files: List<Pair<String, String>>,
    val additionalConf: String
) {
    override fun hashCode(): Int {
        return files.sumOf { (name, text) -> name.hashCode() * text.hashCode() }
    }

    override fun equals(other: Any?): Boolean {
        if (other !is ProjectMessage)
            return false
        return hashCode() == other.hashCode()
    }
}