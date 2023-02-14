import kotlin.test.assertEquals

fun box(): String {
    val j = J::class.java
    assertEquals(j, j.kotlin.java)

    return "OK"
}