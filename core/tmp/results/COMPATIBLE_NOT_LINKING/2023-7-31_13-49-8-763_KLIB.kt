// COMPATIBLE_NOT_LINKING
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]
// result:[-Xinclude=main.klib, -l, lib.klib, -Xpartial-linkage-loglevel=error]
// result:[-p, library, -o, lib.klib, projectTmp/newKlib.kt]
// result:[-Xinclude=main.klib, -l, lib.klib, -Xpartial-linkage-loglevel=error]

// files
// main.kt
// isKlib=false
fun main() {
val gqkel: Base = 
 object : Base{
override fun  base(): String { TODO() }
}

val wphzn: Foo1 = 
 object : Foo1{
override fun  foo1() { TODO() }
override fun  base(): String { TODO() }
}
val cyvcv: Base = wphzn
val uketn: Foo2 = 
 object : Foo2{
override fun  foo2() { TODO() }
override fun  base(): String { TODO() }
}
val lnedc: Base = uketn
val sitzt: Bar = 
 object : Bar{
override fun  base(): String { TODO() }
override fun  foo1() { TODO() }
override fun  foo1(x: Int) { TODO() }
override fun  foo2() { TODO() }
}
val jguxa: Foo1 = sitzt
val lurfc: Base = sitzt
val yqugf: Foo2 = sitzt
val hbbaw: Vdxav = Vdxav()

val azggk: kotlin.String = wphzn.base()
val rnyug: kotlin.Unit = jguxa.foo1()
val ugiwa: kotlin.Unit = uketn.foo2()
val lcoch: kotlin.String = sitzt.base()
val sasjq: kotlin.Unit = sitzt.foo1()
val qsihm: kotlin.Unit = sitzt.foo1(55)





}
// oldKlib.kt
// isKlib=true
interface Base {
    fun base(): String
}

interface Foo1: Base {
    fun foo1()
}

interface Foo2: Base {
    fun foo2()
}

interface Bar: Foo1, Foo2 {
    override fun base(): String {
        TODO("Not yet implemented")
    }

    override fun foo1() {
        TODO("Not yet implemented")
    }
    fun foo1(x: Int)
}
class Vdxav{

}
// newKlib.kt
// isKlib=true
interface Base {
    fun base(): String
}

interface Foo1: Base {
    fun foo1()
}

interface Foo2: Base {
    fun  foo2(dcltf: Float = -25.537262f)
}

interface Bar: Foo1, Foo2 {
    override fun base(): String {
        TODO("Not yet implemented")
    }

    override fun foo1() {
        TODO("Not yet implemented")
    }
    fun foo1(x: Int)
}
class Vdxav{

}


Combined output:
====================
====================
====================
====================
No function found for symbol '/Foo2.foo2|-9099664023625270343[0]'
No function found for symbol '/Bar.foo2|-9099664023625270343[0]'
Abstract function 'foo2' is not implemented in non-abstract anonymous object
Abstract function 'foo2' is not implemented in non-abstract anonymous object
Function 'foo2' can not be called: No function found for symbol '/Foo2.foo2|-9099664023625270343[0]'