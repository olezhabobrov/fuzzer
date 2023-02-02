package com.stepanov.bbf.bugfinder.vertx.codecs

import com.stepanov.bbf.bugfinder.vertx.serverMessages.MutationProblem
import io.vertx.core.buffer.Buffer
import io.vertx.core.eventbus.MessageCodec

class MutationProblemCodec: MessageCodec<MutationProblem, MutationProblem> {
    override fun name(): String = "MutationProblemCodec"

    override fun systemCodecID(): Byte = -1

    override fun transform(s: MutationProblem?): MutationProblem = s ?: error("wtf")

    override fun encodeToWire(buffer: Buffer?, s: MutationProblem?) {
        TODO("Not yet implemented")
    }

    override fun decodeFromWire(pos: Int, buffer: Buffer?): MutationProblem {
        TODO("Not yet implemented")
    }
}