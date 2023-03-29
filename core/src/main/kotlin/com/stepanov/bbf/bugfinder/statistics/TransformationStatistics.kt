package com.stepanov.bbf.bugfinder.statistics

import com.stepanov.bbf.information.CompilerArgs
import com.stepanov.bbf.information.VertxAddresses
import com.stepanov.bbf.messages.CompilationResult
import com.stepanov.bbf.messages.KotlincInvokeStatus
import io.vertx.core.AbstractVerticle
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

class TransformationStatistics: AbstractVerticle() {
    private val statistics = CompilerArgs.transformationsStatFile

    override fun start() {
        localPrepations()
        establishConsumers()
    }

    private fun establishConsumers() {
        vertx.eventBus().consumer<CompilationResult>(VertxAddresses.transformationStatistics) { msg ->
            val compilationResult = msg.body()
            val transformation = compilationResult.transformation
            val file = File(statistics)
            val currentStatistics: TransformationsInfo = if (file.exists()) {
                val statisticsText = file.readText()
                Json.decodeFromString(statisticsText)

            } else {
                TransformationsInfo(mutableListOf())
            }
            val localResult = TransformationStat(
                transformation,
                countAllWithCondition(compilationResult) { status -> status.isCompileSuccess },
                countAllWithCondition(compilationResult) { status -> !status.isCompileSuccess },
                countAllWithCondition(compilationResult) { status -> status.hasCompilerCrash() },
                countAllWithCondition(compilationResult) { status -> status.hasTimeout }
            )
            val stat = currentStatistics.stats.find { it.transformation == transformation }
            currentStatistics.stats.removeIf { it.transformation == transformation }
            if (stat != null) {
                localResult.add(stat)
            }
            currentStatistics.stats.add(localResult)
            file.writeText(Json.encodeToString(currentStatistics))
        }
    }

    private fun countAllWithCondition(compilationResult: CompilationResult,
                                      predicate: (KotlincInvokeStatus) -> Boolean) = compilationResult.results
                                          .sumOf { result ->
                                              result.results.count(predicate)
                                          }

    private fun localPrepations() {
        val dir = File(statistics.substringBeforeLast("/"))
        if (!dir.exists()) {
            dir.mkdir()
        }
    }
}