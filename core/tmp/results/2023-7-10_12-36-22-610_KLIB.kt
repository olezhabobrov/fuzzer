// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {


val cnnnf: Ftmhg = Ftmhg()
val mwvwl: Ftmhg.Cvuka = Ftmhg.Cvuka()
val vmhxx: Ftmhg.Cvuka.Viidu = Ftmhg.Cvuka.Viidu()
val tslka: String = box()
val ucpnk: Int = cnnnf.luto
}
// oldKlib.kt
// isKlib=true
// DONT_TARGET_EXACT_BACKEND: WASM
// WASM_MUTE_REASON: STDLIB_COLLECTIONS

tailrec fun  box(): String {
    val s  = ArrayList<String>()
    s.add("foo")
    s[0] += "bar"
    return if(s[0] == "foobar") "OK" else "fail"
}
class Ftmhg{
class Cvuka{
class Viidu
}
internal val luto: Int  
get() = TODO()
}
// newKlib.kt
// isKlib=true
// DONT_TARGET_EXACT_BACKEND: WASM
// WASM_MUTE_REASON: STDLIB_COLLECTIONS

tailrec fun  box(): String {
    val s  = ArrayList<String>()
    s.add("foo")
    s[0] += "bar"
    return if(s[0] == "foobar") "OK" else "fail"
}
class Ftmhg{
class Cvuka{
class Viidu
}
internal val luto: Int  
get() = TODO()
}
protected fun  ycloh(): Ftmhg { TODO() }


Combined output:
====================
Cannot access 'luto': it is internal in 'Ftmhg'