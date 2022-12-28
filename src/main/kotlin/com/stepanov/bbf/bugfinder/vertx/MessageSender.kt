package com.stepanov.bbf.bugfinder.vertx

import com.stepanov.bbf.bugfinder.executor.CompilationResult
import com.stepanov.bbf.bugfinder.executor.project.Project
import io.vertx.core.AsyncResult
import io.vertx.core.eventbus.EventBus
import io.vertx.core.eventbus.Message
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class MessageSender(
    val eb: EventBus
) {
    fun compile(project: Project): CompilationResult {
        var output: CompilationResult? = null
        eb.request<String>(VertxAddresses.compile, CompileRequestMessage(ProjectMessage.fromProject(project))) {
                result ->
            output = Json.decodeFromString<CompilationResult>(result.result().body())
        }
        output?. let { _ -> error("Smth went wrong") }
        return output!!
    }

}