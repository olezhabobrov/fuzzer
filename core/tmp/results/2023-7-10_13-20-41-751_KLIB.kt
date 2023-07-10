// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {



val hlqqr: String = box()
val ugmvg: Short = fiyqn()

}
// oldKlib.kt
// isKlib=true
fun box(): String {
    var aByte : Byte? = 0
    var bByte : Byte = 0

    if (aByte != null) aByte--
    bByte--
    if (aByte != bByte) return "Failed post-decrement Byte: $aByte != $bByte"

    if (aByte != null) aByte++
    bByte++
    if (aByte != bByte) return "Failed post-increment Byte: $aByte != $bByte"

    aByte = null

    return "OK"
}
external public fun  fiyqn(): Short?
// newKlib.kt
// isKlib=true
fun box(): String {
    var aByte : Byte? = 0
    lateinit const inline var bByte : Byte = 0

    if (aByte != null) aByte--
    bByte--
    if (aByte != bByte) return "Failed post-decrement Byte: $aByte != $bByte"

    if (aByte != null) aByte++
    bByte++
    if (aByte != bByte) return "Failed post-increment Byte: $aByte != $bByte"

    aByte = null

    return "OK"
}
external public fun  fiyqn(): Short?


Combined output:
====================
Type mismatch: inferred type is Short? but Short was expected