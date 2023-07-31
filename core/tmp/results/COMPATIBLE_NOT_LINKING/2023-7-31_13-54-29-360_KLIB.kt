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
val bgile: Base = 
 object : Base{
override fun  base(): String { TODO() }
}

val dywvu: Foo1 = 
 object : Foo1{
override fun  foo1() { TODO() }
override fun  base(): String { TODO() }
}
val ybssa: Base = dywvu
val bedtb: Foo2 = 
 object : Foo2{
override fun  foo2() { TODO() }
}

val fezfs: Bar = 
 object : Bar{
override fun  base(): String { TODO() }
override fun  foo1() { TODO() }
override fun  foo1(x: Int) { TODO() }
override fun  foo2() { TODO() }
}
val gvrep: Foo1 = fezfs
val uslye: Base = fezfs
val woxim: Foo2 = fezfs
val hocmj: Vymmt = Vymmt

val becmu: kotlin.String = fezfs.base()
val eczlz: kotlin.Unit = dywvu.foo1()
val kkvpu: kotlin.Unit = fezfs.foo2()
val marwl: kotlin.String = fezfs.base()
val abefj: kotlin.Unit = fezfs.foo1()
val kcrdv: kotlin.Unit = fezfs.foo1(32)





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

}
// newKlib.kt
// isKlib=true
interface Base {
    fun base(): String
}

interface Foo1: Base {
    fun  foo1() { TODO() }
}

interface Foo2: Foo1{


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

}


Combined output:
====================
====================
====================
====================
Abstract function 'base' is not implemented in non-abstract anonymous object