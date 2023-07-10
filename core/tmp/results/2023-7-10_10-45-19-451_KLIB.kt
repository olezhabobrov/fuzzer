// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {


val kcyer: Fuvek = Fuvek
val metvq: Fuvek.Lqrvk = Fuvek.Lqrvk()
val tupta: String = zap("izmrm")
val abnjm: String = tryZap("srogb", "mzhzf", {a: String, b: String -> "uiaoa"})
val scaor: String = box()
val fetbk: Byte = metvq.ulxxf()

}
// oldKlib.kt
// isKlib=true

fun zap(s: String) = s

inline fun tryZap(s1: String, s2: String, fn: (String, String) -> String) =
        fn(
                try { zap(s1) } catch (e: Exception) { "" },
                try { zap(s2) } catch (e: Exception) { "" }
        )

fun box(): String = tryZap("O", "K") { a, b -> a + b }
 object Fuvek{
open class Lqrvk{
tailrec internal  fun  ulxxf(): Byte { TODO() }
}
}
private fun  wcjqp(): Sequence<Char> { TODO() }
// newKlib.kt
// isKlib=true

fun zap(s: String) = s

inline fun tryZap(s1: String, s2: String, fn: (String, String) -> String) =
        fn(
                try { zap(s1) } catch (e: Exception) { "" },
                try { zap(s2) } catch (e: Exception) { "" }
        )

fun box(): String = tryZap("O", "K") { a, b -> a + b }
 object Fuvek{
open class Lqrvk{
tailrec internal  fun  ulxxf(): Byte { TODO() }
}
fun  zkqvu(): Long { TODO() }
}
private fun  wcjqp(): Sequence<Char> { TODO() }


Combined output:
====================
Cannot access 'ulxxf': it is internal in 'Lqrvk'