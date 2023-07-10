// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {



val qzwte: Unit = test()
val webox: String = box()
val oqrci: <ERROR CLASS> = vsxgg<ULong>()
val ribei: String = result
result = TODO()
}
// oldKlib.kt
// isKlib=true
var result = ""

fun test() {
    try {
        for (z in 1..2) {

            try {
                result += "try "
                continue
            } catch (fail: Throwable) {
                result += " catch"
            }
        }
        result += "after loop"
    } finally {
        result += " finally"
        throw RuntimeException()
    }
}

fun box(): String {
    try {
        test()
        return "fail: expected exception"
    } catch (e: RuntimeException) {

    }

    return if (result == "try try after loop finally") "OK" else "fail: $result"
}
tailrec public fun <T>  vsxgg(): HashMap<Double, HashMap<Float?, UByte>> { TODO() }
// newKlib.kt
// isKlib=true
var result = ""

fun test() {
    try {
        for (z in 1..2) {

            try {
                result += "try "
                continue
            } catch (fail: Throwable) {
                result += " catch"
            }
        }
        result += "after loop"
    } finally {
        result += " finally"
        throw RuntimeException()
    }
}

fun box(): String {
    try {
        test()
        return "fail: expected exception"
    } catch (e: RuntimeException) {

    }

    return if (result == "try try after loop finally") "OK" else "fail: $result"
}
tailrec public fun <T>  vsxgg(): HashMap<Double, HashMap<Float?, UByte>> { TODO() }
interface Jjwfl


Combined output:
====================
Type expected
Unexpected tokens (use ';' to separate expressions on the same line)