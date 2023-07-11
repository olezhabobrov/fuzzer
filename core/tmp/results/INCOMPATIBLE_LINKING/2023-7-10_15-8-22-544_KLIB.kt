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


val cuytl: A = A("shoaz")
val qniqy: C = C
val faoge: Jmsqp = Jmsqp
val gbqrh: String = a({ "pivup"})
val ezirw: String = box()
val rlxtc: String = qniqy.g
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
object Jmsqp
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
object Jmsqp


Combined output:
====================
====================
====================
====================
