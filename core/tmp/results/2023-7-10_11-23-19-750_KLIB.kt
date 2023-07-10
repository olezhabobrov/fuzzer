// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {


val clmeo: Addyi = Addyi()
val cuyun: Addyi.Yyrgv = Addyi.Yyrgv()
val mxylz: String = box()
val wzukz: Float = clmeo.uvbe
clmeo.uvbe = TODO()
}
// oldKlib.kt
// isKlib=true
// WITH_RUNTIME
import kotlin.test.*

fun  box(): String {
    var sum  = 0
    for (i in (1 until 5).reversed().reversed()) {
        sum = sum * 10 + i
    }
    assertEquals(1234, sum)

    var sumL = 0L
    for (i in (1L until 5L).reversed().reversed()) {
        sumL = sumL * 10 + i
    }
    assertEquals(1234, sumL)

    var sumC  = 0
    for (i in ('1' until '5').reversed().reversed()) {
        sumC = sumC * 10 + i.toInt() - '0'.toInt()
    }
    assertEquals(1234, sumC)

    return "OK"
}
class Addyi{
class Yyrgv
private var uvbe: Float  
get() = TODO()
set(value) = TODO()
}
// newKlib.kt
// isKlib=true
// WITH_RUNTIME
import kotlin.test.*

fun  box(): String {
    var sum  = 0
    for (i in (1 until 5).reversed().reversed()) {
        sum = sum * 10 + i
    }
    assertEquals(1234, sum)

    var sumL = 0L
    for (i in (1L until 5L).reversed().reversed()) {
        sumL = sumL * 10 + i
    }
    assertEquals(1234, sumL)

    var sumC  = 0
    for (i in ('1' until '5').reversed().reversed()) {
        sumC = sumC * 10 + i.toInt() - '0'.toInt()
    }
    assertEquals(1234, sumC)

    return "OK"
}
class Addyi{
open class Yyrgv
private var uvbe: Float  
get() = TODO()
set(value) = TODO()
}


Combined output:
====================
Cannot access 'uvbe': it is private in 'Addyi'
Cannot access 'uvbe': it is private in 'Addyi'