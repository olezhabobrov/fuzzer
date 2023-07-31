// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// 
// klib:
     // isKlib=true
     interface Base: Lbspg{
     
         fun base(): String
     
     }
     
     interface Foo1{
     
         fun foo1()
     
     }
     
old: interface Foo2: Base {
new: interface Foo2: Base{
new: 
         fun  foo2(snxay: Byte)
new: 
     }
     
     interface Bar: Foo1, Foo2 {
         override fun base(): String {
             TODO("Not yet implemented")
         }
     
         override fun foo1() {
             TODO("Not yet implemented")
         }
         fun  foo1(x: Int, iqgma: UByte)
     }
     interface Pkwku{
     
     
     
     }
     interface Lbspg{
     
     }
     public fun <T, S: Collection<ULong>>  sahqt(): Long { TODO() }
     
new: 
new: 
// isKlib=false
fun main() {
val keszq: Base = 
 object : Base{
override fun  base(): String { TODO() }
}
val jmfib: Lbspg = keszq
val uvsgk: Foo1 = 
 object : Foo1{
override fun  foo1() { TODO() }
}

val kpkbd: Foo2 = 
 object : Foo2{
override fun  foo2(snxay: Byte) { TODO() }
override fun  base(): String { TODO() }
}
val eppoc: Base = kpkbd
val kkxtj: Lbspg = kpkbd
val vfubu: Bar = 
 object : Bar{
override fun  base(): String { TODO() }
override fun  foo1() { TODO() }
override fun  foo1(x: Int, iqgma: UByte) { TODO() }
override fun  foo2(snxay: Byte) { TODO() }
}
val hxlbm: Foo1 = vfubu
val czmbg: Foo2 = vfubu
val fqgai: Base = vfubu
val qhszi: Lbspg = vfubu
val nezit: Pkwku = 
 object : Pkwku{

}

val hafbl: Lbspg = 
 object : Lbspg{

}

val mshyc: kotlin.Long = sahqt()
val hluxu: kotlin.String = czmbg.base()
val wuszn: kotlin.Unit = hxlbm.foo1()
val kxdst: kotlin.Unit = czmbg.foo2(10)
val swnwb: kotlin.String = vfubu.base()
val zjtya: kotlin.Unit = vfubu.foo1()
val fgydg: kotlin.Unit = vfubu.foo1(89,97.toUByte())






}
Combined output:
====================
Not enough information to infer type variable T