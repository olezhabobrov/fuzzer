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
val fgxil: Base = 
 object : Base{
override fun  base(): String { TODO() }
}

val gepwm: Foo1 = 
 object : Foo1{
override fun  foo1() { TODO() }
}

val hdbjk: Foo2 = 
 object : Foo2{
override fun  foo2() { TODO() }
override fun  base(): String { TODO() }
}
val xqgso: Base = hdbjk
val sdnka: Bar = 
 object : Bar{
override fun  base(): String { TODO() }
override fun  foo1() { TODO() }
override fun  foo1(x: Int) { TODO() }
override fun  foo2() { TODO() }
}
val otxbn: Foo1 = sdnka
val qpqnu: Foo2 = sdnka
val srcqn: Base = sdnka
val wlrjj: Pkwku = Pkwku

val xczss: kotlin.String = hdbjk.base()
val racyo: kotlin.Unit = gepwm.foo1()
val uecgc: kotlin.Unit = sdnka.foo2()
val tymyt: kotlin.String = sdnka.base()
val lgnfs: kotlin.Unit = sdnka.foo1()
val wjzfv: kotlin.Unit = sdnka.foo1(-100)





}
// oldKlib.kt
// isKlib=true
interface Base {
    fun base(): String
}

interface Foo1{

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
object Pkwku{

}
// newKlib.kt
// isKlib=true
interface Base {
    fun base(): String
}

interface Foo1{

    fun foo1()

}

interface Foo2: Base {
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
object Pkwku{

}


Combined output:
====================
====================
====================
====================
