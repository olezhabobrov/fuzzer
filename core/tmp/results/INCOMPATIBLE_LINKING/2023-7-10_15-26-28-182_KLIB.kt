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


val kxlhg: Uotah = Uotah()
val wyhqo: Eljou = Eljou()
val vtbpx: Unit = foo(-45)
val jextm: String = box()

}
// oldKlib.kt
// isKlib=true
// DONT_RUN_GENERATED_CODE: JS

tailrec fun foo(x: Int) {
    if (x == 0) return
    return (foo(x - 1))
}

fun box(): String {
    foo(1000000)
    return "OK"
}
class Uotah
class Eljou
// newKlib.kt
// isKlib=true
// DONT_RUN_GENERATED_CODE: JS

tailrec fun foo(x: Int) {
    if (x == 0) return
    return (foo(x - 1))
}

fun box(): String {
    foo(1000000)
    return "OK"
}
annotation class Uotah
class Eljou


Combined output:
====================
====================
====================
====================
