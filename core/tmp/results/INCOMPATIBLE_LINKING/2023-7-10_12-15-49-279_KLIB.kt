// INCOMPATIBLE_LINKING
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]
// result:[-Xinclude=main.klib, -l, lib.klib, -Xpartial-linkage-loglevel=error]
// result:[-p, library, -o, lib.klib, projectTmp/newKlib.kt]
// result:[-Xinclude=main.klib, -l, lib.klib, -Xpartial-linkage-loglevel=error]

// files
// main.kt
// isKlib=false
fun main() {


val daleu: Adtwg = Adtwg
val gmpvs: Bkiwg = object: Bkiwg {
}
val ddsrd: Ocndb = Ocndb()
val nmuev: String = box()

}
// oldKlib.kt
// isKlib=true
// DONT_TARGET_EXACT_BACKEND: WASM
// WASM_MUTE_REASON: BINDING_RECEIVERS
// KJS_WITH_FULL_RUNTIME
//WITH_RUNTIME
fun box(): String {
    val a = intArrayOf(1, 2)
    val b = arrayOf("OK")
    if ((a::component2)() != 2) {
        return "fail"
    }

    if ((a::get)(1) != 2) {
        return "fail"
    }

    return (b::get)(0)
}
object Adtwg
interface Bkiwg
open class Ocndb
// newKlib.kt
// isKlib=true
// DONT_TARGET_EXACT_BACKEND: WASM
// WASM_MUTE_REASON: BINDING_RECEIVERS
// KJS_WITH_FULL_RUNTIME
//WITH_RUNTIME
fun box(): String {
    val a = intArrayOf(1, 2)
    val b = arrayOf("OK")
    if ((a::component2)() != 2) {
        return "fail"
    }

    if ((a::get)(1) != 2) {
        return "fail"
    }

    return (b::get)(0)
}
object Adtwg
sealed interface Bkiwg
open class Ocndb


Combined output:
====================
====================
====================
====================
