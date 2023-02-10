package com.stepanov.bbf.bugfinder.vertx.codecs

import com.stepanov.bbf.reduktor.executor.CompilationResult
import io.vertx.core.buffer.Buffer
import io.vertx.core.eventbus.MessageCodec
import io.vertx.core.json.JsonObject
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement

class CompilationResultCodec: MessageCodec<CompilationResult, CompilationResult> {
    override fun encodeToWire(buffer: Buffer?, s: CompilationResult?) {
        encodeString(s!!.compiler, buffer!!)
        val invStJ = Json.encodeToJsonElement(s.invokeStatus)
        encodeString(invStJ, buffer)
        val prMesJ = Json.encodeToJsonElement(s.project)
        encodeString(prMesJ, buffer)
    }

    override fun decodeFromWire(pos: Int, buffer: Buffer?): CompilationResult {
        val (compiler, pos1) = getString(pos, buffer!!)
        val (invStStr, pos2) = getString(pos1, buffer)
        val (prMesStr, _) = getString(pos2, buffer)
        return CompilationResult(compiler, Json.decodeFromString(invStStr), Json.decodeFromString(prMesStr))
    }

    override fun name() = "CompileResultCodec"

    override fun systemCodecID(): Byte = -1

    override fun transform(s: CompilationResult?) = s ?: error("wtf why is it null")
}