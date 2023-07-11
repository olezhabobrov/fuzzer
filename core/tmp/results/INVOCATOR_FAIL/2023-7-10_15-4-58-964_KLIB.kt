// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {


val svwdz: A = A()
val tpblu: Tprko = Tprko()
val hzwbf: A.Nested = A.Nested()
val mwhvf: A.Nested.Companion = A.Nested.Companion
val lhmsu: A.Nested.Abnak = A.Nested.Abnak
val nzpnc: String = box()
val ufhnn: Byte = tbrsp()
val gdfis: Int = mwhvf.invoke(49)
val uevoz: Tprko = lhmsu.nzxel()

}
// oldKlib.kt
// isKlib=true
open class A  {

    class Nested {
        companion object {
            operator fun invoke(i: Int) = i
        interface Vpfdp
}
    object Abnak{
private  fun  nzxel(): Tprko? { TODO() }
}
}

}

fun box() = if (A.Nested(42) == 42) "OK" else "fail"
fun  tbrsp(): Byte { TODO() }
sealed class Xwgim
class Tprko
private fun <T>  bdbfy(): Tprko { TODO() }
// newKlib.kt
// isKlib=true
open class A  {

    class Nested {
        companion object {
            operator fun invoke(i: Int) = i
        interface Vpfdp
}
    object Abnak{
private  fun  nzxel(): Tprko? { TODO() }
}
}

}

fun box() = if (A.Nested(42) == 42) "OK" else "fail"
fun  tbrsp(): Byte { TODO() }
sealed open class Xwgim
class Tprko
private fun <T>  bdbfy(): Tprko { TODO() }


Combined output:
====================
Type mismatch: inferred type is Tprko? but Tprko was expected
Cannot access 'nzxel': it is private in 'Abnak'