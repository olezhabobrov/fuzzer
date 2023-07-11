// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {


val hlcfv: Test = Test(0)
val dbpkw: String = box()
val dnpnr: Int = hlcfv.y
val cuxtz: Int = hlcfv.z
val idirr: Test = hlcfv.ekbd
}
// oldKlib.kt
// isKlib=true
//WITH_RUNTIME
import kotlin.test.assertEquals

data class Test  (val x: Int){

    val y = x + 1
    val z: Int
    init {
        z = y + 1
    }

internal val ekbd: Test  
get() = Test(74)
}

fun box(): String {
    val test  = Test(1)
    assertEquals(test.x, 1)
    assertEquals(test.y, 2)
    assertEquals(test.z, 3)

    return "OK"
}
// newKlib.kt
// isKlib=true
//WITH_RUNTIME
import kotlin.test.assertEquals

data class Test  (val x: Int){

    val y = x + 1
    val z: Int
    init {
        z = y + 1
    }

internal val ekbd: Test  
get() = Test(74)
}

fun box(): String {
    val test  = Test(1)
    assertEquals(test.x, 1)
    assertEquals(test.y, 2)
    assertEquals(test.z, 3)

    return "OK"
}
class Ltbwm


Combined output:
====================
Cannot access 'ekbd': it is internal in 'Test'