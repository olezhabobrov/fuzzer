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



val kafsu: String = box()

}
// oldKlib.kt
// isKlib=true
fun box(): String {
    val result = "OK"

    fun foo() = result

    return (::foo)()
}
internal fun  whkfk(): Byte? { TODO() }
// newKlib.kt
// isKlib=true
fun box(): String {
    val result = "OK"

    fun foo() = result

    return (::foo)()
}
private fun  whkfk(): Byte? { TODO() }


Combined output:
====================
====================
====================
====================
