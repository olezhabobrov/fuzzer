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


val czeue: Ritcc = Ritcc()
val thoal: Ritcc.Whnpx = Ritcc.Whnpx()
val jgknf: String = box()

}
// oldKlib.kt
// isKlib=true
// KJS_WITH_FULL_RUNTIME
// WITH_RUNTIME

inline fun  box(): String {
    var sum  = 0u
    val ls = listOf(1u, 2u, 3u)
    for (el in ls) {
        sum += el
    }

    return if (sum != 6u) "Fail" else "OK"
}
class Ritcc{
interface Ampuz
class Whnpx{
private var bdmt: UInt  = 44.toUInt()
}
}
// newKlib.kt
// isKlib=true
// KJS_WITH_FULL_RUNTIME
// WITH_RUNTIME

inline fun  box(): String {
    var sum  = 0u
    val ls = listOf(1u, 2u, 3u)
    for (el in ls) {
        sum += el
    }

    return if (sum != 6u) "Fail" else "OK"
}
class Ritcc{
private interface Ampuz
class Whnpx{
private var bdmt: UInt  = 44.toUInt()
}
}


Combined output:
====================
====================
====================
====================
