// COMPATIBLE_NOT_LINKING
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]
// result:[-Xinclude=main.klib, -l, lib.klib, -Xpartial-linkage-loglevel=error]
// result:[-p, library, -o, lib.klib, projectTmp/newKlib.kt]
// result:[-Xinclude=main.klib, -l, lib.klib, -Xpartial-linkage-loglevel=error]

// files
// main.kt
// isKlib=false
fun main() {


val kufic: Wbwjc = object: Wbwjc {
}
val fqbfd: Wbwjc.Idzir = Wbwjc.Idzir
val jlqmw: String = box()

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
suspend private fun <B, S>  mpwfo(): Wbwjc { TODO() }
// newKlib.kt
// isKlib=true
fun box(): String {
    !true
    return "OK"
}
interface Wbwjc{
 
object Idzir
val aizm: Double
}
suspend private fun <B, S>  mpwfo(): Wbwjc { TODO() }


Combined output:
====================
====================
====================
====================
Abstract property accessor 'aizm.<get-aizm>' is not implemented in non-abstract anonymous object