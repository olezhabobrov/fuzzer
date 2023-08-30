package com.stepanov.bbf.codecs

import com.stepanov.bbf.messages.CompilationResult
import com.stepanov.bbf.messages.KotlincInvokeResult
import com.stepanov.bbf.messages.ProjectMessage
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

class KotlincInvokeResultCodec: MessageCodec<KotlincInvokeResult, KotlincInvokeResult> {
    override fun encodeToWire(buffer: Buffer?, s: KotlincInvokeResult?) {
        encodeString(Json.encodeToString(s!!), buffer!!)
    }

    override fun decodeFromWire(pos: Int, buffer: Buffer?): KotlincInvokeResult {
        val res = getString(pos, buffer!!)
        return Json.decodeFromString(res.first)
    }

    override fun name() = "KotlincInvokeResultCodec"

    override fun systemCodecID(): Byte = -1

    override fun transform(s: KotlincInvokeResult?) = s ?: error("wtf why is it null")
}

class ProjectMessageCodec: MessageCodec<ProjectMessage, ProjectMessage> {
    override fun encodeToWire(buffer: Buffer?, s: ProjectMessage?) {
        encodeString(Json.encodeToString(s!!), buffer!!)
    }

    override fun decodeFromWire(pos: Int, buffer: Buffer?): ProjectMessage {
        val res = getString(pos, buffer!!)
        return Json.decodeFromString(res.first)
    }

    override fun name() = "ProjectMessageCodec"

    override fun systemCodecID(): Byte = -1

    override fun transform(s: ProjectMessage?) = s ?: error("wtf why is it null")
}