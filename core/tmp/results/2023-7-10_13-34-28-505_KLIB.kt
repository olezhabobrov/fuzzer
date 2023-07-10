// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {


val crgtk: A = object: A {
}
val vmykd: O = O
val affuy: Ggnhz = Ggnhz()
val rxinx: O.Rlldl = O.Rlldl()
val bzkcx: O.Duomd = O.Duomd
val evthq: String = test({a: A, b: A, c: A, d: A, e: A, f: A, g: A, h: A, i: A, j: A, k: A, l: A, m: A, n: A, o: A, p: A, q: A, r: A, s: A, t: A, u: A, v: A, w: A, x: A, y: Function25<A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, String, String> -> "aeeuz"})
val cxcxl: String = box()
val qaqxj: Ggnhz = affuy.rdso
}
// oldKlib.kt
// isKlib=true
// DONT_TARGET_EXACT_BACKEND: WASM
// WASM_MUTE_REASON: BIG_ARITY
interface A
object O : A{
class Rlldl
object Duomd
}

typealias F<T> = (A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, T) -> String

fun test(f: F<F<String>>): String =
    f(O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O) {
            _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, s ->
        s
    }

fun box(): String {
    return test {
            _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, f ->
        f(O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, "OK")
    }
}
class Ggnhz{
internal val rdso: Ggnhz  = TODO()
}
// newKlib.kt
// isKlib=true
// DONT_TARGET_EXACT_BACKEND: WASM
// WASM_MUTE_REASON: BIG_ARITY
interface A
object O : A{
class Rlldl
object Duomd
}

typealias F<T> = (A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, T) -> String

fun test(f: F<F<String>>): String =
    f(O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O) {
            _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, s ->
        s
    }

fun box(): String {
    return test {
            _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, f ->
        f(O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, "OK")
    }
}
class Ggnhz{
internal val rdso: Ggnhz  = TODO()
}
infix protected fun <T>  mkuah(): Array<LinkedHashMap<Collection<UByte>, ULong>?> { TODO() }


Combined output:
====================
Cannot access 'rdso': it is internal in 'Ggnhz'