// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {


val fquwr: Fhoez = Fhoez()
val pozcx: Unit = foo<Number?>(BigDecimal(93.97002546226642))
val wfxdu: String = box()

}
// oldKlib.kt
// isKlib=true
fun <T: Number?> foo(t: T) {
    (t ?: 42).toInt()
}

fun box(): String {
    foo<Int?>(null)
    return "OK"
}
class Fhoez
// newKlib.kt
// isKlib=true
fun <T: Number?> foo(t: T) {
    (t ?: 42).toInt()
}

fun box(): String {
    foo<Int?>(null)
    return "OK"
}
class Fhoez{
protected  fun  gtabk(): LinkedHashMap<Long, Boolean> { TODO() }
}


Combined output:
====================
Unresolved reference: BigDecimal