// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {


val yhtgk: Z = Z(-57)
val qieys: Huhtd = Huhtd
val nlruf: String = test1_1(Z(56))
val zvvrh: String = test1_2(Z(-3))
val hxwbq: String = test1_many(Z(-15))
val wtgmx: String = test1_concat1(Z(-82))
val hxooi: String = test1_concat2(Z(69))
val frbvq: String = test1_concat3(Z(6))
val opyff: String = test2_1(Z(67))
val vovjr: String = test2_2(Z(11))
val tvtgo: String = test2_many(Z(23))
val dlfif: String = test2_concat1(Z(47))
val mezcx: String = test2_concat2(Z(-26))
val bgcbm: String = test2_concat3(Z(-30))
val gsnnb: String = box()
val tmesu: Long = yhtgk.fpcp
yhtgk.fpcp = TODO()
}
// oldKlib.kt
// isKlib=true
// !LANGUAGE: +InlineClasses
// WITH_RUNTIME

import kotlin.test.*

inline class Z(val value: Int){
private var fpcp: Long?  
get() = TODO()
set(value) = TODO()
}

fun test1_1(z: Z) = "$z"
fun test1_2(z: Z) = "$z$z"
fun test1_many(z: Z) = "$z $z $z"
fun test1_concat1(z: Z) = "-" + z
fun test1_concat2(z: Z) = "$z" + z
fun test1_concat3(z: Z) = "-" + z + z

fun test2_1(z: Z?) = "$z"
fun test2_2(z: Z?) = "$z$z"
fun test2_many(z: Z?) = "$z $z $z"
fun test2_concat1(z: Z?) = "-" + z
fun test2_concat2(z: Z?) = "$z" + z
fun test2_concat3(z: Z?) = "-" + z + z

fun box(): String {
    assertEquals("Z(value=42)", test1_1(Z(42)))
    assertEquals("Z(value=42)Z(value=42)", test1_2(Z(42)))
    assertEquals("Z(value=42) Z(value=42) Z(value=42)", test1_many(Z(42)))
    assertEquals("-Z(value=42)", test1_concat1(Z(42)))
    assertEquals("Z(value=42)Z(value=42)", test1_concat2(Z(42)))
    assertEquals("-Z(value=42)Z(value=42)", test1_concat3(Z(42)))

    assertEquals("null", test2_1(null))
    assertEquals("nullnull", test2_2(null))
    assertEquals("null null null", test2_many(null))
    assertEquals("-null", test2_concat1(null))
    assertEquals("nullnull", test2_concat2(null))
    assertEquals("-nullnull", test2_concat3(null))

    assertEquals("Z(value=42)", test2_1(Z(42)))
    assertEquals("Z(value=42)Z(value=42)", test2_2(Z(42)))
    assertEquals("Z(value=42) Z(value=42) Z(value=42)", test2_many(Z(42)))
    assertEquals("-Z(value=42)", test2_concat1(Z(42)))
    assertEquals("Z(value=42)Z(value=42)", test2_concat2(Z(42)))
    assertEquals("-Z(value=42)Z(value=42)", test2_concat3(Z(42)))

    return "OK"
}
object Huhtd
external private fun  yshqd(): Array<LinkedHashSet<Huhtd>>
// newKlib.kt
// isKlib=true
// !LANGUAGE: +InlineClasses
// WITH_RUNTIME

import kotlin.test.*

inline class Z(val value: Int){
private var fpcp: Long?  
get() = TODO()
set(value) = TODO()
}

fun test1_1(z: Z) = "$z"
fun test1_2(z: Z) = "$z$z"
fun test1_many(z: Z) = "$z $z $z"
fun test1_concat1(z: Z) = "-" + z
fun test1_concat2(z: Z) = "$z" + z
fun test1_concat3(z: Z) = "-" + z + z

fun test2_1(z: Z?) = "$z"
fun test2_2(z: Z?) = "$z$z"
fun test2_many(z: Z?) = "$z $z $z"
fun test2_concat1(z: Z?) = "-" + z
fun test2_concat2(z: Z?) = "$z" + z
fun test2_concat3(z: Z?) = "-" + z + z

fun box(): String {
    assertEquals("Z(value=42)", test1_1(Z(42)))
    assertEquals("Z(value=42)Z(value=42)", test1_2(Z(42)))
    assertEquals("Z(value=42) Z(value=42) Z(value=42)", test1_many(Z(42)))
    assertEquals("-Z(value=42)", test1_concat1(Z(42)))
    assertEquals("Z(value=42)Z(value=42)", test1_concat2(Z(42)))
    assertEquals("-Z(value=42)Z(value=42)", test1_concat3(Z(42)))

    assertEquals("null", test2_1(null))
    assertEquals("nullnull", test2_2(null))
    assertEquals("null null null", test2_many(null))
    assertEquals("-null", test2_concat1(null))
    assertEquals("nullnull", test2_concat2(null))
    assertEquals("-nullnull", test2_concat3(null))

    assertEquals("Z(value=42)", test2_1(Z(42)))
    assertEquals("Z(value=42)Z(value=42)", test2_2(Z(42)))
    assertEquals("Z(value=42) Z(value=42) Z(value=42)", test2_many(Z(42)))
    assertEquals("-Z(value=42)", test2_concat1(Z(42)))
    assertEquals("Z(value=42)Z(value=42)", test2_concat2(Z(42)))
    assertEquals("-Z(value=42)Z(value=42)", test2_concat3(Z(42)))

    return "OK"
}
object Huhtd{
interface Lpkca
}
external private fun  yshqd(): Array<LinkedHashSet<Huhtd>>


Combined output:
====================
Type mismatch: inferred type is Long? but Long was expected
Cannot access 'fpcp': it is private in 'Z'
Cannot access 'fpcp': it is private in 'Z'