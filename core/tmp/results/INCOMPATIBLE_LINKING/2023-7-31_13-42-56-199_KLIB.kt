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
     
     interface Foo1{
     
         fun foo1()
     
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
     object Pkwku{
     
     }
     interface Lbspg{
     
     }
     
new: 
new: 
// isKlib=false
fun main() {
val bxvoj: Base = 
 object : Base{
override fun  base(): String { TODO() }
}

val fzsvt: Foo1 = 
 object : Foo1{
override fun  foo1() { TODO() }
}

val sbuhr: Foo2 = 
 object : Foo2{
override fun  foo2() { TODO() }
override fun  base(): String { TODO() }
}
val gwnbn: Base = sbuhr
val kicqp: Bar = 
 object : Bar{
override fun  base(): String { TODO() }
override fun  foo1() { TODO() }
override fun  foo1(x: Int) { TODO() }
override fun  foo2() { TODO() }
}
val utnly: Foo1 = kicqp
val zpghi: Foo2 = kicqp
val jjfdh: Base = kicqp
val gyoka: Pkwku = Pkwku

val cojcv: Lbspg = 
 object : Lbspg{

}

val qnjgm: kotlin.String = jjfdh.base()
val wdqqt: kotlin.Unit = fzsvt.foo1()
val adjlx: kotlin.Unit = sbuhr.foo2()
val qnvmq: kotlin.String = kicqp.base()
val mmjmc: kotlin.Unit = kicqp.foo1()
val gvjhp: kotlin.Unit = kicqp.foo1(23)






}
Combined output:
====================
====================
====================
====================
