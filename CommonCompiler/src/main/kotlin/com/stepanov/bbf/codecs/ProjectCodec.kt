package com.stepanov.bbf.codecs

import com.stepanov.bbf.messages.ProjectMessage
import io.vertx.core.buffer.Buffer
import io.vertx.core.eventbus.MessageCodec
import io.vertx.core.json.JsonObject
import kotlin.math.log

class ProjectCodec: MessageCodec<ProjectMessage, ProjectMessage> {
    override fun name(): String = "ProjectCodec"

    override fun systemCodecID(): Byte = -1

    override fun transform(s: ProjectMessage?): ProjectMessage = s!!

    override fun decodeFromWire(position: Int, buffer: Buffer?): ProjectMessage {
        val (projectStr, _) = getString(position, buffer!!)
        val json = JsonObject(projectStr)
        val files = json.getJsonArray("files")
        val build = json.getString("build")
        val conf = json.getString("conf")
        val logInfo = json.getString("logInfo")
        return ProjectMessage(
            files.map { jfile ->
                jfile as JsonObject
                jfile.getString("name") to jfile.getString("text")
            },
            build,
            conf,
            logInfo
        )
    }

    override fun encodeToWire(buffer: Buffer?, s: ProjectMessage?) {
        val json = JsonObject()
//        json.put("header", s!!.configuration)
        json.put("files", s!!.files.map {
            val jfile = JsonObject()
            jfile.put("name", it.first)
            jfile.put("text", it.second)
        }
        )
        json.put("build", "tmp/build")
        json.put("conf", "")
        json.put("logInfo", s.logInfo)
        encodeString(json.toString(), buffer!!)
    }
}