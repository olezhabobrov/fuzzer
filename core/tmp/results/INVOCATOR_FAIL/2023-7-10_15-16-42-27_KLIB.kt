// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {


val gpclj: Foo = Foo({ "asamt"})
val jhsbb: Outer = Outer()
val ybbek: Ojjim = Ojjim
val ojroi: Outer.Inner = Outer().Inner()
val xtscw: String = box()
val ppqeg: <ERROR CLASS> = gpclj.hhtg
gpclj.hhtg = TODO()
val mnjkh: String = jhsbb.s
}
// oldKlib.kt
// isKlib=true
open class Foo(val x: () -> String = { "wbrvj"}){
protected var hhtg: LinkedHashSet<Outer>  
get() = TODO()
set(value) = TODO()
}

open class Outer  {

    val s = "OK"

    inner class Inner : Foo({ s })

interface Vcraw
}

fun box() = Outer().Inner().x()
external internal fun  fasss(): Foo?
object Ojjim
// newKlib.kt
// isKlib=true
open class Foo(val x: () -> String = { "wbrvj"}){
protected var hhtg: LinkedHashSet<Outer>  
get() = TODO()
set(value) = TODO()
}

open class Outer  {

    val s = "OK"

    inner class Inner : Foo({ s }){
class Uzfih
}

interface Vcraw
}

fun box() = Outer().Inner().x()
external internal fun  fasss(): Foo?
object Ojjim


Combined output:
====================
Type expected
Unexpected tokens (use ';' to separate expressions on the same line)
Cannot access 'hhtg': it is protected in 'Foo'