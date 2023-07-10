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


val gmiqy: Uotah = Uotah()
val ebstu: Eljou = Eljou
val ukuwq: Yojyr = object: Yojyr {
}
val mgkin: Eljou.Npvzd = Eljou.Npvzd
val zjefo: Unit = foo(-38)
val qnkzo: String = box()

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
object Eljou{
object Npvzd
}
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
object Eljou{
object Npvzd
}
external internal fun  yymkj(): UByte
interface Yojyr


Combined output:
====================
====================
====================
====================
