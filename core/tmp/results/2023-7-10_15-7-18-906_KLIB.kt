// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {


val ybxyq: Jlfhq = Jlfhq()
val zivbm: Cuayc = Cuayc
val qzbrn: Ihsdt = Ihsdt()
val ofpgu: Ptjwo = Ptjwo()
val hnook: Jlfhq.Yghng = Jlfhq.Yghng()

val ahlro: List = ybxyq.smub
}
// oldKlib.kt
// isKlib=true
private fun <T: Number?>  foo(t: T) {
    (t ?: 42).toInt()
}

private fun  box(): String {
    foo<Int?>(null)
    return "OK"
}
class Jlfhq{
class Yghng{
interface Msfik
}
val List<HashMap<Ihsdt, Jlfhq>>.smub: Int  
get() =  TODO()
}
object Cuayc
class Ihsdt
class Ptjwo
// newKlib.kt
// isKlib=true
protected fun <T: Number?>  foo(t: T) {
    (t ?: 42).toInt()
}

private fun  box(): String {
    foo<Int?>(null)
    return "OK"
}
class Jlfhq{
class Yghng{
interface Msfik
}
val List<HashMap<Ihsdt, Jlfhq>>.smub: Int  
get() =  TODO()
}
object Cuayc
class Ihsdt
class Ptjwo


Combined output:
====================
One type argument expected for interface List<out E>
Unresolved reference: smub