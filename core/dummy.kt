import java.util.*
import kotlin.*




val abcq1: HashSet<Double> = TODO()

fun  abcq(a: HashSet<Double>){}

// DONT_TARGET_EXACT_BACKEND: WASM
// WASM_MUTE_REASON: STDLIB_COLLECTIONS
// KJS_WITH_FULL_RUNTIME
// WITH_RUNTIME

fun box(): String {
    val m = hashMapOf<String, String?>()
    m.put("b", null)
    val oldValue = m.getOrPut(box(), { "Foo" })
    return if (oldValue == "Foo") "OK" else "fail: $oldValue"
}
