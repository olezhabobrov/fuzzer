package com.stepanov.bbf.bugfinder.manager

import com.stepanov.bbf.bugfinder.server.messages.CompilationResultHolder
import com.stepanov.bbf.bugfinder.util.StatisticCollector
import com.stepanov.bbf.information.CompilerArgs
import com.stepanov.bbf.information.VertxAddresses
import com.stepanov.bbf.messages.CompilationResult
import com.stepanov.bbf.messages.KotlincInvokeStatus
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


internal fun bugType(result: KotlincInvokeStatus): BugType =
    if (result.combinedOutput.contains("Exception while analyzing expression"))
        BugType.FRONTEND
    else
        BugType.BACKEND

data class Bug(val result: CompilationResult) {
    val project = result.results.first().projectMessage
}


class BugManager: AbstractVerticle() {

    override fun start() {
        establishConsumers()
        log.debug("Bug manager deployed")
    }

    private fun saveBug(bug: Bug): String {

        try {
            log.debug("SAVING BUG")
            if (ReportProperties.getPropAsBoolean("SAVE_STATS") == true) saveStats()
            //Report bugs
            return FileReporter.dump(bug)
        } catch (e: Exception) {
            log.debug("Exception ${e.localizedMessage} ${e.stackTraceToString()}\n")
            System.exit(1)
        }
        return ""
    }

    private fun saveStats() {
        val f = File("bugsPerMinute.txt")
        val curText = StringBuilder(f.readText())
        val bugs = curText.split("\n").first().split(": ").last().toInt()
        val newText = curText.replaceFirst(Regex("\\d+\n"), "${bugs + 1}\n")
        f.writeText(newText)
    }

    private fun establishConsumers() {
        vertx.eventBus().consumer<CompilationResult>(VertxAddresses.bugManager) { msg ->
            processCompilationResults(msg.body())
        }
    }

    private fun processCompilationResults(result: CompilationResult) {
//        if (isUnusualResult(results)) {
//            log.debug("FOUND SOME INTERESTING RESULT")
//        }
        val resultPath = saveBug(Bug(result))
        log.debug("Write result to $resultPath")
    }

//    private fun isUnusualResult(results: List<KotlincInvokeStatus>): Boolean {
//        if (results.distinctBy { it.invokeStatus.hasCompilerCrash() }.size > 1)
//            return true
//        if (results.distinctBy { it.invokeStatus.combinedOutput.substringBefore("\n") }.size > 1)
//            return true
//        return false
//    }

    private val log = Logger.getLogger("bugManagerLogger")
}

