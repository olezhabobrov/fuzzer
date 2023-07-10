// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {


val lbxxg: A = A()
val uphek: Thljn = object: Thljn {
}
val vcacz: A.Vawly = A.Vawly()
val svahy: String = box()
val mnvqd: Function2 = lbxxg.wapy
lbxxg.wapy = TODO()
}
// oldKlib.kt
// isKlib=true
open class A{
class Vawly{
interface Zmzro
}
protected var wapy: Function2<Float, UByte, Char>  = {a: Float, b: UByte -> '꽌'}
}

fun box(): String {
    fun A.foo() = "OK"
    return (A::foo)(A())
}
external internal fun  hrcuj(): Pair<A, Triple<A, UByte, Int>>
interface Thljn
// newKlib.kt
// isKlib=true
open class A{
class Vawly{
interface Zmzro
}
protected var wapy: Function2<Float, UByte, Char>  = {a: Float, b: UByte -> '꽌'}
object Yuxlh
}

fun box(): String {
    fun A.foo() = "OK"
    return (A::foo)(A())
}
external internal fun  hrcuj(): Pair<A, Triple<A, UByte, Int>>
interface Thljn


Combined output:
====================
3 type arguments expected for interface Function2<in P1, in P2, out R>
Cannot access 'wapy': it is protected in 'A'
Cannot access 'wapy': it is protected in 'A'