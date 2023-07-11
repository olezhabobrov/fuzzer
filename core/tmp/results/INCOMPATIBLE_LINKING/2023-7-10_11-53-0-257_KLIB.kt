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


val heuwp: Ykbbv = object: Ykbbv {
}
val fxnhr: Float = dknox()

}
// oldKlib.kt
// isKlib=true
private fun  box(): String {
    // kotlin.Nothing should not be loaded here
    return (if (null is Nothing?) "O" else "FAIL1") + (if (null !is Nothing) "K" else "FAIL2")
}
interface Ykbbv
external internal fun  xplna(): Ykbbv
fun  dknox(): Float { TODO() }
// newKlib.kt
// isKlib=true
private fun  box(): String {
    // kotlin.Nothing should not be loaded here
    return (if (null is Nothing?) "O" else "FAIL1") + (if (null !is Nothing) "K" else "FAIL2")
}
interface Ykbbv
external private fun  xplna(): Ykbbv
fun  dknox(): Float { TODO() }


Combined output:
====================
====================
====================
====================
