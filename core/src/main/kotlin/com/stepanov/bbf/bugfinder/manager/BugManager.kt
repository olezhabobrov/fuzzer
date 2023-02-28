package com.stepanov.bbf.bugfinder.manager

import com.stepanov.bbf.bugfinder.util.StatisticCollector
import com.stepanov.bbf.information.CompilerArgs
import com.stepanov.bbf.information.VertxAddresses
import com.stepanov.bbf.messages.CompilationResult
import com.stepanov.bbf.messages.ProjectMessage
import io.vertx.core.AbstractVerticle
import org.apache.log4j.Logger
import java.io.File

enum class BugType {
    BACKEND,
    FRONTEND,
    DIFFBEHAVIOR,
    UNKNOWN,
    DIFFCOMPILE,
    DIFFABI,
    PERFORMANCE
}


internal fun bugType(result: CompilationResult): BugType =
    if (result.invokeStatus.combinedOutput.contains("Exception while analyzing expression"))
        BugType.FRONTEND
    else
        BugType.BACKEND

data class Bug(val compiler: String, val msg: String, val crashedProject: ProjectMessage, val type: BugType) {

    constructor(res: CompilationResult): this(
        res.compiler,
        res.invokeStatus.combinedOutput,
        res.project,
        bugType(res)
    )

    val compilerWithVersion = "$compiler version ${CompilerArgs.compilerVersion(compiler)}"

    fun compareTo(other: Bug): Int =
        if (compiler == other.compiler)
            type.compareTo(other.type)
        else compiler.compareTo(other.compiler)

    fun getDirWithSameTypeBugs(): String =
        CompilerArgs.resultsDir +
                when (type) {
                    BugType.DIFFBEHAVIOR -> "diffBehavior"
                    BugType.DIFFCOMPILE -> "diffCompile"
                    BugType.DIFFABI -> "diffABI"
//                    BugType.FRONTEND, BugType.BACKEND -> compilers.first().compilerInfo.filter { it != ' ' }
                    else -> ""
                }

    fun copy() = Bug(compiler, msg, crashedProject.copy(), type)

    override fun toString(): String {
        return "${type.name}\n${compiler}\nText:\n${crashedProject}"
    }

    override fun equals(other: Any?): Boolean =
        other is Bug && other.crashedProject == this.crashedProject && other.type == this.type &&
                other.compiler == this.compiler

    override fun hashCode(): Int {
        var result = compiler.hashCode()
        result = 31 * result + msg.hashCode()
        result = 31 * result + crashedProject.hashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + compiler.hashCode()
        return result
    }


}


class BugManager: AbstractVerticle() {

    private val bugs = mutableListOf<Bug>()

    override fun start() {
        establishConsumers()
        log.debug("Bug manager deployed")
    }

    private fun saveBug(bug: Bug) {

        try {
            val field = when (bug.type) {
                BugType.BACKEND -> "Backend"
                BugType.FRONTEND -> "Frontend"
                BugType.DIFFBEHAVIOR -> "Miscompilation"
                else -> ""
            }
            if (field.isNotEmpty()) StatisticCollector.incField(field)
            log.debug("SAVING ${bug.type} BUG")
            if (ReportProperties.getPropAsBoolean("SAVE_STATS") == true) saveStats()
            //Report bugs
            if (ReportProperties.getPropAsBoolean("TEXT_REPORTER") == true) {
                TextReporter.dump(bugs)
            }
            if (ReportProperties.getPropAsBoolean("FILE_REPORTER") == true) {
                val bugList =
                    if (bug.type == BugType.FRONTEND || bug.type == BugType.BACKEND)
                        listOf(bug, bug)
                    else listOf(bug)
                FileReporter.dump(bugList)
            }
        } catch (e: Exception) {
            log.debug("Exception ${e.localizedMessage} ${e.stackTraceToString()}\n")
            System.exit(1)
        }
    }

    fun haveDuplicates(bug: Bug): Boolean {
        val dirWithSameBugs = bug.getDirWithSameTypeBugs()
        TODO()
    }

    private fun parseTypeOfBugByMsg(msg: String): BugType =
        if (msg.contains("Exception while analyzing expression"))
            BugType.FRONTEND
        else
            BugType.BACKEND

    private fun saveStats() {
        val f = File("bugsPerMinute.txt")
        val curText = StringBuilder(f.readText())
        val bugs = curText.split("\n").first().split(": ").last().toInt()
        val newText = curText.replaceFirst(Regex("\\d+\n"), "${bugs + 1}\n")
        f.writeText(newText)
    }

    private fun establishConsumers() {
        vertx.eventBus().consumer<Bug>(VertxAddresses.bugManager) { msg ->
            saveBug(msg.body())
        }
    }

    private val log = Logger.getLogger("bugManagerLogger")
}

