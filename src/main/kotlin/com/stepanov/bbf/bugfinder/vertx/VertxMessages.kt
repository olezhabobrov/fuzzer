package com.stepanov.bbf.bugfinder.vertx

import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.reduktor.executor.KotlincInvokeStatus
import kotlinx.serialization.Serializable

@Serializable
data class CompileRequestMessage(
    val project: ProjectMessage,
)

@Serializable
data class CompileResultMessage(
    val fileName: String,
    val compilationResult: KotlincInvokeStatus
)

@Serializable
data class ProjectMessage(
    val text: String,
) {
    companion object {
        fun fromProject(project: Project): ProjectMessage =
            ProjectMessage(project.convertToSingleFileProject().files.first().text)

    }
}