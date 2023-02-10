package com.stepanov.bbf.bugfinder.vertx.codecs

import com.stepanov.bbf.bugfinder.mutator.vertxMessages.MutationResult
import io.vertx.core.buffer.Buffer
import io.vertx.core.eventbus.MessageCodec

class MutationResultCodec: MessageCodec<MutationResult, MutationResult> {
    override fun encodeToWire(buffer: Buffer?, s: MutationResult?) {
        TODO("Not yet implemented")
    }

    override fun decodeFromWire(pos: Int, buffer: Buffer?): MutationResult {
        TODO("Not yet implemented")
    }

    override fun name(): String = "MutationResultCodec"

    override fun systemCodecID(): Byte = -1

    override fun transform(s: MutationResult?): MutationResult = s ?: error("wtf why is it null")
}