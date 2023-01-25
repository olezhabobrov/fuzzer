package com.stepanov.bbf.bugfinder.vertx.codecs

import com.stepanov.bbf.bugfinder.executor.project.Project
import io.vertx.core.buffer.Buffer
import io.vertx.core.eventbus.MessageCodec

class ProjectCodec: MessageCodec<Project, Project> {
    override fun decodeFromWire(pos: Int, buffer: Buffer?): Project {
        TODO("Not yet implemented")
    }

    override fun name(): String = "ProjectCodec"

    override fun systemCodecID(): Byte = -1

    override fun transform(s: Project?): Project = s ?: error("wtf is it null")

    override fun encodeToWire(buffer: Buffer?, s: Project?) {
        TODO("Not yet implemented")
    }
}