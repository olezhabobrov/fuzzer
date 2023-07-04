package com.stepanov.bbf.bugfinder.statistics

import com.stepanov.bbf.information.CompilerArgs
import com.stepanov.bbf.information.VertxAddresses
import com.stepanov.bbf.messages.CompilationResult
import io.vertx.core.AbstractVerticle
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

class TransformationStatistics: AbstractVerticle() {

    override fun start() {
        localPrepations()
        establishConsumers()
    }

    private fun establishConsumers() {
        vertx.eventBus().consumer<CompilationResult>(VertxAddresses.transformationStatistics) { msg ->
            val compilationResult = msg.body()
            val mutationStat = compilationResult.mutationStat
            val transformation = mutationStat.transformation
            if (transformation.isEmpty())
                return@consumer
            val fileName = CompilerArgs.statDir + transformation + ".json"
            val file = File(fileName)
            val currentStatistics: TransformationFullStat = if (file.exists()) {
                val statisticsText = file.readText()
                Json.decodeFromString(statisticsText)

            } else {
                TransformationFullStat(transformation)
            }
            currentStatistics.add(compilationResult)
            file.writeText(format.encodeToString(currentStatistics))
        }
    }


    private fun localPrepations() {
        val dir = File(CompilerArgs.statDir)
        if (!dir.exists()) {
            dir.mkdir()
        }
    }

    private val format = Json { prettyPrint = true }
}