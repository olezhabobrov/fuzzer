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



val zqpgu: String = box()

}
// oldKlib.kt
// isKlib=true

fun box(): String {
    try {
        throw Throwable("OK", null)
    } catch (t: Throwable) {
        if (t.cause != null) return "fail 1"
        return t.message!!
    }

    return "fail 2"
}
 inline  private fun  vazcv(): Short { TODO() }
// newKlib.kt
// isKlib=true

fun box(): String {
    try {
        throw Throwable("OK", null)
    } catch (t: Throwable) {
        if (t.cause != null) return "fail 1"
        return t.message!!
    }

    return "fail 2"
}
  


Combined output:
====================
====================
====================
====================
