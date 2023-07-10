// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {


val wlkvw: Ttmix = Ttmix()
val qxxfo: Hdenh = object: Hdenh {
}
val kmxtr: Ttmix.Rzdkk = Ttmix.Rzdkk()
val rqvds: Boolean = equals1(-81.71360889959409, 11.783972117346693)
val chzht: Boolean = equals2(45.993379240878596, 46.32144200654065)
val kcsql: Boolean = equals3(26.028146563094936, -59.102838139086764)
val ygfrc: Boolean = equals4(55.31006776461257, -99.19030651259841)
val uctre: Boolean = equals5("wexhh", "ofzlv")
val ecgfg: String = box()
val mcmsf: Int = kmxtr.pellw()
val dglzr: Ttmix = wlkvw.okcs
wlkvw.okcs = TODO()
}
// oldKlib.kt
// isKlib=true
// DONT_TARGET_EXACT_BACKEND: WASM
// WASM_MUTE_REASON: IGNORED_IN_JS
// !LANGUAGE: -ProperIeee754Comparisons
// DONT_TARGET_EXACT_BACKEND: JS_IR
// DONT_TARGET_EXACT_BACKEND: JS_IR_ES6

fun equals1(a: Double, b: Double) = a == b

fun equals2(a: Double?, b: Double?) = a!! == b!!

fun equals3(a: Double?, b: Double?) = a != null && b != null && a == b

fun equals4(a: Double?, b: Double?) = if (a is Double && b is Double) a == b else null!!

fun equals5(a: Any?, b: Any?) = if (a is Double && b is Double) a == b else null!!


fun box(): String {
    if (-0.0 != 0.0) return "fail 0"
    if (!equals1(-0.0, 0.0)) return "fail 1"
    if (!equals2(-0.0, 0.0)) return "fail 2"
    if (!equals3(-0.0, 0.0)) return "fail 3"
    if (!equals4(-0.0, 0.0)) return "fail 4"

    // Smart casts behavior in 1.2
    if (equals5(-0.0, 0.0)) return "fail 5"

    return "OK"
}

internal fun  kquwu(): Float { TODO() }
open class Ttmix  {

class Rzdkk{
private  fun  pellw(): Int { TODO() }
}
lateinit var okcs: Ttmix

}
interface Hdenh
// newKlib.kt
// isKlib=true
// DONT_TARGET_EXACT_BACKEND: WASM
// WASM_MUTE_REASON: IGNORED_IN_JS
// !LANGUAGE: -ProperIeee754Comparisons
// DONT_TARGET_EXACT_BACKEND: JS_IR
// DONT_TARGET_EXACT_BACKEND: JS_IR_ES6

fun equals1(a: Double, b: Double) = a == b

fun equals2(a: Double?, b: Double?) = a!! == b!!

fun equals3(a: Double?, b: Double?) = a != null && b != null && a == b

fun equals4(a: Double?, b: Double?) = if (a is Double && b is Double) a == b else null!!

fun equals5(a: Any?, b: Any?) = if (a is Double && b is Double) a == b else null!!


fun box(): String {
    if (-0.0 != 0.0) return "fail 0"
    if (!equals1(-0.0, 0.0)) return "fail 1"
    if (!equals2(-0.0, 0.0)) return "fail 2"
    if (!equals3(-0.0, 0.0)) return "fail 3"
    if (!equals4(-0.0, 0.0)) return "fail 4"

    // Smart casts behavior in 1.2
    if (equals5(-0.0, 0.0)) return "fail 5"

    return "OK"
}

internal fun  kquwu(): Float { TODO() }
open class Ttmix  {

class Rzdkk{
private  fun  pellw(): Int { TODO() }
}
lateinit var okcs: Ttmix

}
interface Hdenh
class Cscqw


Combined output:
====================
Cannot access 'pellw': it is private in 'Rzdkk'