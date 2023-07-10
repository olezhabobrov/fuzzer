// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {


val lkryl: Outer = Outer()
val lzbkh: Qgsfd = Qgsfd()
val zhinp: Iiwxy = Iiwxy
val pruiz: Outer.Nested = Outer.Nested()
val lhkmq: String = box()
val tpioq: String = lkryl.test()
val beceb: Any = pruiz.foo()
val nzawk: Double = pruiz.lugaf()

}
// oldKlib.kt
// isKlib=true
// !LANGUAGE: +ProperVisibilityForCompanionObjectInstanceField

class Outer {
    private companion object {
        override fun toString(): String = "OK"
    }

    open class Nested  {

        fun foo(): Any = Outer.Companion
    interface Avzju

internal  fun  lugaf(): Double? { TODO() }
}

    fun test() = Nested().foo().toString()
}

fun box() = Outer().test()
class Qgsfd
object Iiwxy
// newKlib.kt
// isKlib=true
// !LANGUAGE: +ProperVisibilityForCompanionObjectInstanceField

class Outer {
    private companion object {
        override fun toString(): String = "OK"
    }

    open class Nested  {

        fun foo(): Any = Outer.Companion
    interface Avzju

internal  fun  lugaf(): Double? { TODO() }
}

    fun test() = Nested().foo().toString()
}

fun box() = Outer().test()
class Qgsfd
object Iiwxy{
inline suspend private  fun <reified T, S: KDeclarationContainer>  zqyxk(): Qgsfd { TODO() }
}


Combined output:
====================
Type mismatch: inferred type is Double? but Double was expected
Cannot access 'lugaf': it is internal in 'Nested'