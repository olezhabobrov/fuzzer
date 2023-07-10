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


val xlmlo: Qzkng = Qzkng
logged("nicnw", -24)
val pcxvp: String = box()
val tlxhv: StringBuilder = log
}
// oldKlib.kt
// isKlib=true
// DONT_TARGET_EXACT_BACKEND: WASM
// WASM_MUTE_REASON: STDLIB_STRING_BUILDER
// KJS_WITH_FULL_RUNTIME
// WITH_RUNTIME
import kotlin.test.*

val log = StringBuilder()

fun logged(message: String, value: Int) =
    value.also { log.append(message) }

fun box(): String {
    var sum  = 0
    for (i in logged("start;", 1)..logged("end;", 8) step logged("step;", 2)) {
        sum = sum * 10 + i
    }

    assertEquals(1357, sum)

    assertEquals("start;end;step;", log.toString())

    return "OK"
}
object Qzkng{
interface Xtown
}
// newKlib.kt
// isKlib=true
// DONT_TARGET_EXACT_BACKEND: WASM
// WASM_MUTE_REASON: STDLIB_STRING_BUILDER
// KJS_WITH_FULL_RUNTIME
// WITH_RUNTIME
import kotlin.test.*

val log = StringBuilder()

fun logged(message: String, value: Int) =
    value.also { log.append(message) }

fun box(): String {
    var sum  = 0
    for (i in logged("start;", 1)..logged("end;", 8) step logged("step;", 2)) {
        sum = sum * 10 + i
    }

    assertEquals(1357, sum)

    assertEquals("start;end;step;", log.toString())

    return "OK"
}
object Qzkng{
abstract interface Xtown
}


Combined output:
====================
====================
====================
====================
