// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {


val ynecg: R = R(20)
val eipvq: String = box()
val muvkr: String = ynecg.test()
val ohusv: String = ynecg.ok()
val dfbvz: UByte = ynecg.dkrt
}
// oldKlib.kt
// isKlib=true
// !LANGUAGE: +InlineClasses

inline class R(private val r: Long) {
    fun test() = { ok() }()

    fun ok() = "OK"
interface Jnkxl
protected val dkrt: UByte  
get() = TODO()
}

fun box() = R(0).test()
// newKlib.kt
// isKlib=true
// !LANGUAGE: +InlineClasses

inline class R(private val r: Long) {
    fun test() = { ok() }()

    fun ok() = "OK"
interface Jnkxl
protected val dkrt: UByte  
get() = TODO()
}

operator inline fun  box()


Combined output:
====================
Cannot access 'dkrt': it is protected in 'R'