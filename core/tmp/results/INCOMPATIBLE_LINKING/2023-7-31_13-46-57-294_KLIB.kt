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
old:     fun  base(): String { TODO() }
new:     fun  base(): String
     }
     
     interface Foo1: Base {
         fun  foo1() { TODO() }
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
     tailrec public fun  pxbrg(): String { TODO() }
     
new: 
new: 
// isKlib=false
fun main() {
val uizcb: Base = 
 object : Base{
override fun  base(): String { TODO() }
}

val jqpfj: Foo1 = 
 object : Foo1{
override fun  foo1() { TODO() }
override fun  base(): String { TODO() }
}
val onott: Base = jqpfj
val mhzgt: Foo2 = 
 object : Foo2{
override fun  foo2() { TODO() }
}

val seyni: Bar = 
 object : Bar{
override fun  base(): String { TODO() }
override fun  foo1() { TODO() }
override fun  foo1(x: Int) { TODO() }
override fun  foo2() { TODO() }
}
val urqbf: Foo1 = seyni
val xhvjq: Base = seyni
val hmlmr: Foo2 = seyni
val nwmat: kotlin.String = pxbrg()
val docea: kotlin.String = seyni.base()
val ouxmj: kotlin.Unit = seyni.foo1()
val heifn: kotlin.Unit = seyni.foo2()
val pessd: kotlin.String = seyni.base()
val dukfd: kotlin.Unit = seyni.foo1()
val nzcbt: kotlin.Unit = seyni.foo1(-48)




}
Combined output:
====================
====================
====================
====================
