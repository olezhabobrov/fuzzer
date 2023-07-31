// INCOMPATIBLE_LINKING
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]
// result:[-Xinclude=main.klib, -l, lib.klib, -Xpartial-linkage-loglevel=error]
// result:[-p, library, -o, lib.klib, projectTmp/newKlib.kt]
// result:[-Xinclude=main.klib, -l, lib.klib, -Xpartial-linkage-loglevel=error]

// 
// klib:
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
     interface Lxvnd{
     
     }
     tailrec internal fun  qyuwk(): Foo1 { TODO() }
     
new: 
new: 
// isKlib=false
fun main() {
val ghrux: Base = 
 object : Base{
override fun  base(): String { TODO() }
}
val zsjjo: Lxvnd = ghrux
val zcqlc: Foo1 = 
 object : Foo1{
override fun  foo1() { TODO() }
override fun  base(): String { TODO() }
}
val subks: Base = zcqlc
val gzbvk: Lxvnd = zcqlc
val lywlw: Foo2 = 
 object : Foo2{
override fun  foo2(sgrkg: String) { TODO() }
override fun  base(): String { TODO() }
}
val pxvhy: Base = lywlw
val ogdiu: Lxvnd = lywlw
val eqwti: Bar = 
 object : Bar{
override fun  base(): String { TODO() }
override fun  foo1() { TODO() }
override fun  foo1(x: Int) { TODO() }
override fun  foo2(sgrkg: String) { TODO() }
}
val dgkba: Foo1 = eqwti
val jzsgb: Base = eqwti
val ibdki: Lxvnd = eqwti
val ygldc: Foo2 = eqwti
val vsdjy: Lxvnd = 
 object : Lxvnd{

}


val gnats: kotlin.String = zcqlc.base()
val bkare: kotlin.Unit = zcqlc.foo1()
val rdteo: kotlin.Unit = ygldc.foo2(gnats)
val excyq: kotlin.String = eqwti.base()
val tdsbr: kotlin.Unit = eqwti.foo1()
val ynysh: kotlin.Unit = eqwti.foo1(-77)





}
Combined output:
====================
====================
====================
====================
