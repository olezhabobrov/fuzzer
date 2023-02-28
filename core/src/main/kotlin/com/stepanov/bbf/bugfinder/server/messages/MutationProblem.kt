package com.stepanov.bbf.bugfinder.server.messages

import com.stepanov.bbf.bugfinder.project.Project
import com.stepanov.bbf.bugfinder.mutator.transformations.Constants
import com.stepanov.bbf.bugfinder.mutator.transformations.Transformation
import com.stepanov.bbf.bugfinder.mutator.vertxMessages.MutationStrategy
import com.stepanov.bbf.information.VertxAddresses
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import java.io.File
import java.util.*
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor


@Serializable
data class MutationProblem(
    val compilers: List<String>,
    val allowedTransformations: AllowedTransformations,
    val mutationTarget: MutationTarget,
    val mutationCount: Int
) {
    fun createMutationStrategy(): MutationStrategy {
        val project: Project
        when (mutationTarget) {
            is SingleSourceTarget -> {
                mutationTarget.writeFile()
                project = Project(listOf(mutationTarget.getLocalName()))
            }
            is ProjectTarget -> {
                mutationTarget.writeProject()
                project = Project(mutationTarget.files.map { it.getLocalName() })
            }
        }
        // TODO: probably shouldn't mutate random file
        // i.e. for a certain mutation we should mutate certain file
        // and should fix params
        return MutationStrategy(List(mutationCount) { _ ->
            val transformation = listOfTransformations.random()
            transformation.primaryConstructor!!
                .call(project, project.files.random())
        }, project, this)
    }

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
        compilers.forEach { address ->
            when (address) {
                VertxAddresses.JVMCompiler, VertxAddresses.NativeCompiler -> {}
                else -> throw IllegalArgumentException("Unknown compiler target: $address")
            }
        }
        if (mutationCount < 0) {
            throw IllegalArgumentException("mutationCount should be positive or 0, but it equals $mutationCount")
        }
        if (listOfTransformations.isEmpty()) {
            throw IllegalArgumentException("List of allowed transformations is empty")
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

@Serializable
sealed class MutationTarget

@Serializable
sealed class SingleSourceTarget: MutationTarget() {

    abstract fun getSourceCode(): String

    abstract fun getLocalName(): String

    fun writeFile() {
        val fileName = getLocalName()
        File(fileName.substringBeforeLast("/")).mkdir()
            File(fileName).writeText(getSourceCode())
    }
}

@Serializable
@SerialName("random")
class RandomFileTarget: SingleSourceTarget() {
    private val tmpFileName =
        File("tmp/arrays/").listFiles()?.filter { it.path.endsWith(".kt") }?.random()!!.name
    private val code = File("tmp/arrays/$tmpFileName").readText()
    private val name =
        "projectTmp/$tmpFileName"

    override fun getLocalName(): String = name

    override fun getSourceCode(): String = code
}

@Serializable
@SerialName("code")
class SourceFileTarget(val code: String): SingleSourceTarget() {
    override fun getLocalName(): String = "projectTmp/tmp.kt"
    override fun getSourceCode(): String = code
}

@Serializable
@SerialName("name")
class NameFileTarget(val name: String): SingleSourceTarget() {
    private val copiedFileName =
        "projectTmp/$name"
    private val code = File("tmp/arrays/$name").readText()

    override fun getLocalName(): String = copiedFileName

    override fun getSourceCode(): String = code
}

@Serializable
@SerialName("project")
data class ProjectTarget(
//    val projectRoot: String,
    val files: List<NameFileTarget>,
    val args: String = ""
): MutationTarget() {

    fun writeProject() {
        files.forEach { it.writeFile() }
    }
}

fun parseMutationProblem(data: String): MutationProblem =
    Json.decodeFromString<MutationProblem>(data).also { it.validate() }
