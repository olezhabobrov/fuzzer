// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {


val agfhm: Xbcsv = object: Xbcsv {
}
val qgnjt: Zwemb = object: Zwemb {
}
val tdull: Foo.Companion = Foo.Companion
val gsyen: Foo.Companion.Kjjzv = Foo.Companion.Kjjzv()
val nbrlp: Foo.Companion.Xjaaf = Foo.Companion.Xjaaf()
val tepgg: Foo.Companion.Kcpoc = Foo.Companion.Kcpoc()
val kpbok: String = box()
val kmxkg: Zwemb = yuocy()

}
// oldKlib.kt
// isKlib=true
class Foo private constructor(val param: String = "OK") {
    companion object {
        val s = Foo()
    open class Kjjzv
class Xjaaf
class Kcpoc
}
}

fun box(): String {
    Foo.s.param
    return "OK"
}
interface Xbcsv
interface Zwemb
suspend  fun  yuocy(): Zwemb { TODO() }
// newKlib.kt
// isKlib=true
class Foo private constructor(val param: String = "OK") {
    companion object {
        val s = Foo()
    open class Kjjzv
class Xjaaf
class Kcpoc
}
}

fun box(): String {
    Foo.s.param
    return "OK"
}
interface Xbcsv
interface Zwemb
suspend tailrec inline fun  yuocy(): Zwemb { TODO() }


Combined output:
====================
Suspend function 'yuocy' should be called only from a coroutine or another suspend function