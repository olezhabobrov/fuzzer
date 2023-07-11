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


val zawll: Locit = object: Locit {
}
val sqfhw: String = box()
val xqgve: String = v2
v2 = TODO()
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

public var v2: String = "V2"
interface Locit
external internal fun  unvbr(): List<Boolean>
// newKlib.kt
// isKlib=true
// FILE: 1.kt

private val v1 : String = "V1"

fun box(): String {
    val s  = "v1: $v1, v2: $v2"
    return "OK"
}

// FILE: 2.kt

public var v2: String = "V2"
sealed interface Locit
external internal fun  unvbr(): List<Boolean>


Combined output:
====================
====================
====================
====================
