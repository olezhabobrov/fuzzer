// COMPATIBLE_NOT_LINKING
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
old: interface Lbspg{
new: interface Lbspg: Bar{
     
new: 
new: 
     }
     
new: 
new: 
// isKlib=false
fun main() {
val xafzy: Base = 
 object : Base{
override fun  base(): String { TODO() }
}

val bihoe: Foo1 = 
 object : Foo1{
override fun  foo1() { TODO() }
}

val glaoo: Foo2 = 
 object : Foo2{
override fun  foo2() { TODO() }
override fun  base(): String { TODO() }
}
val gvwgd: Base = glaoo
val bzqyz: Bar = 
 object : Bar{
override fun  base(): String { TODO() }
override fun  foo1() { TODO() }
override fun  foo1(x: Int) { TODO() }
override fun  foo2() { TODO() }
}
val apedx: Foo1 = bzqyz
val zaopq: Foo2 = bzqyz
val pkxeb: Base = bzqyz
val hgsky: Pkwku = Pkwku

val swrbu: Lbspg = 
 object : Lbspg{

}

val xkmkn: kotlin.String = pkxeb.base()
val zlfek: kotlin.Unit = bihoe.foo1()
val dlvmb: kotlin.Unit = glaoo.foo2()
val cyxns: kotlin.String = bzqyz.base()
val efibs: kotlin.Unit = bzqyz.foo1()
val yojkx: kotlin.Unit = bzqyz.foo1(-66)






}
Combined output:
====================
====================
====================
====================
Abstract function 'foo1' is not implemented in non-abstract anonymous object
Abstract function 'foo2' is not implemented in non-abstract anonymous object