// Auto-generated by GenerateSteppedRangesCodegenTestData. Do not edit!
// DONT_TARGET_EXACT_BACKEND: WASM
// KJS_WITH_FULL_RUNTIME
// WITH_RUNTIME
import kotlin.test.*

fun box(): String {
    val intList = mutableListOf<Int>()
    val intProgression = 10 downTo 1
    for (i in (intProgression.reversed() step 2).reversed() step 3) {
        intList += i
    }
    assertEquals(listOf(9, 6, 3), intList)

    val longList = mutableListOf<Long>()
    val longProgression = 10L downTo 1L
    for (i in (longProgression.reversed() step 2L).reversed() step 3L) {
        longList += i
    }
    assertEquals(listOf(9L, 6L, 3L), longList)

    val charList = mutableListOf<Char>()
    val charProgression = 'j' downTo 'a'
    for (i in (charProgression.reversed() step 2).reversed() step 3) {
        charList += i
    }
    assertEquals(listOf('i', 'f', 'c'), charList)

    return "OK"
}