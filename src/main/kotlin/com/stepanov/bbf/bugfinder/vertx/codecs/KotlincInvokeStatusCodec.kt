package com.stepanov.bbf.bugfinder.vertx.codecs

import com.stepanov.bbf.reduktor.executor.KotlincInvokeStatus
import io.vertx.core.buffer.Buffer
import io.vertx.core.eventbus.MessageCodec

class KotlincInvokeStatusCodec: MessageCodec<KotlincInvokeStatus, KotlincInvokeStatus> {
    override fun encodeToWire(buffer: Buffer?, s: KotlincInvokeStatus?) {
        TODO("Not yet implemented")
    }

    override fun decodeFromWire(pos: Int, buffer: Buffer?): KotlincInvokeStatus {
        TODO("Not yet implemented")
    }

    override fun name() = "CompileResultCodec"

    override fun systemCodecID(): Byte = -1

    override fun transform(s: KotlincInvokeStatus?) = s ?: error("wtf why is it null")
}