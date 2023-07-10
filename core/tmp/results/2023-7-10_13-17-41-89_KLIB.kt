// INCOMPATIBLE_LINKING
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]
// result:[-Xinclude=main.klib, -l, lib.klib, -Xpartial-linkage-loglevel=error]
// result:[-p, library, -o, lib.klib, projectTmp/newKlib.kt]
// result:[-Xinclude=main.klib, -l, lib.klib, -Xpartial-linkage-loglevel=error]

// files
// oldKlib.kt
// isKlib=true
val x: String = "OK"
    get() = field

fun box() = x
// main.kt
// isKlib=false
fun main() {



val unbxh: String = box()
val bcoya: String = x
}
// newKlib.kt
// isKlib=true
internal val x : String = "OK"
get() = field

fun box() = x


Combined output:
====================
====================
====================
====================
