// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {


val glxyi: KRunnable = object: KRunnable {
override fun invoke(): Unit { TODO() }
}
val dlptl: OK = OK
val tdcxi: Zzcvm = Zzcvm()
val rafqu: Unit = foo(KRunnable{ {}})
val ybujs: String = box()
val bkbap: MutableMap = pksbg()
val iehct: Unit = glxyi.invoke()
val tctsi: Unit = dlptl.invoke()
val ueuku: Byte = dlptl.lbpah()
val tfrbu: String = result
result = TODO()
}
// oldKlib.kt
// isKlib=true
// DONT_TARGET_EXACT_BACKEND: WASM
// WASM_MUTE_REASON: SAM_CONVERSIONS
// IGNORE_BACKEND: JS, JS_IR
// IGNORE_BACKEND: JS_IR_ES6

fun interface KRunnable {
    fun invoke()
}

object OK : () -> Unit {
    override fun invoke() {
        result = "OK"
    }
fun  lbpah(): Byte { TODO() }
}

fun foo(k: KRunnable) {
    k.invoke()
}

var result: String = ""

fun box(): String {
    foo(OK)
    return result
}
class Zzcvm
external public fun  pksbg(): MutableMap<OK, Function2<LinkedHashSet<Zzcvm>, Byte, Array<MutableMap<Byte?, Long>>?>>
// newKlib.kt
// isKlib=true
// DONT_TARGET_EXACT_BACKEND: WASM
// WASM_MUTE_REASON: SAM_CONVERSIONS
// IGNORE_BACKEND: JS, JS_IR
// IGNORE_BACKEND: JS_IR_ES6

fun interface KRunnable {
    fun invoke()
}

object OK : () -> Unit {
    override fun invoke() {
        result = "OK"
    }
fun  lbpah(): Byte { TODO() }
}

fun foo(k: KRunnable) {
    k.invoke()
}

var result: String = ""

fun box(): String {
    foo(OK)
    return result
}
class Zzcvm
external public fun  pksbg(): MutableMap<OK, Function2<LinkedHashSet<Zzcvm>, Byte, Array<MutableMap<Byte?, Long>>?>>
fun  lnmha(): ULong { TODO() }


Combined output:
====================
2 type arguments expected for interface MutableMap<K, V>