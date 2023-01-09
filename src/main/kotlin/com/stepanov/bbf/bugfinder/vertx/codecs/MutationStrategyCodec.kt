package com.stepanov.bbf.bugfinder.vertx.codecs

import com.stepanov.bbf.bugfinder.mutator.vertxMessages.MutationStrategy
import io.vertx.core.buffer.Buffer
import io.vertx.core.eventbus.MessageCodec

class MutationStrategyCodec: MessageCodec<MutationStrategy, MutationStrategy> {
    override fun encodeToWire(buffer: Buffer?, s: MutationStrategy?) {
        TODO("Not yet implemented")
    }

    override fun decodeFromWire(pos: Int, buffer: Buffer?): MutationStrategy {
        TODO("Not yet implemented")
    }

    override fun name(): String = "Mutation Strategy Codec"

    override fun systemCodecID(): Byte = -1

    override fun transform(s: MutationStrategy?): MutationStrategy = s ?: error("wtf why null")
}