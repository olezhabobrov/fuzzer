// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {


val qsmfl: Sgjce = Sgjce
val rwzum: Sgjce.Vkxah = Sgjce.Vkxah()
val aefuj: String = test<HashSet<ArrayDeque<UInt>>>()
val wisyn: String = box()
val pkjqh: UInt = qsmfl.qvvuo<Set<Sgjce>>()

}
// oldKlib.kt
// isKlib=true
fun <T> test(): String {
    val x = object {
        fun <S> foo() = "OK"
    }
    return x.foo<Any>()
}

fun box() = test<Int>()
object Sgjce{
class Vkxah
private  fun <T>  qvvuo(): UInt { TODO() }
}
// newKlib.kt
// isKlib=true
fun <T> test(): String {
    val x = object {
        fun <S> foo() = "OK"
    }
    return x.foo<Any>()
}

fun box() = test<Int>()
object Sgjce{
class Vkxah
private fun <R>  qvvuo(): UInt { TODO() }
}


Combined output:
====================
Cannot access 'qvvuo': it is private in 'Sgjce'