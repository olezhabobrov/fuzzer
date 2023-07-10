// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {


val ivyyp: Base = Base({ "hxsqq"})
val tboxe: Yxxaw = Yxxaw
val uuqau: String = box()
val uqgvh: Yxxaw = ivyyp.miag
ivyyp.miag = TODO()
}
// oldKlib.kt
// isKlib=true
open class Base(val fn: () -> String){
protected var miag: Yxxaw  
get() = Yxxaw
set(value) = TODO()
}

fun box(): String {
    class Local  {

        inner class Inner(ok: String) : Base({ ok })
    
}

    return Local().Inner("OK").fn()
}
tailrec internal fun  qbsru(): Triple<String, UByte, Function2<ArrayList<Double>, Long?, Function1<Base, Base?>>> { TODO() }
object Yxxaw
// newKlib.kt
// isKlib=true
open class Base(val fn: () -> String){
protected var miag: Yxxaw  
get() = Yxxaw
set(value) = TODO()
}

fun box(): String {
    class Local  {

        inner class Inner(ok: String) : Base({ ok })
    
}

    return Local().Inner("OK").fn()
}
tailrec internal fun  qbsru(): Triple<String, UByte, Function2<ArrayList<Double>, Long?, Function1<Base, Base?>>> { TODO() }
object Yxxaw
fun  coefm(): Base { TODO() }


Combined output:
====================
Cannot access 'miag': it is protected in 'Base'
Cannot access 'miag': it is protected in 'Base'