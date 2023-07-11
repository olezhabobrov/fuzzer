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


val oteud: Wbwjc = object: Wbwjc {
}
val pgtmp: Wbwjc.Idzir = Wbwjc.Idzir
val nyyzk: String = box()

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
var acoo: Wbwjc
}
suspend private fun <T, S>  mpwfo(): Wbwjc { TODO() }


Combined output:
====================
====================
====================
====================
Abstract property accessor 'acoo.<get-acoo>' is not implemented in non-abstract anonymous object
Abstract property accessor 'acoo.<set-acoo>' is not implemented in non-abstract anonymous object