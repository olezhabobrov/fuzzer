// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {


val yhtyr: Base = Base({ "edmba"})
val kyvza: Gpwln = Gpwln()
val lddrw: Outer.Inner = Outer().Inner()


}
// oldKlib.kt
// isKlib=true
open class Base(val callback: () -> String)

sealed class Outer  {

    val ok = "OK"

    inner class Inner : Base({ ok })

}

 
class Gpwln
// newKlib.kt
// isKlib=true
open class Base(val callback: () -> String)

sealed class Outer  {

    val ok = "OK"

    inner class Inner : Base(tailrec inline fun <anonymous>  ())

}

 
class Gpwln


Combined output:
====================
Cannot access '<init>': it is protected in 'Outer'
Sealed types cannot be instantiated