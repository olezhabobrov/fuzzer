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
val jfpye: Base = 
 object : Base{
override fun  base(): String { TODO() }
}
val mzshh: Lxvnd = jfpye
val homeq: Foo1 = 
 object : Foo1{
override fun  foo1() { TODO() }
override fun  base(): String { TODO() }
}
val ewqyp: Base = homeq
val wkmjt: Lxvnd = homeq
val wqwup: Foo2 = 
 object : Foo2{
override fun  foo2(sgrkg: String) { TODO() }
override fun  base(): String { TODO() }
}
val rzxfi: Base = wqwup
val axrxu: Lxvnd = wqwup
val ntzat: Bar = 
 object : Bar{
override fun  base(): String { TODO() }
override fun  foo1() { TODO() }
override fun  foo1(x: Int) { TODO() }
override fun  foo2(sgrkg: String) { TODO() }
}
val palnw: Foo1 = ntzat
val ksqjc: Base = ntzat
val cykaw: Lxvnd = ntzat
val atmal: Foo2 = ntzat
val brcrt: Lxvnd = 
 object : Lxvnd{

}


val wtyry: kotlin.String = rzxfi.base()
val nytgc: kotlin.Unit = homeq.foo1()
val lhryd: kotlin.Unit = atmal.foo2(wtyry)
val unuzt: kotlin.String = ntzat.base()
val ilgxl: kotlin.Unit = ntzat.foo1()
val ugqwi: kotlin.Unit = ntzat.foo1(39)





}
// oldKlib.kt
// isKlib=true
interface Base: Lxvnd{

    fun  base(): String { TODO() }

}

interface Foo1: Base {
    fun foo1()
}

interface Foo2: Base {
    fun  foo2(sgrkg: String)
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
interface Lxvnd{

}
tailrec internal fun  qyuwk(): Foo1 { TODO() }
// newKlib.kt
// isKlib=true
interface Base{


    fun  base(): String { TODO() }


}

interface Foo1: Base {
    fun foo1()
}

interface Foo2: Base {
    fun  foo2(sgrkg: String)
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
interface Lxvnd{

}
tailrec internal fun  qyuwk(): Foo1 { TODO() }


Combined output:
====================
====================
====================
====================
