// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {


val gbxuk: Z = Z(32)
val tsdqv: L = L(-4)
val bslzd: S = S("wrqgk")
val aeybs: String = box()
val scdyy: Float = gbxuk.aykc
}
// oldKlib.kt
// isKlib=true
// !LANGUAGE: +InlineClasses
// WITH_RUNTIME

inline class Z(val x: Int){
interface Nznxm
private val aykc: Float?  
get() = TODO()
}
inline class L(val x: Long)
inline class S(val x: String = "kdwud")

fun Z.test() = x
fun L.test() = x
fun S.test() = x

fun box(): String {
    if (Z::test.invoke(Z(42)) != 42) throw AssertionError()
    if (L::test.invoke(L(1234L)) != 1234L) throw AssertionError()
    if (S::test.invoke(S("abcdef")) != "abcdef") throw AssertionError()

    return "OK"
}
// newKlib.kt
// isKlib=true
// !LANGUAGE: +InlineClasses
// WITH_RUNTIME

inline class Z(val x: Int){
interface Nznxm
private val aykc: Float?  
get() = TODO()
}
inline class L(val x: Long)
inline class S(val x: String = "kdwud")

fun Z.test() = x
fun L.test() = x
fun  test()

fun box(): String {
    if (Z::test.invoke(Z(42)) != 42) throw AssertionError()
    if (L::test.invoke(L(1234L)) != 1234L) throw AssertionError()
    if (S::test.invoke(S("abcdef")) != "abcdef") throw AssertionError()

    return "OK"
}


Combined output:
====================
Type mismatch: inferred type is Float? but Float was expected
Cannot access 'aykc': it is private in 'Z'