package com.stepanov.bbf.bugfinder.vertx.codecs

import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.vertx.serverMessages.ProjectMessage
import io.vertx.core.buffer.Buffer
import io.vertx.core.eventbus.MessageCodec
import io.vertx.core.json.JsonObject

class ProjectCodec: MessageCodec<Project, ProjectMessage> {
    override fun name(): String = "ProjectCodec"

    override fun systemCodecID(): Byte = -1

    override fun transform(s: Project?): ProjectMessage =
        s!!.getProjectMessage()

    override fun decodeFromWire(position: Int, buffer: Buffer?): ProjectMessage {
        val (projectStr, _) = getString(position, buffer!!)
        val json = JsonObject(projectStr)
        val files = json.getJsonArray("files")
        val conf = json.getString("conf")
        return ProjectMessage(
            files.map { jfile ->
                jfile as JsonObject
                jfile.getString("name") to jfile.getString("text")
            },
            conf
        )
    }

    override fun encodeToWire(buffer: Buffer?, s: Project?) {
        val json = JsonObject()
//        json.put("header", s!!.configuration)
        json.put("files", s!!.files.map {
            val jfile = JsonObject()
            jfile.put("name", it.name)
            jfile.put("text", it.text)
        }
        )
        json.put("conf", "")
        encodeString(json.toString(), buffer!!)
    }
}