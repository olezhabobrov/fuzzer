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
old: class Vdxav: Base{
new: open class Vdxav: Base{
     
     
new: 
     open override fun  base(): String { TODO() }
     
     protected var ikyl: Base?  
     get() = object: Base {
     override fun base(): String { TODO() }
     }
     set(value) = TODO()
new: 
     }
     
new: 
new: 
// isKlib=false
fun main() {
val bttbe: Base = 
 object : Base{
override fun  base(): String { TODO() }
}

val wripn: Foo1 = 
 object : Foo1{
override fun  foo1() { TODO() }
override fun  base(): String { TODO() }
}
val whzno: Base = wripn
val hrrsf: Foo2 = 
 object : Foo2{
override fun  foo2() { TODO() }
override fun  base(): String { TODO() }
}
val ycodb: Base = hrrsf
val bqyob: Bar = 
 object : Bar{
override fun  base(): String { TODO() }
override fun  foo1() { TODO() }
override fun  foo1(x: Int) { TODO() }
override fun  foo2() { TODO() }
}
val cviec: Foo1 = bqyob
val fvlvb: Base = bqyob
val tlsyq: Foo2 = bqyob
val mnxat: Vdxav = Vdxav()
val rjziv: Base = mnxat

val mecks: kotlin.String = mnxat.base()
val ojqqv: kotlin.Unit = cviec.foo1()
val tnxis: kotlin.Unit = tlsyq.foo2()
val uyrsu: kotlin.String = bqyob.base()
val enhqm: kotlin.Unit = bqyob.foo1()
val uqfld: kotlin.Unit = bqyob.foo1(-21)
val dxjsw: kotlin.String = mnxat.base()
val sbhbd: kotlin.String = .base()




val nbjxy: Base? = mnxat.ikyl
mnxat.ikyl = TODO()

}
Combined output:
====================
Expecting an expression
Unexpected tokens (use ';' to separate expressions on the same line)
Cannot access 'ikyl': it is protected in 'Vdxav'
Cannot access 'ikyl': it is protected in 'Vdxav'