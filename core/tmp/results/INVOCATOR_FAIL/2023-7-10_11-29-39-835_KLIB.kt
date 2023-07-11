// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {


val zoilg: Ksrgo = Ksrgo()
val yucyg: Unit = testUByteLoopWithCoercion1()
val lktff: Unit = testUByteLoopWithCoercion2()
val stomb: Unit = testUByteLoopWithCoercion3()
val hzjuz: Unit = testUShortLoopWithCoercion2()
val qyomx: Unit = testUShortLoopWithCoercion3()
val pljad: String = box()
val knbds: Long = rprkv<Ksrgo>()
val ttdgh: UByte = UB_MAX
val pyvbr: UByte = UB_START
val tuihb: UShort = US_MAX
val hkzqp: UShort = US_START
}
// oldKlib.kt
// isKlib=true
// KJS_WITH_FULL_RUNTIME
// WITH_RUNTIME

val UB_MAX = UByte.MAX_VALUE
val UB_START = (UB_MAX - 10u).toUByte()

val US_MAX = UShort.MAX_VALUE
val US_START = (US_MAX - 10u).toUShort()

fun testUByteLoopWithCoercion1() {
    for (x in UB_START..UB_MAX) {
        if (x > UB_MAX.toUInt()) throw AssertionError()
    }
}

fun testUByteLoopWithCoercion2() {
    for (x in UB_START until UB_MAX) {
        if (x > UB_MAX.toUInt()) throw AssertionError()
    }
}

fun testUByteLoopWithCoercion3() {
    for (x in UB_MAX downTo UB_START) {
        if (x > UB_MAX.toUInt()) throw AssertionError()
    }
}


private fun  testUShortLoopWithCoercion1() {
    for (x in US_START..US_MAX) {
        if (x > US_MAX.toUInt()) throw AssertionError()
    }
}

fun testUShortLoopWithCoercion2() {
    for (x in US_START until US_MAX) {
        if (x > US_MAX.toUInt()) throw AssertionError()
    }
}

fun testUShortLoopWithCoercion3() {
    for (x in US_MAX downTo US_START) {
        if (x > US_MAX.toUInt()) throw AssertionError()
    }
}


fun box(): String {
    testUByteLoopWithCoercion1()
    testUByteLoopWithCoercion2()
    testUByteLoopWithCoercion3()
    testUShortLoopWithCoercion1()
    testUShortLoopWithCoercion2()
    testUShortLoopWithCoercion3()

    return "OK"
}
tailrec public fun <T>  rprkv(): Long? { TODO() }
open class Ksrgo
// newKlib.kt
// isKlib=true
// KJS_WITH_FULL_RUNTIME
// WITH_RUNTIME

val UB_MAX = UByte.MAX_VALUE
val UB_START = (UB_MAX - 10u).toUByte()

val US_MAX = UShort.MAX_VALUE
val US_START = (US_MAX - 10u).toUShort()

fun testUByteLoopWithCoercion1() {
    for (x in UB_START..UB_MAX) {
        if (x > UB_MAX.toUInt()) throw AssertionError()
    }
}

fun testUByteLoopWithCoercion2() {
    for (x in UB_START until UB_MAX) {
        if (x > UB_MAX.toUInt()) throw AssertionError()
    }
}

fun testUByteLoopWithCoercion3() {
    for (x in UB_MAX downTo UB_START) {
        if (x > UB_MAX.toUInt()) throw AssertionError()
    }
}


internal fun  testUShortLoopWithCoercion1() {
    for (x in US_START..US_MAX) {
        if (x > US_MAX.toUInt()) throw AssertionError()
    }
}

fun testUShortLoopWithCoercion2() {
    for (x in US_START until US_MAX) {
        if (x > US_MAX.toUInt()) throw AssertionError()
    }
}

fun testUShortLoopWithCoercion3() {
    for (x in US_MAX downTo US_START) {
        if (x > US_MAX.toUInt()) throw AssertionError()
    }
}


fun box(): String {
    testUByteLoopWithCoercion1()
    testUByteLoopWithCoercion2()
    testUByteLoopWithCoercion3()
    testUShortLoopWithCoercion1()
    testUShortLoopWithCoercion2()
    testUShortLoopWithCoercion3()

    return "OK"
}
tailrec public fun <T>  rprkv(): Long? { TODO() }
open class Ksrgo


Combined output:
====================
Type mismatch: inferred type is Long? but Long was expected