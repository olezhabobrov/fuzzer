package com.stepanov.bbf.codecs

import io.vertx.core.buffer.Buffer

fun getString(position: Int, buffer: Buffer): Pair<String, Int> {
    var pos = position
    val length = buffer.getInt(pos)
    pos += Int.SIZE_BYTES
    val res = buffer.getBytes(pos, pos + length)
    pos += length
    return Pair(String(res), pos)
}

fun encodeString(str: String, buffer: Buffer) {
    val res = str.toByteArray()
    buffer.appendInt(res.size)
    buffer.appendBytes(res)
}