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


val rkcii: A = A()
val lthlx: A.Nested = A.Nested()
val xtvad: A.Nested.Companion = A.Nested.Companion
val vsfoq: A.Nested.Abnak = A.Nested.Abnak
val nqmyy: String = box()
val hyjuo: Int = xtvad.invoke(61)

}
// oldKlib.kt
// isKlib=true
open class A  {

    class Nested {
        companion object {
            operator fun invoke(i: Int) = i
        }
    object Abnak
}

}

fun box() = if (A.Nested(42) == 42) "OK" else "fail"

// newKlib.kt
// isKlib=true
class A  {


    class Nested {
        companion object {
            operator fun invoke(i: Int) = i
        }
    object Abnak
}


}

fun box() = if (A.Nested(42) == 42) "OK" else "fail"



Combined output:
====================
====================
====================
====================
