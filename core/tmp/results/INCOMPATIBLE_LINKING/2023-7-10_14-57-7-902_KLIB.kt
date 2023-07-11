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


val tqphr: Outer = Outer()
val pmvcs: Qgsfd = Qgsfd()
val hiiuz: Outer.Nested = Outer.Nested()
val tzurk: String = box()
val nvixk: String = tqphr.test()
val qetvh: Any = hiiuz.foo()

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

}

    fun test() = Nested().foo().toString()
}

fun box() = Outer().test()
class Qgsfd
// newKlib.kt
// isKlib=true
// !LANGUAGE: +ProperVisibilityForCompanionObjectInstanceField

class Outer {
    private companion object {
        override fun toString(): String = "OK"
    }

    open class Nested  {

        fun foo(): Any = Outer.Companion
    private interface Avzju

}

    fun test() = Nested().foo().toString()
}

fun box() = Outer().test()
class Qgsfd


Combined output:
====================
====================
====================
====================
