// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {


val payhn: Z = Z(36)
val ifimw: L = L(14)
val yvvnx: S = S("ythuf")
val fvkwc: String = box()
val vivjm: UInt = payhn.fasyv()

}
// oldKlib.kt
// isKlib=true
// DONT_TARGET_EXACT_BACKEND: WASM
// WASM_MUTE_REASON: BOUND_RECEIVER
// !LANGUAGE: +InlineClasses
// WITH_RUNTIME

inline class Z(val x: Int){
private final fun  fasyv(): UInt { TODO() }
}
inline class L(val x: Long)
inline class S(val x: String)

fun Z.test() = x
fun L.test() = x
fun S.test() = x

fun box(): String {
    if (Z(42)::test.invoke() != 42) throw AssertionError()
    if (L(1234L)::test.invoke() != 1234L) throw AssertionError()
    if (S("abcdef")::test.invoke() != "abcdef") throw AssertionError()

    return "OK"
}
// newKlib.kt
// isKlib=true
// DONT_TARGET_EXACT_BACKEND: WASM
// WASM_MUTE_REASON: BOUND_RECEIVER
// !LANGUAGE: +InlineClasses
// WITH_RUNTIME

inline class Z(val x: Int){
private final fun  fasyv(): UInt { TODO() }
}
inline class L(val x: Long)
inline class S(val x: String)

fun Z.test() = x
fun L.test() = x
fun S.test() = x

fun box(): String {
    if (Z(42)::test.invoke() != 42) throw AssertionError()
    if (L(1234L)::test.invoke() != 1234L) throw AssertionError()
    if (S("abcdef")::test.invoke() != "abcdef") throw AssertionError()

    return "OK"
}
class Adlix


Combined output:
====================
Cannot access 'fasyv': it is private in 'Z'