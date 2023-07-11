// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {


val aacmu: Locit = object: Locit {
}
val pbykg: String = box()
val zrhxl: List = unvbr()
val plquz: String = v2
}
// oldKlib.kt
// isKlib=true
// FILE: 1.kt

private val v1 : String = "V1"

fun box(): String {
    val s  = "v1: $v1, v2: $v2"
    return "OK"
}

// FILE: 2.kt

public val v2 : String = "V2"
interface Locit
external public fun  unvbr(): List<Boolean>
// newKlib.kt
// isKlib=true
// FILE: 1.kt

private val v1 : String = "V1"

fun box(): String {
    val s  = "v1: $v1, v2: $v2"
    return "OK"
}

// FILE: 2.kt

public val v2 : String = "V2"
interface Locit
external public fun  unvbr(): List<Boolean>
external  fun  swool(): UByte


Combined output:
====================
One type argument expected for interface List<out E>