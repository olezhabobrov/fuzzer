package com.stepanov.bbf.bugfinder.server.codecs

import com.stepanov.bbf.bugfinder.mutator.vertxMessages.MutationRequest
import io.vertx.core.buffer.Buffer
import io.vertx.core.eventbus.MessageCodec

class MutationRequestCodec: MessageCodec<MutationRequest, MutationRequest> {
    override fun encodeToWire(buffer: Buffer?, s: MutationRequest?) {
        TODO("Not yet implemented")
    }

    override fun decodeFromWire(pos: Int, buffer: Buffer?): MutationRequest {
        TODO("Not yet implemented")
    }

    override fun name(): String = "Mutation Strategy Codec"

    override fun systemCodecID(): Byte = -1

    override fun transform(s: MutationRequest?): MutationRequest = s ?: error("wtf why null")
}