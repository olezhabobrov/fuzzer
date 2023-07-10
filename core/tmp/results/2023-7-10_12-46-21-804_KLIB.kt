// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {


val ocwfj: Kkacz = Kkacz()
val ueytn: Ovfzm = Ovfzm()
val pntlw: Mwxfy = Mwxfy()
val snpou: Ovfzm.Legjm = Ovfzm.Legjm()

val wxoop: UShort = pntlw.vpwi
}
// oldKlib.kt
// isKlib=true
private fun  box(): String {
    val result = "OK"

    fun foo() = result

    return (::foo)()
}
internal fun  whkfk(): Byte? { TODO() }
class Kkacz
class Ovfzm{
class Legjm
}
class Mwxfy{
val UShort?.vpwi: Function2<Byte, Double, Function1<Triple<Mwxfy, Kkacz, Mwxfy>, Kkacz>>  
get() =  TODO()
}
// newKlib.kt
// isKlib=true
private fun  box(): String {
    val result = "OK"

    fun foo() = result

    return (::foo)()
}
internal fun  whkfk(): Byte? { TODO() }
 
class Ovfzm{
class Legjm
}
class Mwxfy{
val UShort?.vpwi: Function2<Byte, Double, Function1<Triple<Mwxfy, Kkacz, Mwxfy>, Kkacz>>  
get() =  TODO()
}


Combined output:
====================
Unresolved reference: vpwi