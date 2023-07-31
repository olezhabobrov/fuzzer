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
         fun foo1()
     }
     
     interface Foo2{
     
         fun foo2()
     
     }
     
     interface Bar: Foo1{
     
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
     
new: 
new: 
// isKlib=false
fun main() {
val ldabc: Base = 
 object : Base{
override fun  base(): String { TODO() }
}

val clvza: Foo1 = 
 object : Foo1{
override fun  foo1() { TODO() }
override fun  base(): String { TODO() }
}
val jhuse: Base = clvza
val yqgyd: Foo2 = 
 object : Foo2{
override fun  foo2() { TODO() }
}

val qtkxz: Bar = 
 object : Bar{
override fun  base(): String { TODO() }
override fun  foo1() { TODO() }
override fun  foo1(x: Int) { TODO() }
}
val nwcbc: Foo1 = qtkxz
val bpfjv: Base = qtkxz
val ebjfq: kotlin.String = bpfjv.base()
val cveoi: kotlin.Unit = qtkxz.foo1()
val fgxsi: kotlin.Unit = yqgyd.foo2()
val mdwyr: kotlin.String = qtkxz.base()
val yhuun: kotlin.Unit = qtkxz.foo1()
val wqqdf: kotlin.Unit = qtkxz.foo1(-16)




}
Combined output:
====================
====================
====================
====================
