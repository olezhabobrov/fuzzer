// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {


val dpmcp: Mkrfd = Mkrfd()
val uklpm: String = box()
val jmvlz: Function2 = euhod<Pair<Char?, Float>, UInt>()

}
// oldKlib.kt
// isKlib=true
// WITH_RUNTIME
import kotlin.test.*

fun box(): String {
    var sum  = 0
    for (i in (4 downTo 1).reversed().reversed()) {
        sum = sum * 10 + i
    }
    assertEquals(4321, sum)

    var sumL = 0L
    for (i in (4L downTo 1L).reversed().reversed()) {
        sumL = sumL * 10 + i
    }
    assertEquals(4321, sumL)

    var sumC  = 0
    for (i in ('4' downTo '1').reversed().reversed()) {
        sumC = sumC * 10 + i.toInt() - '0'.toInt()
    }
    assertEquals(4321, sumC)

    return "OK"
}
 
public fun <T, S>  euhod(): Function2<Short, ArrayList<ArrayList<Triple<Byte, Int, Boolean?>>>, Byte> { TODO() }
class Mkrfd
// newKlib.kt
// isKlib=true
// WITH_RUNTIME
import kotlin.test.*

abstract fun  box(): String
 
public fun <T, S>  euhod(): Function2<Short, ArrayList<ArrayList<Triple<Byte, Int, Boolean?>>>, Byte> { TODO() }
class Mkrfd


Combined output:
====================
3 type arguments expected for interface Function2<in P1, in P2, out R>