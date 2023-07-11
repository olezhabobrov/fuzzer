// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {


val bbxtv: Adtwg = Adtwg
val cbizh: Bkiwg = object: Bkiwg {
}
val tvqce: Ocndb = Ocndb()
val zovxg: Ildcz = Ildcz
val piaok: String = box()
val jgjun: <ERROR CLASS> = zovxg.fhzzh()

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
object Ildcz{
tailrec   fun  fhzzh(): HashMap<HashMap<String?, Ildcz>, MutableMap<Function1<Ildcz, Int>, Function2<Set<HashMap<UInt, Char>>?, LinkedHashMap<UByte, Ocndb>, Ildcz>>>? { TODO() }
}
// newKlib.kt
// isKlib=true
abstract fun  box(): String
object Adtwg
interface Bkiwg
open class Ocndb
object Ildcz{
tailrec   fun  fhzzh(): HashMap<HashMap<String?, Ildcz>, MutableMap<Function1<Ildcz, Int>, Function2<Set<HashMap<UInt, Char>>?, LinkedHashMap<UByte, Ocndb>, Ildcz>>>? { TODO() }
}


Combined output:
====================
Type expected
Unexpected tokens (use ';' to separate expressions on the same line)