package com.stepanov.bbf.bugfinder.server.codecs

import com.stepanov.bbf.bugfinder.server.messages.CompilationResultHolder
import io.vertx.core.buffer.Buffer
import io.vertx.core.eventbus.MessageCodec

class CompilationResultHolderCodec: MessageCodec<CompilationResultHolder, CompilationResultHolder> {
    override fun encodeToWire(buffer: Buffer?, s: CompilationResultHolder?) {
        TODO("Not yet implemented")
    }

    override fun decodeFromWire(pos: Int, buffer: Buffer?): CompilationResultHolder {
        TODO("Not yet implemented")
    }

    override fun name(): String = "Holder of results codec"

    override fun systemCodecID(): Byte = -1

    override fun transform(s: CompilationResultHolder?): CompilationResultHolder = s!!
}