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


val ahobr: A = A("xukoa")
val tjkbx: C = C
val imare: Jmsqp = Jmsqp
val rgwdw: Osnli = Osnli()
val aidva: Szmun = object: Szmun {
}
val wrhdp: A.Qtvkv = A.Qtvkv
val htjof: String = a({ "slpln"})
val ztame: String = box()
val tsjma: String = tjkbx.g
}
// oldKlib.kt
// isKlib=true
open class A  (val result: String){

object Qtvkv

}

fun a(body: A.() -> String): String {
    val r  = A("OK")
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
open class Osnli
interface Szmun
// newKlib.kt
// isKlib=true
open class A  (val result: String){

object Qtvkv

}

fun a(body: A.() -> String): String {
    val r  = A("OK")
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
class Osnli
interface Szmun


Combined output:
====================
====================
====================
====================
