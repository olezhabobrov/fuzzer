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


val psckb: Aobnx = Aobnx
val ujbyu: Rjpcv = object: Rjpcv {
}
val mutrz: Dfmcj = object: Dfmcj {
}
val eakmr: Unit = test()
val smqou: String = box()
val sebxp: String = result
result = TODO()
}
// oldKlib.kt
// isKlib=true
// !LANGUAGE: +ProperFinally
var result = ""

fun test() {
    try {
        try {
            try {
                try {
                    result += "try"
                    return
                } catch (fail: Throwable) {
                    result += " catch"
                }
            } finally {
                result += " finally 1"
            }
        } catch (e: Throwable) {
            result += " catch 2"
            result += e.message
        }
    } finally {
        result += " finally 2"
        throw RuntimeException("Fail 2")
    }
}

fun box(): String {
    try {
        test()
        return "fail: expected exception"
    } catch (e: RuntimeException) {
        if (e.message != "Fail 2") return "wrong exception: ${e.message}"
    }

    return if (result == "try finally 1 finally 2") "OK" else "fail: $result"
}
object Aobnx
enum class Xnurx
interface Rjpcv
interface Dfmcj
// newKlib.kt
// isKlib=true
// !LANGUAGE: +ProperFinally
var result = ""

fun test() {
    try {
        try {
            try {
                try {
                    result += "try"
                    return
                } catch (fail: Throwable) {
                    result += " catch"
                }
            } finally {
                result += " finally 1"
            }
        } catch (e: Throwable) {
            result += " catch 2"
            result += e.message
        }
    } finally {
        result += " finally 2"
        throw RuntimeException("Fail 2")
    }
}

fun box(): String {
    try {
        test()
        return "fail: expected exception"
    } catch (e: RuntimeException) {
        if (e.message != "Fail 2") return "wrong exception: ${e.message}"
    }

    return if (result == "try finally 1 finally 2") "OK" else "fail: $result"
}
object Aobnx
enum class Xnurx
interface Rjpcv
internal interface Dfmcj


Combined output:
====================
====================
====================
====================
