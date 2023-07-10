// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {


val svahl: I = object: I {
override fun f(): String { TODO() }
}
val rfzfx: II = object: II {
override fun f(): String { TODO() }
}
val bfuhp: Iwrsi = Iwrsi()
val wmkgb: String = rn(I{ "lqwkj"})
val nqvpz: String = rnInline(I{ "fnmod"})
val rbndv: I = rnInlineCtor("bawdi")
val gqetq: String = rn2({ "jchmi"})
val bqvga: String = rn2Inline({ "mwcus"})
val eqezf: String = rnInlineCtorProxy("igfhv")
val epzlq: II = id(II{ "ootfn"})
val chzsv: String = box()
val pdhfe: String = svahl.f()
val ayvge: String = rfzfx.f()
val vmzvu: <ERROR CLASS> = bfuhp.chpk
}
// oldKlib.kt
// isKlib=true
// DONT_TARGET_EXACT_BACKEND: WASM
// WASM_MUTE_REASON: SAM_CONVERSIONS
// !LANGUAGE: +NewInference +FunctionalInterfaceConversion +SamConversionPerArgument +SamConversionForKotlinFunctions

// MODULE: m1
// FILE: m1.kt

fun interface I {
    fun f(): String
}

fun rn(i: I) = i.f()

inline fun rnInline(i: I) = i.f()

inline fun rnInlineCtor(s: String) = I { s + ".m1" }

// MODULE: m2(m1)
// FILE: m2.kt

fun rn2(f: () -> String) = rn(f)

inline fun rn2Inline(noinline f: () -> String) = rnInline(f)

inline fun rnInlineCtorProxy(s: String) = rnInlineCtor(s + ".m2").f()

fun interface II {
    fun f(): String
}

// MODULE: main(m2)
// FILE: main.kt

fun id(i: II) = i

fun box(): String {

    if (id { "1" }.f() != "1") return "fail 1"

    if (II { "2" }.f() != "2") return "fail 2"

    if (rnInlineCtorProxy("3") != "3.m2.m1") return "fail 3"

    if (rn2Inline { "inline" } != "inline") return "fail 4"

    return rn2 { "OK" }
}
open class Iwrsi{
private val chpk: ArrayList<Long>  
get() = TODO()
}
// newKlib.kt
// isKlib=true
// DONT_TARGET_EXACT_BACKEND: WASM
// WASM_MUTE_REASON: SAM_CONVERSIONS
// !LANGUAGE: +NewInference +FunctionalInterfaceConversion +SamConversionPerArgument +SamConversionForKotlinFunctions

// MODULE: m1
// FILE: m1.kt

fun interface I {
    fun f(): String
}

fun rn(i: I) = i.f()

inline fun rnInline(i: I) = i.f()

inline fun rnInlineCtor(s: String) = I { s + ".m1" }

// MODULE: m2(m1)
// FILE: m2.kt

fun rn2(f: () -> String) = rn(f)

inline fun rn2Inline(noinline f: () -> String) = rnInline(f)

inline fun rnInlineCtorProxy(s: String) = rnInlineCtor(s + ".m2").f()

fun interface II {
    fun f(): String
}

// MODULE: main(m2)
// FILE: main.kt

fun id(i: II) = i

fun box(): String {

    if (id { "1" }.f() != "1") return "fail 1"

    if (II { "2" }.f() != "2") return "fail 2"

    if (rnInlineCtorProxy("3") != "3.m2.m1") return "fail 3"

    if (rn2Inline { "inline" } != "inline") return "fail 4"

    return rn2 { "OK" }
}
open class Iwrsi{
private val chpk: ArrayList<Long>  
get() = TODO()
public  fun  pcsfj(): ULong? { TODO() }
}


Combined output:
====================
Type expected
Unexpected tokens (use ';' to separate expressions on the same line)