package com.stepanov.bbf.bugfinder.manager

import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.executor.*
import com.stepanov.bbf.bugfinder.util.StatisticCollector
import com.stepanov.bbf.information.VertxAddresses
import com.stepanov.bbf.messages.CompilationResult
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

data class Bug(val compilers: List<String>, val msg: String, val crashedProject: Project, val type: BugType) {

    constructor(b: Bug) : this(
        b.compilers,
        b.msg,
        b.crashedProject.copy(),
        b.type
    )

    constructor(compiler: String, msg: String, crashedProject: Project, type: BugType) : this(
        listOf(compiler),
        msg,
        crashedProject,
        type
    )

    constructor(res: CompilationResult): this(
        listOf(res.compiler),
        res.invokeStatus.combinedOutput,
        Project.getProjectByMessage(res.project),
        res.invokeStatus.bugType()
    )

    val compilerVersion = compilers.joinToString(", ")

    fun compareTo(other: Bug): Int =
        if (compilerVersion == other.compilerVersion)
            type.compareTo(other.type)
        else compilerVersion.compareTo(other.compilerVersion)

    fun getDirWithSameTypeBugs(): String =
        CompilerArgs.resultsDir +
                when (type) {
                    BugType.DIFFBEHAVIOR -> "diffBehavior"
                    BugType.DIFFCOMPILE -> "diffCompile"
                    BugType.DIFFABI -> "diffABI"
//                    BugType.FRONTEND, BugType.BACKEND -> compilers.first().compilerInfo.filter { it != ' ' }
                    else -> ""
                }

    fun copy() = Bug(compilers, msg, crashedProject.copy(), type)

    override fun toString(): String {
        return "${type.name}\n${compilers.map { TODO() }}\nText:\n${crashedProject}"
    }

    override fun equals(other: Any?): Boolean =
        other is Bug && other.crashedProject == this.crashedProject && other.type == this.type &&
                other.compilers.map { TODO() } == this.compilers.map { TODO() }

    override fun hashCode(): Int {
        var result = compilers.hashCode()
        result = 31 * result + msg.hashCode()
        result = 31 * result + crashedProject.hashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + compilerVersion.hashCode()
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
            println("SAVING ${bug.type} BUG")
            if (ReportProperties.getPropAsBoolean("SAVE_STATS") == true) saveStats()
            //Check if bug is real project bug
//            val newBug = bug.copy()//checkIfBugIsProject(bug)
//            log.debug("Start to reduce ${newBug.crashedProject}")
//            val reduced =
//                try {
//                    Reducer.reduce(newBug)
//                } catch (e: Exception) {
//                    newBug.crashedProject.copy()
//                }
//            val reducedBug = Bug(newBug.compilers, newBug.msg, reduced, newBug.type)
//            log.debug("Reduced: ${reducedBug.crashedProject}")
//            val newestBug = reducedBug//checkIfBugIsProject(reducedBug)
            //Try to find duplicates
//            if (/*newBug.crashedProject.texts.size == 1 &&*/
//                CompilerArgs.shouldFilterDuplicateCompilerBugs &&
//                haveDuplicates(newestBug)
//            ) {
//                StatisticCollector.incField("Duplicates")
//                return
//            }
//            bugs.add(newestBug)
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

