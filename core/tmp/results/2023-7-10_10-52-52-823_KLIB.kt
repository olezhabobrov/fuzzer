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


val qpmjb: Wbwjc = object: Wbwjc {
}
val oxxii: Wbwjc.Idzir = Wbwjc.Idzir
val pvfgd: String = box()

}
// oldKlib.kt
// isKlib=true
fun box(): String {
    !true
    return "OK"
}
interface Wbwjc{
 
object Idzir
}
suspend private fun <T, S>  mpwfo(): Wbwjc { TODO() }
// newKlib.kt
// isKlib=true
fun box(): String {
    !true
    return "OK"
}
interface Wbwjc{
 
object Idzir
}
 


Combined output:
====================
====================
====================
====================
