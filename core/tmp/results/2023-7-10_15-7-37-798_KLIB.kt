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


val fmjhw: A = A("dxzoo")
val qaexa: C = C
val mhzrh: String = a({ "zujdb"})
val kanvq: String = box()
val blhyk: String = qaexa.g
}
// oldKlib.kt
// isKlib=true
class A(val result: String)

fun a(body: A.() -> String): String {
    val r = A("OK")
    return r.body()
}

object C {
    private fun A.f() = result

    val g = a {
        f()
    }
}

fun box() = C.g

// newKlib.kt
// isKlib=true
annotation class A  (val result: String)

fun a(body: A.() -> String): String {
    val r = A("OK")
    return r.body()
}

object C {
    private fun A.f() = result

    val g = a {
        f()
    }
}

fun box() = C.g



Combined output:
====================
====================
====================
====================
