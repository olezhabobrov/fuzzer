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
val igutr: Base = 
 object : Base{
override fun  base(): String { TODO() }
}

val egvpv: Foo1 = 
 object : Foo1{
override fun  foo1() { TODO() }
override fun  base(): String { TODO() }
}
val mlsuw: Base = egvpv
val ygwqd: Foo2 = 
 object : Foo2{
override fun  foo2() { TODO() }
override fun  base(): String { TODO() }
}
val czbku: Base = ygwqd
val bpcqg: Bar = 
 object : Bar{
override fun  base(): String { TODO() }
override fun  foo1() { TODO() }
override fun  foo1(x: Int) { TODO() }
override fun  foo2() { TODO() }
}
val dstfg: Foo1 = bpcqg
val vebxt: Base = bpcqg
val jwtiu: Foo2 = bpcqg
val bjygu: kotlin.String = pxbrg()
val ylbwp: kotlin.String = mlsuw.base()
val ejnps: kotlin.Unit = dstfg.foo1()
val sucyn: kotlin.Unit = bpcqg.foo2()
val xsmsu: kotlin.String = bpcqg.base()
val npynw: kotlin.Unit = bpcqg.foo1()
val laetz: kotlin.Unit = bpcqg.foo1(93)




}
// oldKlib.kt
// isKlib=true
interface Base {
    fun base(): String
}

interface Foo1: Base {
    fun  foo1() { TODO() }
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
tailrec public fun  pxbrg(): String { TODO() }
// newKlib.kt
// isKlib=true
interface Base {
    fun base(): String
}

interface Foo1: Base, Foo2{

    fun  foo1() { TODO() }

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
tailrec public fun  pxbrg(): String { TODO() }


Combined output:
====================
====================
====================
====================
Abstract function 'foo2' is not implemented in non-abstract anonymous object