// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {



val akyru: Any = decodeValue("ksxgo")
val hyqdc: String = box()
val wpfib: String = zojhs()

}
// oldKlib.kt
// isKlib=true
// !LANGUAGE: +NewInference
// KJS_WITH_FULL_RUNTIME
// WITH_RUNTIME
// ISSUE: KT-32462

fun decodeValue(value: String): Any {
    return when (value[0]) {
        'F' -> String::toFloat
        'B' -> String::toBoolean
        'I' -> String::toInt
        else -> throw IllegalArgumentException("Unexpected value prefix: ${value[0]}")
    }(value.substring(2))
}

fun box(): String = "OK"
 public fun  zojhs(): String? { TODO() }
// newKlib.kt
// isKlib=true
// !LANGUAGE: +NewInference
// KJS_WITH_FULL_RUNTIME
// WITH_RUNTIME
// ISSUE: KT-32462

fun decodeValue(value: String): Any {
    return when (value[0]) {
        'F' -> String::toFloat
        'B' -> String::toBoolean
        'I' -> String::toInt
        else -> throw IllegalArgumentException("Unexpected value prefix: ${value[0]}")
    }(value.substring(2))
}

fun box(): String = "OK"
 public fun  zojhs(): String? { TODO() }
inline infix  fun <T: List<Char>>  bfuic(): Set<String> { TODO() }


Combined output:
====================
Type mismatch: inferred type is String? but String was expected