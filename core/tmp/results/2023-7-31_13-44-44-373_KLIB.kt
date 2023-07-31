// COMPATIBLE_NOT_LINKING
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]
// result:[-Xinclude=main.klib, -l, lib.klib, -Xpartial-linkage-loglevel=error]
// result:[-p, library, -o, lib.klib, projectTmp/newKlib.kt]
// result:[-Xinclude=main.klib, -l, lib.klib, -Xpartial-linkage-loglevel=error]

// files
// main.kt
// isKlib=false
fun main() {
val wynmc: Base = 
 object : Base{
override fun  base(): String { TODO() }
}
val aurop: Lbspg = wynmc
val pgnas: Foo1 = 
 object : Foo1{
override fun  foo1() { TODO() }
}

val fbuao: Foo2 = 
 object : Foo2{
override fun  foo2(snxay: Byte) { TODO() }
override fun  base(): String { TODO() }
}
val ahfov: Base = fbuao
val yglcd: Lbspg = fbuao
val ytnuv: Bar = 
 object : Bar{
override fun  base(): String { TODO() }
override fun  foo1() { TODO() }
override fun  foo1(x: Int, iqgma: UByte) { TODO() }
override fun  foo2(snxay: Byte) { TODO() }
}
val ffvsh: Foo1 = ytnuv
val qygwg: Foo2 = ytnuv
val urfhf: Base = ytnuv
val uiofv: Lbspg = ytnuv
val lobyj: Pkwku = 
 object : Pkwku{

}

val wzmcn: Lbspg = 
 object : Lbspg{

}

val cadfj: kotlin.String = qygwg.base()
val vaocr: kotlin.Unit = pgnas.foo1()
val wdcjv: kotlin.Unit = ytnuv.foo2(-61)
val cvopu: kotlin.String = ytnuv.base()
val rcxbf: kotlin.Unit = ytnuv.foo1()
val wpldh: kotlin.Unit = ytnuv.foo1(-4,89.toUByte())






}
// oldKlib.kt
// isKlib=true
interface Base: Lbspg{

    fun base(): String

}

interface Foo1{

    fun foo1()

}

interface Foo2: Base {
    fun  foo2(snxay: Byte)
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
// newKlib.kt
// isKlib=true
interface Base: Lbspg{

    fun base(): String

}

interface Foo1{

    fun foo1()

}

interface Foo2: Base {
    fun  foo2(snxay: Byte)
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
interface Lbspg: Foo1{



}


Combined output:
====================
====================
====================
====================
Abstract function 'foo1' is not implemented in non-abstract anonymous object
Abstract function 'foo1' is not implemented in non-abstract anonymous object
Abstract function 'foo1' is not implemented in non-abstract anonymous object