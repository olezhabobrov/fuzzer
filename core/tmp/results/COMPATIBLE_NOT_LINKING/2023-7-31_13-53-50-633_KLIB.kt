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
     
old: interface Foo2{
new: interface Foo2: Base{
     
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
     object Vymmt{
     
     }
     
new: 
new: 
// isKlib=false
fun main() {
val tloof: Base = 
 object : Base{
override fun  base(): String { TODO() }
}

val xgsva: Foo1 = 
 object : Foo1{
override fun  foo1() { TODO() }
override fun  base(): String { TODO() }
}
val zfolp: Base = xgsva
val oajcs: Foo2 = 
 object : Foo2{
override fun  foo2() { TODO() }
}

val ggecw: Bar = 
 object : Bar{
override fun  base(): String { TODO() }
override fun  foo1() { TODO() }
override fun  foo1(x: Int) { TODO() }
override fun  foo2() { TODO() }
}
val kcuvn: Foo1 = ggecw
val anngg: Base = ggecw
val kgjiv: Foo2 = ggecw
val wknkh: Vymmt = Vymmt

val xxqvc: kotlin.String = kcuvn.base()
val urcdm: kotlin.Unit = kcuvn.foo1()
val jstum: kotlin.Unit = ggecw.foo2()
val hvsha: kotlin.String = ggecw.base()
val jluol: kotlin.Unit = ggecw.foo1()
val jwlhk: kotlin.Unit = ggecw.foo1(92)





}
Combined output:
====================
====================
====================
====================
Abstract function 'base' is not implemented in non-abstract anonymous object