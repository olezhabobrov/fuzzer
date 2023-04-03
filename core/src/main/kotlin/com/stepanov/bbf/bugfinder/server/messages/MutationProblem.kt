package com.stepanov.bbf.bugfinder.server.messages

import com.stepanov.bbf.bugfinder.mutator.transformations.Constants
import com.stepanov.bbf.bugfinder.mutator.transformations.Transformation
import com.stepanov.bbf.bugfinder.mutator.vertxMessages.MutationStrategy
import com.stepanov.bbf.information.VertxAddresses
import com.stepanov.bbf.messages.ProjectMessage
import com.stepanov.bbf.util.getSimpleNameFile
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
    val mutationCount: Int,
    val repeatInf: Boolean = false
) {
    fun createMutationStrategy(): MutationStrategy {
        val project: ProjectMessage
        when (mutationTarget) {
            is SingleSourceTarget -> {
                if (mutationTarget is RandomFileTarget)
                    mutationTarget.updateRandomFile()
                mutationTarget.writeFile()
                project = ProjectMessage(listOf(mutationTarget.simpleFileName() to mutationTarget.getSourceCode()))
            }
            is ProjectTarget -> {
                mutationTarget.writeProject()
                project = ProjectMessage(mutationTarget.files.map { it.getLocalName() to it.getSourceCode()})
            }
        }
        return MutationStrategy(List(mutationCount) { listOfTransformations.random() }, project, this)
    }

    private val listOfTransformations: List<Transformation>
        get() {
            if (allowedTransformations is All)
                return Constants.allTransformations
            if (allowedTransformations is TransformationsList)
                return allowedTransformations.transformationsList.map { it.callConstructor() }
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
data class TransformationClass(val clazz: KClass<out Transformation>) {
    fun callConstructor(): Transformation {
        return clazz.primaryConstructor!!.call()
    }
}

@Serializable
sealed class MutationTarget

@Serializable
sealed class SingleSourceTarget: MutationTarget() {

    fun simpleFileName(): String = getLocalName().getSimpleNameFile()

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
    private var tmpFileName = randomTmpFileName()

    private val code
        get() = File("tmp/arrays/$tmpFileName").readText()
    private val name
        get() = "projectTmp/$tmpFileName"

    override fun getLocalName(): String = name

    override fun getSourceCode(): String = code


    private fun randomTmpFileName() =
        File("tmp/arrays/").listFiles()?.filter { it.path.endsWith(".kt") }?.random()!!.name


    fun updateRandomFile() {
        tmpFileName = randomTmpFileName()
    }
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
        "projectTmp/${name.getSimpleNameFile()}"
    private val code = File(name).readText()

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
