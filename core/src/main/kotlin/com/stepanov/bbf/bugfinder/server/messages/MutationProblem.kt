package com.stepanov.bbf.bugfinder.server.messages

import com.stepanov.bbf.information.CompilerArgs
import com.stepanov.bbf.bugfinder.project.Project
import com.stepanov.bbf.bugfinder.mutator.transformations.Constants
import com.stepanov.bbf.bugfinder.mutator.transformations.Transformation
import com.stepanov.bbf.bugfinder.mutator.vertxMessages.MutationStrategy
import com.stepanov.bbf.bugfinder.util.getRandomVariableName
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
        if (mutationTarget is FileTarget) {
            mutationTarget.writeFile()
            project = Project(listOf(mutationTarget.realFileName()))
        } else if (mutationTarget is ProjectTarget) {
            mutationTarget.writeProject()
            project = Project(mutationTarget.files.map { file ->
                mutationTarget.realFileTarget(file).realFileName()
            })
        } else {
            error("mutationTarget is not file or project wtf")
        }
        // TODO: probably shouldn't mutate random file
        // i.e. for a certain mutation we should mutate certain file
        // and should fix params
        return MutationStrategy(List(mutationCount) { _ ->
            val transformation = listOfTransformations.random()
            transformation.primaryConstructor!!
                .call(project, project.files.random())
        }, project)
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

/**
 *  If fileName and code are both null, then should choose random file from tmp folder
 */
@Serializable
@SerialName("file")
data class FileTarget(
    val fileName: String? = null,
    val code: String? = null
): MutationTarget() {
    fun realFileName(): String {
        val tmpFolder = "projectTmp/${Random().getRandomVariableName()}/"
        if (fileName == null) {
            return tmpFolder + "tmp.kt"
        }
        if (!File(fileName).exists()) {
            return tmpFolder + fileName
        }
        return tmpFolder + fileName.removePrefix("tmp/arrays/")
    }

    fun writeFile() {
        val realFileName = realFileName()
        File(realFileName.substringBeforeLast("/")).mkdir()
        if (code != null)
            File(realFileName).writeText(code)
        else
            File(realFileName).writeText(
                File(fileName!!).readText()
            )
    }

//    fun extactCode(): String {
//        if (code != null)
//            return code
//        if (fileName != null)
//            return File(fileName).readText()
//        val file = File(CompilerArgs.baseDir).listFiles()?.filter { it.path.endsWith(".kt") }?.random()
//            ?: error("wtf couldn't choose random file for some reason")
//        return file.readText()
//    }

    override fun validate() {
        if (code == null &&
            fileName != null &&
            !File(fileName).exists())
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
        if (files.isEmpty())
            throw IllegalArgumentException("No files in project")
        files.forEach {  file ->
            if (file.fileName == null) {
                throw IllegalArgumentException("Provided project, but fileName not provided wtf")
            }
            realFileTarget(file).validate()
        }
    }

    fun writeProject() {
        files.forEach { file ->
            realFileTarget(file).writeFile()
        }
    }

    fun realFileTarget(file: FileTarget): FileTarget =
        FileTarget(File(File(projectRoot), file.fileName!!).path, file.code)
}

fun parseMutationProblem(data: String): MutationProblem =
    Json.decodeFromString<MutationProblem>(data).also { it.validate() }
