// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// 
// klib:
     // isKlib=true
     interface Base {
         fun base(): String
     }
     
     interface Foo1: Base {
         fun foo1()
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
old: class Phhzb{
new: interface Phhzb{
     
new: 
     protected var cbmb: Triple<Double, Float, UShort>  = TODO()
new: 
     }
     
new: 
new: 
// isKlib=false
fun main() {
val srybl: Base = 
 object : Base{
override fun  base(): String { TODO() }
}

val sspmz: Foo1 = 
 object : Foo1{
override fun  foo1() { TODO() }
override fun  base(): String { TODO() }
}
val veobp: Base = sspmz
val duupy: Foo2 = 
 object : Foo2{
override fun  foo2() { TODO() }
}

val tgkel: Bar = 
 object : Bar{
override fun  base(): String { TODO() }
override fun  foo1() { TODO() }
override fun  foo1(x: Int) { TODO() }
override fun  foo2() { TODO() }
}
val kyhhy: Foo1 = tgkel
val ehxqq: Base = tgkel
val fmejd: Foo2 = tgkel
val qqdxz: Phhzb = Phhzb()

val cotdk: kotlin.String = sspmz.base()
val arvbc: kotlin.Unit = tgkel.foo1()
val ozivr: kotlin.Unit = tgkel.foo2()
val wtpaw: kotlin.String = tgkel.base()
val xsuqw: kotlin.Unit = tgkel.foo1()
val isbub: kotlin.Unit = tgkel.foo1(44)




val zgpmz: kotlin.Triple<kotlin.Double, kotlin.Float, kotlin.UShort> = qqdxz.cbmb
qqdxz.cbmb = TODO()
}
Combined output:
====================
Cannot access 'cbmb': it is protected in 'Phhzb'
Cannot access 'cbmb': it is protected in 'Phhzb'