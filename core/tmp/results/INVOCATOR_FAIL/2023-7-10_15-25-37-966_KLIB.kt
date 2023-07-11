// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {


val iidws: Delegate = Delegate()
val fbahy: Delegate2 = Delegate2()
val bovjg: A = A()
val kvsst: Zeqwf = Zeqwf
val mqcgm: A.Companion = A.Companion
val chbuq: A.Companion.Xxewq = A.Companion.Xxewq()
val vmevp: String = box()
val eswqx: String = bovjg.f()
val nfjtv: String = bovjg.g()
val rgjdw: Unit = bovjg.set2()
val cskab: String = bovjg.get2()
val ismul: Unit = bovjg.set3()
val zbdcc: String = bovjg.get3()
val gzzsp: Short = mqcgm.kxzkb()
val rgsee: String = fbahy.value
fbahy.value = TODO()
}
// oldKlib.kt
// isKlib=true
// DONT_TARGET_EXACT_BACKEND: WASM
import kotlin.reflect.KProperty

class Delegate {
    operator fun getValue(t: Any?, p: KProperty<*>): String = "OK"
 
}

class Delegate2 {
    var value: String = "NOT OK"

    operator fun getValue(t: Any?, p: KProperty<*>): String = value

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        this.value = value
    }
}

open class A  {

    companion object {
        val s: String by Delegate()
        var s2: String by Delegate2()
        var s3: String by Delegate2()
    class Xxewq
fun  kxzkb(): Short? { TODO() }
}

    fun f() = s
    inline fun g() = s

    fun set2() {
        s2 = "OK"
    }
    fun get2() = s2

    inline fun set3() {
        s3 = "OK"
    }
    inline fun get3() = s3

interface Wrfkm
}

fun box(): String {
    val a = A()
    if (a.f() != "OK") return "FAIL0"
    if (a.g() != "OK") return "FAIL0"
    a.set2()
    if (a.get2() != "OK") return "FAIL0"
    a.set3()
    return a.get3()
}
object Zeqwf
// newKlib.kt
// isKlib=true
// DONT_TARGET_EXACT_BACKEND: WASM
import kotlin.reflect.KProperty

class Delegate {
    operator fun getValue(t: Any?, p: KProperty<*>): String = "OK"
 
}

class Delegate2 {
    var value: String = "NOT OK"

    operator fun getValue(t: Any?, p: KProperty<*>): String = value

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        this.value = value
    }
}

open class A  {

    companion object {
        val s: String by Delegate()
        var s2: String by Delegate2()
        var s3: String by Delegate2()
    class Xxewq
fun  kxzkb(): Short? { TODO() }
}

    fun f() = s
    inline fun g() = s

    fun set2() {
        s2 = "OK"
    }
    fun get2() = s2

    inline fun set3() {
        s3 = "OK"
    }
    inline fun get3() = s3

interface Wrfkm
internal var dffx: Delegate  = Delegate()
get() = Delegate()
private set
}

fun box(): String {
    val a = A()
    if (a.f() != "OK") return "FAIL0"
    if (a.g() != "OK") return "FAIL0"
    a.set2()
    if (a.get2() != "OK") return "FAIL0"
    a.set3()
    return a.get3()
}
object Zeqwf


Combined output:
====================
Type mismatch: inferred type is Short? but Short was expected