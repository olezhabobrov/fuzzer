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
     
     interface Foo1: Base {
         fun foo1()
     }
     
old: interface Foo2: Base {
new: interface Foo2: Base, Foo1{
new: 
         fun foo2()
new: 
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
     
new: 
new: 
// isKlib=false
fun main() {
val ouijk: Base = 
 object : Base{
override fun  base(): String { TODO() }
}

val eyebm: Foo1 = 
 object : Foo1{
override fun  foo1() { TODO() }
override fun  base(): String { TODO() }
}
val faixd: Base = eyebm
val gyrkv: Foo2 = 
 object : Foo2{
override fun  foo2() { TODO() }
override fun  base(): String { TODO() }
}
val twpuv: Base = gyrkv
val pghtr: Bar = 
 object : Bar{
override fun  base(): String { TODO() }
override fun  foo1() { TODO() }
override fun  foo1(x: Int) { TODO() }
override fun  foo2() { TODO() }
}
val gxmup: Foo1 = pghtr
val feqza: Base = pghtr
val qdiau: Foo2 = pghtr
val jvnzz: kotlin.String = feqza.base()
val kkdns: kotlin.Unit = eyebm.foo1()
val whtoi: kotlin.Unit = pghtr.foo2()
val awunb: kotlin.String = pghtr.base()
val ybgtx: kotlin.Unit = pghtr.foo1()
val hjnrc: kotlin.Unit = pghtr.foo1(-46)




}
Combined output:
====================
====================
====================
====================
Abstract function 'foo1' is not implemented in non-abstract anonymous object