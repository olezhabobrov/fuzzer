package com.stepanov.bbf.bugfinder.vertx.serverMessages

import com.stepanov.bbf.bugfinder.mutator.transformations.Constants
import com.stepanov.bbf.bugfinder.mutator.transformations.Transformation
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import java.io.File
import kotlin.reflect.KClass

@Serializable
data class MutationProblem(
    val compilers: List<String>,
    val allowedTransformations: AllowedTransformations,
    val mutationTarget: MutationTarget,
    val mutationCount: Int
) {
    val listOfTransformations: List<KClass<out Transformation>>
        get() {
            if (allowedTransformations is All)
                return Constants.allTransformations
            if (allowedTransformations is TransformationsList)
                return allowedTransformations.transformationsList.map { it.clazz }
            error("wtf")
        }

    fun validate() {
        if (compilers.isEmpty()) {
            throw IllegalArgumentException("No target compilers defined")
        }
        if (mutationCount <= 0) {
            throw IllegalArgumentException("mutationCount should be positive, but it equals $mutationCount")
        }
        if (listOfTransformations.isEmpty()) {
            throw IllegalArgumentException("List of allowed transformations is empty")
        }
        mutationTarget.validate()
    }
}

@Serializable
sealed class AllowedTransformations

@Serializable
@SerialName("All")
object All: AllowedTransformations()

@Serializable
@SerialName("Some")
data class TransformationsList(val transformationsList: List<TransformationClass>): AllowedTransformations()

@Serializable(with = TransformationClassSerializer::class)
data class TransformationClass(val clazz: KClass<out Transformation>)

@Serializable
sealed class MutationTarget {
    open fun validate() {
        throw IllegalArgumentException("Mutation target should be project or file")
    }
}

@Serializable
@SerialName("file")
data class FileTarget(
    val fileName: String = "tmp.kt",
    val code: String? = null
): MutationTarget() {
    override fun validate() {
        if (code == null && !File(fileName).exists())
            throw IllegalArgumentException("should provide either code of fuzzable file or it correct name")

    }
}

@Serializable
@SerialName("project")
data class ProjectTarget(
    val projectRoot: String,
    val files: List<FileTarget>,
    val args: String = ""
): MutationTarget() {
    override fun validate() {
        files.forEach {  file ->
            FileTarget(File(File(projectRoot), file.fileName).path, file.code).validate()
        }
    }
}

fun parseMutationProblem(data: String): MutationProblem = Json.decodeFromString(data)