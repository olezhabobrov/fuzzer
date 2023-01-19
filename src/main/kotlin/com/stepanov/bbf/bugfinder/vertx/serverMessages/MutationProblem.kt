package com.stepanov.bbf.bugfinder.vertx.serverMessages

import com.stepanov.bbf.bugfinder.mutator.transformations.ExpressionObfuscator
import com.stepanov.bbf.bugfinder.mutator.transformations.Transformation
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.encodeToJsonElement
import java.net.URI
import kotlin.reflect.KClass

@Serializable
data class MutationProblem(
    val tasks: List<MutationTask>,
    @Serializable(with = URISerializer::class)
    val projectPath: URI = URI.create("/")
) {
    fun validate() {
        if (!projectPath.isAbsolute) {
            throw IllegalArgumentException("projectPath(=$projectPath) in request is not absolute")
        }
        projectPath
    }
}

@Serializable
data class MutationTask(
    @Serializable(with = URISerializer::class)
    val file: URI,
    val allowedTransformations: AllowedTransformations,
    val mutationCount: Int
)

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

fun foo() {
    val json = Json.encodeToJsonElement(MutationTask(URI("file"), TransformationsList(listOf(TransformationClass(ExpressionObfuscator::class))), 503))
    val clazz = Json.decodeFromJsonElement<MutationTask>(json)
    assert(true)
}
