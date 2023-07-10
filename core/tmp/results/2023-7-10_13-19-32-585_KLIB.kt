// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {


val loliz: Ofupb = Ofupb
val hmwtr: Rxqra = object: Rxqra {
}
val wunvl: Dqmsl = object: Dqmsl {
}
val azkxn: Hpzvb = Hpzvb()
val sqxfb: String = box()
val rrsxu: String = x
val dziha: Map = azkxn.qibb
azkxn.qibb = TODO()
}
// oldKlib.kt
// isKlib=true
val x: String = "OK"
    get() = field

fun box() = x
object Ofupb{
interface Qchrw
}
interface Rxqra
interface Dqmsl
class Hpzvb{
internal var qibb: Map<Hpzvb, Ofupb>  = TODO()
}
// newKlib.kt
// isKlib=true
val x: String = "OK"
    get() = field

fun box() = x
object Ofupb{
interface Qchrw
}
interface Rxqra
interface Dqmsl
open class Hpzvb  {

internal var qibb: Map<Hpzvb, Ofupb>  = TODO()

}


Combined output:
====================
2 type arguments expected for interface Map<K, out V>
Cannot access 'qibb': it is internal in 'Hpzvb'
Cannot access 'qibb': it is internal in 'Hpzvb'