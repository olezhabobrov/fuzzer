package com.stepanov.bbf.codecs

import io.vertx.core.buffer.Buffer

internal fun getString(position: Int, buffer: Buffer): Pair<String, Int> {
    var pos = position
    val length = buffer.getInt(pos)
    pos += Int.SIZE_BYTES
    val res = buffer.getString(pos, pos + length)
    pos += length
    return Pair(res, pos)
}

internal fun encodeString(o: Any, buffer: Buffer) {
    val res = o.toString()
    buffer.appendInt(res.length)
    buffer.appendString(res)
}