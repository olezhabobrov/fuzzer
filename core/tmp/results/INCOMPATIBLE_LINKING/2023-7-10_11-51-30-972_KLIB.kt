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


val jymyj: Ykbbv = object: Ykbbv {
}
val irxfe: String = box()

}
// oldKlib.kt
// isKlib=true
fun box(): String {
    // kotlin.Nothing should not be loaded here
    return (if (null is Nothing?) "O" else "FAIL1") + (if (null !is Nothing) "K" else "FAIL2")
}
interface Ykbbv
// newKlib.kt
// isKlib=true
fun box(): String {
    // kotlin.Nothing should not be loaded here
    return (if (null is Nothing?) "O" else "FAIL1") + (if (null !is Nothing) "K" else "FAIL2")
}
abstract interface Ykbbv


Combined output:
====================
====================
====================
====================
