// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {


val voytt: Zqgfa = Zqgfa()
val zjatf: String = box()
val dumzq: List = voytt.zoqp
voytt.zoqp = TODO()
}
// oldKlib.kt
// isKlib=true
fun box(): String {
    class A  {

private val rxod: String  = TODO()

public val vljx: Short  
get() = TODO()
}
    fun A.foo() = "OK"
    return (A::foo)((::A)())
}
open class Zqgfa{
public var zoqp: List<Char>  = TODO()
}
 inline  internal fun  kisgl(): Zqgfa { TODO() }
// newKlib.kt
// isKlib=true
fun box(): String {
    class A  {

private val rxod: String  = TODO()

public val vljx: Short  
get() = TODO()
}
    fun A.foo() = "OK"
    return (A::foo)((::A)())
}
open abstract class Zqgfa  {

public var zoqp: List<Char>  = TODO()

}
 inline  internal fun  kisgl(): Zqgfa { TODO() }


Combined output:
====================
One type argument expected for interface List<out E>