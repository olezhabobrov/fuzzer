// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {


val vqtgf: A = A()
val gobkn: A.Companion = A.Companion
val ahefo: String = box()
val qmsyi: Char = vqtgf.kdcq
}
// oldKlib.kt
// isKlib=true
class A() {
    companion object {
        val value = 10
    }
val Char.kdcq: Byte  
get() =  TODO()
}

fun box() = if (A.value == 10) "OK" else "Fail ${A.value}"

// newKlib.kt
// isKlib=true
class A() {
    companion object {
        val value = 10
    }
val Char.kdcq: Byte  
get() =  TODO()
public var ixjx: Char  = TODO()
}

fun box() = if (A.value == 10) "OK" else "Fail ${A.value}"



Combined output:
====================
Unresolved reference: kdcq