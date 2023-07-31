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
val zwoaq: Base = 
 object : Base{
override fun  base(): String { TODO() }
}

val xapgt: Foo1 = 
 object : Foo1{
override fun  foo1() { TODO() }
override fun  base(): String { TODO() }
}
val cimlq: Base = xapgt
val svcfs: Foo2 = 
 object : Foo2{
override fun  foo2() { TODO() }
}

val lreim: Bar = 
 object : Bar{
override fun  base(): String { TODO() }
override fun  foo1() { TODO() }
override fun  foo1(x: Int) { TODO() }
override fun  foo2() { TODO() }
}
val payxv: Foo1 = lreim
val nvygc: Base = lreim
val oerld: Foo2 = lreim
val wvrwn: Ysbda = 
 object : Ysbda{

}

val odoli: kotlin.String = pxbrg()
val tcqnv: kotlin.String = cimlq.base()
val pdqim: kotlin.Unit = payxv.foo1()
val fluas: kotlin.Unit = svcfs.foo2()
val qvkyo: kotlin.String = lreim.base()
val rfwme: kotlin.Unit = lreim.foo1()
val miefo: kotlin.Unit = lreim.foo1(91)





}
// oldKlib.kt
// isKlib=true
interface Base {
    fun  base(): String { TODO() }
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
    fun foo1(x: Int)
}
tailrec public fun  pxbrg(): String { TODO() }
interface Ysbda{

}
// newKlib.kt
// isKlib=true
interface Base {
    fun  base(): String { TODO() }
}

interface Foo1: Base {
    fun  foo1() { TODO() }
}

interface Foo2{

    fun foo2()

}

interface Bar: Foo1, Foo2{

    override fun base(): String {
        TODO("Not yet implemented")
    }

    override fun foo1() {
        TODO("Not yet implemented")
    }
    fun foo1(x: Int)

}
tailrec public fun  pxbrg(): String { TODO() }
interface Ysbda{

}


Combined output:
====================
====================
====================
====================
