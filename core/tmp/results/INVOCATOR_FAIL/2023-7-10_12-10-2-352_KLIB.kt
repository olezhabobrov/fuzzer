// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {


val zarpw: C = object: C {
}
val qluft: C.Nodhh = C.Nodhh
val qrlov: C.Nodhh.Ivyev = C.Nodhh.Ivyev
val zjpmq: Unit = checkNotEqual("xrsrx", "dysjm")
val thzby: Any = capture1({a: String -> {}})
val qzffr: Any = capture2({a: Array<String> -> {}})
val phgig: String = box()
val kyakd: Any = captureMemberFromOtherFile()
val hxqco: Any = captureExtensionFromOtherFile()
val yifxg: Unit = zarpw.member("wftcz")

}
// oldKlib.kt
// isKlib=true
// FILE: test.kt

fun checkNotEqual(x: Any, y: Any) {
    if (x == y || y == x) throw AssertionError("$x and $y should NOT be equal")
}

abstract class C  {

    fun member(vararg xs: String) {}
object Nodhh{
object Ivyev
}

}

fun C.extension(vararg xs: String) {}

fun capture1(fn: C.(String) -> Unit): Any = fn
fun capture2(fn: C.(Array<String>) -> Unit): Any = fn

fun box(): String {
    checkNotEqual(capture1(C::member), capture2(C::member))
    checkNotEqual(capture1(C::member), captureMemberFromOtherFile())

    checkNotEqual(capture1(C::extension), capture2(C::extension))
    checkNotEqual(capture1(C::extension), captureExtensionFromOtherFile())
    return "OK"
}

// FILE: fromOtherFile.kt

fun captureMemberFromOtherFile(): Any = capture2(C::member)
fun captureExtensionFromOtherFile(): Any = capture2(C::extension)

// newKlib.kt
// isKlib=true
// FILE: test.kt

fun checkNotEqual(x: Any, y: Any) {
    if (x == y || y == x) throw AssertionError("$x and $y should NOT be equal")
}

abstract class C  {

    fun member(vararg xs: String) {}
object Nodhh{
object Ivyev{
private final fun  yslzg(): Function2<Short, Function2<String, Long, MutableMap<ULong, ULong?>>, UInt> { TODO() }
}
}

}

fun C.extension(vararg xs: String) {}

fun capture1(fn: C.(String) -> Unit): Any = fn
fun capture2(fn: C.(Array<String>) -> Unit): Any = fn

fun box(): String {
    checkNotEqual(capture1(C::member), capture2(C::member))
    checkNotEqual(capture1(C::member), captureMemberFromOtherFile())

    checkNotEqual(capture1(C::extension), capture2(C::extension))
    checkNotEqual(capture1(C::extension), captureExtensionFromOtherFile())
    return "OK"
}

// FILE: fromOtherFile.kt

fun captureMemberFromOtherFile(): Any = capture2(C::member)
fun captureExtensionFromOtherFile(): Any = capture2(C::extension)



Combined output:
====================
This type has a constructor, and thus must be initialized here