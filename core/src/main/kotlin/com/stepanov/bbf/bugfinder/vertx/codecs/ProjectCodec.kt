package com.stepanov.bbf.bugfinder.vertx.codecs

import com.stepanov.bbf.bugfinder.executor.project.Header
import com.stepanov.bbf.bugfinder.executor.project.Project
import io.vertx.core.buffer.Buffer
import io.vertx.core.eventbus.MessageCodec
import io.vertx.core.json.JsonObject

class ProjectCodec: MessageCodec<Project, Project> {
    override fun name(): String = "ProjectCodec"

    override fun systemCodecID(): Byte = -1

    override fun transform(s: Project?): Project = s ?: error("wtf is it null")

    override fun decodeFromWire(position: Int, buffer: Buffer?): Project {
        val (projectStr, _) = getString(position, buffer!!)
        return Project.createFromCode(projectStr)
    }

    override fun encodeToWire(buffer: Buffer?, s: Project?) {
        encodeString(s!!, buffer!!)
    }
}