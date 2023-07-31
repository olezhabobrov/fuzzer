// INCOMPATIBLE_LINKING
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]
// result:[-Xinclude=main.klib, -l, lib.klib, -Xpartial-linkage-loglevel=error]
// result:[-p, library, -o, lib.klib, projectTmp/newKlib.kt]
// result:[-Xinclude=main.klib, -l, lib.klib, -Xpartial-linkage-loglevel=error]

// 
// klib:
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
     
old: interface Bar: Foo1, Foo2 {
new: interface Bar: Foo1, Foo2{
new: 
         override fun base(): String {
             TODO("Not yet implemented")
         }
     
         override fun foo1() {
             TODO("Not yet implemented")
         }
         fun foo1(x: Int)
new: 
     }
     tailrec public fun  pxbrg(): String { TODO() }
     
new: 
new: 
// isKlib=false
fun main() {
val mbite: Base = 
 object : Base{
override fun  base(): String { TODO() }
}

val vhcvj: Foo1 = 
 object : Foo1{
override fun  foo1() { TODO() }
override fun  base(): String { TODO() }
}
val iudga: Base = vhcvj
val kmbwn: Foo2 = 
 object : Foo2{
override fun  foo2() { TODO() }
override fun  base(): String { TODO() }
}
val rcewk: Base = kmbwn
val ifdct: Bar = 
 object : Bar{
override fun  base(): String { TODO() }
override fun  foo1() { TODO() }
override fun  foo1(x: Int) { TODO() }
override fun  foo2() { TODO() }
}
val qtijv: Foo1 = ifdct
val agoxl: Base = ifdct
val ugtct: Foo2 = ifdct
val wwwpp: kotlin.String = pxbrg()
val sohqv: kotlin.String = vhcvj.base()
val tiszc: kotlin.Unit = ifdct.foo1()
val cljon: kotlin.Unit = ugtct.foo2()
val fwaje: kotlin.String = ifdct.base()
val zntef: kotlin.Unit = ifdct.foo1()
val rafux: kotlin.Unit = ifdct.foo1(-74)




}
Combined output:
====================
====================
====================
====================
