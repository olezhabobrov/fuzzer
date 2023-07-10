// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {


val raxlp: Wbwjc.Idzir = Wbwjc.Idzir
val qrlnc: Boolean = knllr()

}
// oldKlib.kt
// isKlib=true
private fun  box(): String {
    !true
    return "OK"
}
private interface Wbwjc  {

 
object Idzir

}
suspend private fun <B, S>  mpwfo(): Wbwjc { TODO() }
public fun  knllr(): Boolean { TODO() }
// newKlib.kt
// isKlib=true
private fun  box(): String {
    !true
    return "OK"
}
private interface Wbwjc  {

 
object Idzir

}
suspend private infix operator tailrec inline fun <B, S>  mpwfo(): Wbwjc { TODO() }
public fun  knllr(): Boolean { TODO() }


Combined output:
====================
Cannot access 'Wbwjc': it is private in file
Cannot access 'Idzir': it is public in 'Wbwjc'
Cannot access 'Wbwjc': it is private in file
Cannot access 'Wbwjc': it is private in file