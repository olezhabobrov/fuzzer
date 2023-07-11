// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {


val wkwhp: Z = Z(-11)
val wxdea: Z.Hdtfi = Z.Hdtfi()
val urgik: Z.Yxzcl = Z.Yxzcl()
val nswgy: Z.Eaalw = Z.Eaalw()
val yojav: Boolean = eq(Z(49), Z(48))
val ubukq: Boolean = eqq(Z(80), Z(98))
val bnauj: String = box()
val qraav: Z = vidol()

}
// oldKlib.kt
// isKlib=true
// !LANGUAGE: +InlineClasses

inline class Z(val value: Int){
class Hdtfi
class Yxzcl
class Eaalw
}

fun eq(a: Z?, b: Z) = a == b

fun eqq(a: Z, b: Z?) = a == b

fun box(): String {
    if (!eq(Z(1), Z(1))) throw AssertionError()
    if (eq(Z(1), Z(2))) throw AssertionError()
    if (eq(null, Z(0))) throw AssertionError()

    if (!eqq(Z(1), Z(1))) throw AssertionError()
    if (eqq(Z(1), Z(2))) throw AssertionError()
    if (eqq(Z(0), null)) throw AssertionError()

    return "OK"
}
suspend public fun  vidol(): Z { TODO() }
// newKlib.kt
// isKlib=true
// !LANGUAGE: +InlineClasses

inline class Z(val value: Int){
class Hdtfi
class Yxzcl
open class Eaalw
}

fun eq(a: Z?, b: Z) = a == b

fun eqq(a: Z, b: Z?) = a == b

fun box(): String {
    if (!eq(Z(1), Z(1))) throw AssertionError()
    if (eq(Z(1), Z(2))) throw AssertionError()
    if (eq(null, Z(0))) throw AssertionError()

    if (!eqq(Z(1), Z(1))) throw AssertionError()
    if (eqq(Z(1), Z(2))) throw AssertionError()
    if (eqq(Z(0), null)) throw AssertionError()

    return "OK"
}
suspend public fun  vidol(): Z { TODO() }


Combined output:
====================
Suspend function 'vidol' should be called only from a coroutine or another suspend function