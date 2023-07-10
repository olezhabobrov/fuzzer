// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {


val pizhu: Ahzlc = Ahzlc()
val iscir: String = box()
val orrin: UInt = ua
val vextx: UInt = ub
val gyzhm: Byte = pizhu.hjgm
pizhu.hjgm = TODO()
}
// oldKlib.kt
// isKlib=true
// KJS_WITH_FULL_RUNTIME
// WITH_RUNTIME

val ua = 1234U
val ub = 5678U

fun box(): String {
    if (ua.compareTo(ub) > 0) {
        throw AssertionError()
    }

    return "OK"
}
class Ahzlc{
private var hjgm: Byte?  
get() = TODO()
set(value) = TODO()
}
// newKlib.kt
// isKlib=true
// KJS_WITH_FULL_RUNTIME
// WITH_RUNTIME

val ua = 1234U
val ub = 5678U

fun box(): String {
    if (ua.compareTo(ub) > 0) {
        throw AssertionError()
    }

    return "OK"
}
class Ahzlc{
private const var hjgm : Byte? 
get() = TODO()
set(value) = TODO()
}


Combined output:
====================
Type mismatch: inferred type is Byte? but Byte was expected
Cannot access 'hjgm': it is private in 'Ahzlc'
Cannot access 'hjgm': it is private in 'Ahzlc'