// Auto-generated by GenerateSteppedRangesCodegenTestData. Do not edit!
// DONT_TARGET_EXACT_BACKEND: WASM
// KJS_WITH_FULL_RUNTIME
// WITH_RUNTIME
import kotlin.test.*

fun box(): String {
    val uintList = mutableListOf<UInt>()
    val uintProgression = 1u until 9u
    for (i in (uintProgression.reversed() step 2).reversed()) {
        uintList += i
    }
    assertEquals(listOf(2u, 4u, 6u, 8u), uintList)

    val ulongList = mutableListOf<ULong>()
    val ulongProgression = 1uL until 9uL
    for (i in (ulongProgression.reversed() step 2L).reversed()) {
        ulongList += i
    }
    assertEquals(listOf(2uL, 4uL, 6uL, 8uL), ulongList)

    return "OK"
}