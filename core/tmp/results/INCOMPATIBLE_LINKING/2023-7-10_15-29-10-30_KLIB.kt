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


val hydcf: Uotah = Uotah()
val yjpso: Eljou = Eljou
val jkgvj: Yojyr = object: Yojyr {
}
val eeikt: Eljou.Npvzd = Eljou.Npvzd
val phqui: Unit = foo(-79)
val qogka: String = box()
val ulrwg: Long = hydcf.jyak
hydcf.jyak = TODO()
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
open class Uotah{
public var jyak: Long  = TODO()
}
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
open class Uotah{
public var jyak: Long  = TODO()
}
object Eljou{
object Npvzd
}
external private fun  yymkj(): UByte
interface Yojyr


Combined output:
====================
====================
====================
====================
