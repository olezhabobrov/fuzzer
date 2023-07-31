// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {
val tdoor: Base = 
 object : Base{
override fun  base(): String { TODO() }
}

val xelig: Foo1 = 
 object : Foo1{
override fun  foo1() { TODO() }
override fun  base(): String { TODO() }
}
val tibkv: Base = xelig
val xhybw: Foo2 = 
 object : Foo2{
override fun  foo2() { TODO() }
}

val efxph: Bar = 
 object : Bar{
override fun  base(): String { TODO() }
override fun  foo1() { TODO() }
override fun  foo1(x: Int) { TODO() }
override fun  foo2() { TODO() }
}
val ljpva: Foo1 = efxph
val obdci: Base = efxph
val uwiyz: Foo2 = efxph
val hwans: Vymmt = Vymmt

val otpcq: Vymmt.Kvetj = Vymmt.
 object : Kvetj{

}

val bnzby: kotlin.String = tdoor.base()
val nkoqy: kotlin.Unit = ljpva.foo1()
val ldvil: kotlin.Unit = efxph.foo2()
val znocb: kotlin.String = efxph.base()
val dtueq: kotlin.Unit = efxph.foo1()
val dkktc: kotlin.Unit = efxph.foo1(65)






}
// oldKlib.kt
// isKlib=true
interface Base {
    fun base(): String
}

interface Foo1: Base {
    fun  foo1() { TODO() }
}

interface Foo2{

    fun foo2()

}

interface Bar: Foo1, Foo2 {
    override fun base(): String {
        TODO("Not yet implemented")
    }

    override fun foo1() {
        TODO("Not yet implemented")
    }
    fun  foo1(x: Int) { TODO() }
}
object Vymmt{

interface Kvetj{

}
}
// newKlib.kt
// isKlib=true
interface Base {
    fun base(): String
}

interface Foo1{

    fun  foo1() { TODO() }

}

interface Foo2{

    fun foo2()

}

interface Bar: Foo1, Foo2 {
    override fun base(): String {
        TODO("Not yet implemented")
    }

    override fun foo1() {
        TODO("Not yet implemented")
    }
    fun  foo1(x: Int) { TODO() }
}
object Vymmt{

interface Kvetj{

}
}


Combined output:
====================
The expression cannot be a selector (occur after a dot)
Type mismatch: inferred type is `<no name provided>` but Vymmt.Kvetj was expected
Unresolved reference: Kvetj