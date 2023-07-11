// INCOMPATIBLE_LINKING
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]
// result:[-Xinclude=main.klib, -l, lib.klib, -Xpartial-linkage-loglevel=error]
// result:[-p, library, -o, lib.klib, projectTmp/newKlib.kt]
// result:[-Xinclude=main.klib, -l, lib.klib, -Xpartial-linkage-loglevel=error]

// files
// main.kt
// isKlib=false
fun main() {


val rzypv: Jvjyl = Jvjyl
val vlbme: Dbxyk = Dbxyk()
val eqgtx: Jvjyl.Revyj = Jvjyl.Revyj
val girjd: String = box()

}
// oldKlib.kt
// isKlib=true
operator fun Array<String>.get(index1: Int, index2: Int) = this[index1 + index2]
operator fun Array<String>.set(index1: Int, index2: Int, elem: String) {
    this[index1 + index2] = elem
}

fun box(): String {
    val s  = Array<String>(1, { "" })
    s[1, -1] = "OK"
    return s[-2, 2]
}
 
object Jvjyl{
object Revyj
}
open class Dbxyk
// newKlib.kt
// isKlib=true
operator fun Array<String>.get(index1: Int, index2: Int) = this[index1 + index2]
operator fun Array<String>.set(index1: Int, index2: Int, elem: String) {
    this[index1 + index2] = elem
}

fun box(): String {
    val s  = Array<String>(1, { "" })
    s[1, -1] = "OK"
    return s[-2, 2]
}
 
object Jvjyl{
object Revyj
}
class Dbxyk


Combined output:
====================
====================
====================
====================
