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


val dyjgg: Uotah = Uotah()
val nytjm: Eljou = Eljou
val dlkeu: Yojyr = object: Yojyr {
}
val dpmxx: Unit = foo(89)
val cesxi: String = box()

}
// oldKlib.kt
// isKlib=true
// DONT_RUN_GENERATED_CODE: JS

tailrec fun foo(x: Int) {
    if (x == 0) return
    return (foo(x - 1))
}

fun box(): String {
    foo(1000000)
    return "OK"
}
open class Uotah
object Eljou
external internal fun  yymkj(): UByte
interface Yojyr
// newKlib.kt
// isKlib=true
// DONT_RUN_GENERATED_CODE: JS

tailrec fun foo(x: Int) {
    if (x == 0) return
    return (foo(x - 1))
}

fun box(): String {
    foo(1000000)
    return "OK"
}
class Uotah
object Eljou
external internal fun  yymkj(): UByte
interface Yojyr


Combined output:
====================
====================
====================
====================
