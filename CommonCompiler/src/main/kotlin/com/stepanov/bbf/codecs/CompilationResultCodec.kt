package com.stepanov.bbf.codecs

import com.stepanov.bbf.messages.CompilationResult
import io.vertx.core.buffer.Buffer
import io.vertx.core.eventbus.MessageCodec
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class CompilationResultCodec: MessageCodec<CompilationResult, CompilationResult> {
    override fun encodeToWire(buffer: Buffer?, s: CompilationResult?) {
        encodeString(Json.encodeToString(s!!), buffer!!)
    }

    override fun decodeFromWire(pos: Int, buffer: Buffer?): CompilationResult {
        val res = getString(pos, buffer!!)
        return Json.decodeFromString(res.first)
    }

    override fun name() = "CompileResultCodec"

    override fun systemCodecID(): Byte = -1

    override fun transform(s: CompilationResult?) = s ?: error("wtf why is it null")
}