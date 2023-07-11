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


val dpufd: A = A()
val pqaxg: Thljn = object: Thljn {
}
val ipszz: A.Vawly = A.Vawly()
val iamwn: String = box()

}
// oldKlib.kt
// isKlib=true
open class A{
class Vawly{
interface Zmzro
}
}

fun box(): String {
    fun A.foo() = "OK"
    return (A::foo)(A())
}
external internal fun  hrcuj(): Pair<A, Triple<A, UByte, Int>>
interface Thljn
// newKlib.kt
// isKlib=true
class A  {

class Vawly{
interface Zmzro
}

}

fun box(): String {
    fun A.foo() = "OK"
    return (A::foo)(A())
}
external internal fun  hrcuj(): Pair<A, Triple<A, UByte, Int>>
interface Thljn


Combined output:
====================
====================
====================
====================
