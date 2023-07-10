// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {



val fuixu: String = foo({a: Char, b: Char -> "qhmrw"})
bar('⹙')
val ldnjt: String = box()
val pquvv: <ERROR CLASS> = hrvko<UByte, UShort?>()

}
// oldKlib.kt
// isKlib=true
// DONT_TARGET_EXACT_BACKEND: WASM
// WASM_MUTE_REASON: IGNORED_IN_JS
// !LANGUAGE: +NewInference
// WITH_RUNTIME
// KJS_WITH_FULL_RUNTIME

inline fun foo(mkString: (Char, Char) -> String): String =
        mkString('O','K')

fun bar (vararg xs: Char) =
        xs.concatToString()

fun box(): String = foo(::bar)
// -> { a, b -> bar(a, b) }
fun <T, S>  hrvko(): ArrayList<UByte> { TODO() }
// newKlib.kt
// isKlib=true
// DONT_TARGET_EXACT_BACKEND: WASM
// WASM_MUTE_REASON: IGNORED_IN_JS
// !LANGUAGE: +NewInference
// WITH_RUNTIME
// KJS_WITH_FULL_RUNTIME

inline fun foo(mkString: (Char, Char) -> String): String =
        mkString('O','K')

fun bar (vararg xs: Char) =
        xs.concatToString()

fun box(): String = foo(::bar)
// -> { a, b -> bar(a, b) }
fun <T, S>  hrvko(): ArrayList<UByte> { TODO() }
protected fun <T: AbstractMap<List<Float>, Byte>>  crlob(): LinkedHashMap<Float, LinkedHashMap<Float, UInt>> { TODO() }


Combined output:
====================
Type expected
Unexpected tokens (use ';' to separate expressions on the same line)