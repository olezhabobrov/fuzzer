package com.stepanov.bbf.codecs

import com.stepanov.bbf.messages.CompilationRequest
import io.vertx.core.buffer.Buffer
import io.vertx.core.eventbus.MessageCodec
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class CompilationRequestCodec: MessageCodec<CompilationRequest, CompilationRequest> {
    override fun name(): String = "ProjectCodec"

    override fun systemCodecID(): Byte = -1

    override fun transform(s: CompilationRequest?): CompilationRequest = s!!

    override fun decodeFromWire(position: Int, buffer: Buffer?): CompilationRequest {
        val res = getString(position, buffer!!)
        return Json.decodeFromString(res.first)
    }

    override fun encodeToWire(buffer: Buffer?, s: CompilationRequest?) {
        encodeString(Json.encodeToString(s!!), buffer!!)
    }
}