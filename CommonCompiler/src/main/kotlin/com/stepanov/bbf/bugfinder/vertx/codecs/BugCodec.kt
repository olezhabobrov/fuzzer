package com.stepanov.bbf.bugfinder.vertx.codecs

import com.stepanov.bbf.bugfinder.manager.Bug
import io.vertx.core.buffer.Buffer
import io.vertx.core.eventbus.MessageCodec

class BugCodec: MessageCodec<Bug, Bug> {
    override fun encodeToWire(buffer: Buffer?, s: Bug?) {
        TODO("Not yet implemented")
    }

    override fun decodeFromWire(pos: Int, buffer: Buffer?): Bug {
        TODO("Not yet implemented")
    }

    override fun name(): String = "Bug Codec"
    override fun systemCodecID(): Byte = -1

    override fun transform(s: Bug?): Bug = s ?: error("wtf why is it null")
}