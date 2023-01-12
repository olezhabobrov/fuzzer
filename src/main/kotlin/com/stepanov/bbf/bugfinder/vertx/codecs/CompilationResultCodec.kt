package com.stepanov.bbf.bugfinder.vertx.codecs

import com.stepanov.bbf.reduktor.executor.CompilationResult
import com.stepanov.bbf.reduktor.executor.KotlincInvokeStatus
import io.vertx.core.buffer.Buffer
import io.vertx.core.eventbus.MessageCodec

class CompilationResultCodec: MessageCodec<CompilationResult, CompilationResult> {
    override fun encodeToWire(buffer: Buffer?, s: CompilationResult?) {
        TODO("Not yet implemented")
    }

    override fun decodeFromWire(pos: Int, buffer: Buffer?): CompilationResult {
        TODO("Not yet implemented")
    }

    override fun name() = "CompileResultCodec"

    override fun systemCodecID(): Byte = -1

    override fun transform(s: CompilationResult?) = s ?: error("wtf why is it null")
}