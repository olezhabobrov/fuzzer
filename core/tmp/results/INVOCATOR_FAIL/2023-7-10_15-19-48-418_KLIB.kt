// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {


val zdjtl: A = A
val spiqc: Etcyt = Etcyt()
val qdpue: String = box()
val vzoja: Int = zdjtl.get(-98, 84, 72)
val iooom: Unit = zdjtl.set(-84, 83, -53, -13)
val rbvsd: A = spiqc.kxoft<Etcyt, String?>()
val nlzww: Int = zdjtl.x
zdjtl.x = TODO()
}
// oldKlib.kt
// isKlib=true
object A {
    var x = 0

    operator fun get(i1: Int, i2: Int, i3: Int): Int = x

    operator fun set(i1: Int, i2: Int, i3: Int, value: Int) {
        x = value
    }
}

fun box(): String {
    A.x = 0
    val xx  = A[1, 2, 3]++
    return if (xx != 0 || A.x != 1) "Failed" else "OK"
}
class Etcyt{
inline tailrec private  fun <T: HashMap<A, UInt>, reified S>  kxoft(): A { TODO() }
}
// newKlib.kt
// isKlib=true
object A {
    var x = 0

    operator fun get(i1: Int, i2: Int, i3: Int): Int = x

    operator fun set(i1: Int, i2: Int, i3: Int, value: Int) {
        x = value
    }
}

fun box(): String {
    A.x = 0
    val xx  = A[1, 2, 3]++
    return if (xx != 0 || A.x != 1) "Failed" else "OK"
}
interface Etcyt  {

inline tailrec private  fun <T: HashMap<A, UInt>, reified S>  kxoft(): A { TODO() }

}


Combined output:
====================
Cannot access 'kxoft': it is private in 'Etcyt'
Type argument is not within its bounds: should be subtype of 'HashMap<A, UInt>'