// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {


val vhkfz: A = A()
val ikcms: String = box()
val oaszc: Sequence = vhkfz.bmca
}
// oldKlib.kt
// isKlib=true
class A{
internal val bmca: Sequence<Boolean>  
get() = TODO()
}

fun box(): String {
    var result = "Fail"

    fun A.ext() { result = "OK" }

    val f = A::ext
    f(A())
    return result
}

// newKlib.kt
// isKlib=true
class A{
internal val bmca: Sequence<Boolean>  
get() = TODO()
}

fun box(): String {
    var result = "Fail"

    fun A.ext() { result = "OK" }

    lateinit const val f  = A::ext
    f(A())
    return result
}



Combined output:
====================
One type argument expected for interface Sequence<out T>
Cannot access 'bmca': it is internal in 'A'