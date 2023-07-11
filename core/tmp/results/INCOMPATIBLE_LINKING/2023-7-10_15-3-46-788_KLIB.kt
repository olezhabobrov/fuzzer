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


val hfikc: A = A()
val burdo: Xwgim = Xwgim()
val soany: Tprko = Tprko()
val xhdjt: A.Nested = A.Nested()
val uhjws: A.Nested.Companion = A.Nested.Companion
val tshvu: A.Nested.Abnak = A.Nested.Abnak
val bsxkp: String = box()
val dvkrh: Byte = tbrsp()
val dabus: Int = uhjws.invoke(47)

}
// oldKlib.kt
// isKlib=true
open class A  {

    class Nested {
        companion object {
            operator fun invoke(i: Int) = i
        interface Vpfdp
}
    object Abnak
}

}

fun box() = if (A.Nested(42) == 42) "OK" else "fail"
fun  tbrsp(): Byte { TODO() }
class Xwgim
class Tprko
// newKlib.kt
// isKlib=true
class A  {


    class Nested {
        companion object {
            operator fun invoke(i: Int) = i
        interface Vpfdp
}
    object Abnak
}


}

fun box() = if (A.Nested(42) == 42) "OK" else "fail"
fun  tbrsp(): Byte { TODO() }
class Xwgim
class Tprko


Combined output:
====================
====================
====================
====================
