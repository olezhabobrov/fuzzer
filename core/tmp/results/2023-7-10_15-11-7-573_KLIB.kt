// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {


val xwmpn: A = A("amdqb")
val lascp: C = C
val ascvr: Jmsqp = Jmsqp
val mdvlr: Osnli = Osnli()
val csvri: Szmun = object: Szmun {
}
val hcosa: A.Qtvkv = A.Qtvkv
val xlfnr: String = a({ "cyxqc"})
val koquj: String = box()
val echdm: Set = xwmpn.vlek
val mjsvv: String = lascp.g
}
// oldKlib.kt
// isKlib=true
open class A  (val result: String){

object Qtvkv

internal val vlek: Set<Boolean>  
get() = TODO()
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
external internal fun <T, S>  jsubf(): UInt?
// newKlib.kt
// isKlib=true
class A  (val result: String){


object Qtvkv

internal val vlek: Set<Boolean>  
get() = TODO()

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
external internal fun <T, S>  jsubf(): UInt?


Combined output:
====================
One type argument expected for interface Set<out E>
Cannot access 'vlek': it is internal in 'A'