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


val mopvy: Ykihe = Ykihe
val ybnjj: Dpbma = Dpbma
val frtwi: String = box()

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
object Ykihe
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
object Ykihe
object Dpbma{
sealed interface Uedrv
}


Combined output:
====================
====================
====================
====================
