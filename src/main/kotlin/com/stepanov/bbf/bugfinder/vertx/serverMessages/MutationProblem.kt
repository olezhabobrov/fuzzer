package com.stepanov.bbf.bugfinder.vertx.serverMessages

import com.stepanov.bbf.bugfinder.mutator.transformations.Constants
import com.stepanov.bbf.bugfinder.mutator.transformations.ExpressionObfuscator
import com.stepanov.bbf.bugfinder.mutator.transformations.Transformation
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.encodeToJsonElement
import java.io.File
import java.io.ObjectInputStream.GetField
import java.net.URI
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.reflect.KClass

@Serializable
data class MutationProblem(
    val tasks: List<MutationTask>,
    val projectPath: String = "/"
) {
    fun validate() {
        if (!File(projectPath).isAbsolute) {
            throw IllegalArgumentException("projectPath(=$projectPath) in request is not absolute")
        }
        if (tasks.isEmpty()) {
            throw IllegalArgumentException("No MutationTasks provided in request")
        }
        tasks.forEach { it.validate(projectPath) }
    }
}

@Serializable
data class MutationTask(
    val file: String,
    val allowedTransformations: AllowedTransformations,
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

    fun validate(projectPath: String) {
        if (mutationCount <= 0) {
            throw IllegalArgumentException("mutationCount should be positive, but it equals $mutationCount")
        }
        if (listOfTransformations.isEmpty()) {
            throw IllegalArgumentException("List of allowed transformations is empty")
        }
        if (URI.create(file).isAbsolute) {
            throw IllegalArgumentException("file(=$file) shouldn't be absolute path")
        }
        if (!File(projectPath + file).exists()) {
            throw IllegalArgumentException("${projectPath + file} does not exist, where projectPath=$projectPath and filePath=$file")
        }
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

fun parseMutationProblem(data: String): MutationProblem = Json.decodeFromString(data)