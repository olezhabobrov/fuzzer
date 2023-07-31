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
     
         fun base(): String
     
     }
     
     interface Foo1: Base {
         fun foo1()
     }
     
old: interface Foo2: Base {
new: interface Foo2: Base{
new: 
         fun  foo2(sgrkg: String)
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
     interface Lxvnd{
     
     }
     tailrec internal fun  qyuwk(): Foo1 { TODO() }
     
new: 
new: 
// isKlib=false
fun main() {
val kdzfz: Base = 
 object : Base{
override fun  base(): String { TODO() }
}
val njhqc: Lxvnd = kdzfz
val tyjro: Foo1 = 
 object : Foo1{
override fun  foo1() { TODO() }
override fun  base(): String { TODO() }
}
val xwqkk: Base = tyjro
val azatm: Lxvnd = tyjro
val zttaz: Foo2 = 
 object : Foo2{
override fun  foo2(sgrkg: String) { TODO() }
override fun  base(): String { TODO() }
}
val qqfqm: Base = zttaz
val jlxgo: Lxvnd = zttaz
val jehnp: Bar = 
 object : Bar{
override fun  base(): String { TODO() }
override fun  foo1() { TODO() }
override fun  foo1(x: Int) { TODO() }
override fun  foo2(sgrkg: String) { TODO() }
}
val ffthf: Foo1 = jehnp
val dmiia: Base = jehnp
val mtrib: Lxvnd = jehnp
val lfdxy: Foo2 = jehnp
val essxg: Lxvnd = 
 object : Lxvnd{

}


val gkdwb: kotlin.String = zttaz.base()
val hsale: kotlin.Unit = ffthf.foo1()
val sejlg: kotlin.Unit = zttaz.foo2(gkdwb)
val ywhmj: kotlin.String = jehnp.base()
val dpavk: kotlin.Unit = jehnp.foo1()
val zldsw: kotlin.Unit = jehnp.foo1(-62)





}
Combined output:
====================
====================
====================
====================
