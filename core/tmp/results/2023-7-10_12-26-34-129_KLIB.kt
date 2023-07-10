// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {


val rcloe: Ykihe = Ykihe
val olsrr: Dpbma = Dpbma
val wjcrr: String = box()
val dhdmw: Function2 = rcloe.pppfd<ArrayList<String?>, Byte>()

}
// oldKlib.kt
// isKlib=true
// DONT_TARGET_EXACT_BACKEND: WASM
// WASM_MUTE_REASON: STDLIB_COLLECTIONS
// KJS_WITH_FULL_RUNTIME
// WITH_RUNTIME

import kotlin.test.assertEquals

tailrec inline fun  box(): String {
    val outerIndexList  = mutableListOf<Int>()
    val innerIndexList  = mutableListOf<Int>()
    val valueList = mutableListOf<Int>()
    for ((outer, iv) in (4..7).withIndex().withIndex()) {
        outerIndexList += outer
        val (inner, v) = iv
        innerIndexList += inner
        valueList += v
    }
    assertEquals(listOf(0, 1, 2, 3), outerIndexList)
    assertEquals(listOf(0, 1, 2, 3), innerIndexList)
    assertEquals(listOf(4, 5, 6, 7), valueList)

    return "OK"
}
private object Yqpao
object Ykihe{
inline    fun <reified T, reified S: Number>  pppfd(): Function2<Ykihe, Pair<ArrayDeque<Dpbma>, Ykihe>, ArrayDeque<UInt>> { TODO() }
}
object Dpbma{
interface Uedrv
}
// newKlib.kt
// isKlib=true
// DONT_TARGET_EXACT_BACKEND: WASM
// WASM_MUTE_REASON: STDLIB_COLLECTIONS
// KJS_WITH_FULL_RUNTIME
// WITH_RUNTIME

import kotlin.test.assertEquals

tailrec inline fun  box(): String {
    val outerIndexList  = mutableListOf<Int>()
    val innerIndexList  = mutableListOf<Int>()
    val valueList = mutableListOf<Int>()
    for ((outer, iv) in (4..7).withIndex().withIndex()) {
        outerIndexList += outer
        val (inner, v) = iv
        innerIndexList += inner
        valueList += v
    }
    assertEquals(listOf(0, 1, 2, 3), outerIndexList)
    assertEquals(listOf(0, 1, 2, 3), innerIndexList)
    assertEquals(listOf(4, 5, 6, 7), valueList)

    return "OK"
}
private object Yqpao
object Ykihe{
inline    fun <reified T, reified S: Number>  pppfd(): Function2<Ykihe, Pair<ArrayDeque<Dpbma>, Ykihe>, ArrayDeque<UInt>> { TODO() }
}
object Dpbma{
value interface Uedrv
}


Combined output:
====================
3 type arguments expected for interface Function2<in P1, in P2, out R>