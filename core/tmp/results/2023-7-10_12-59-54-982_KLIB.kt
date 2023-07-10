// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {


val kadnb: Delegate = Delegate("oselo")
val qtmen: Pqurh = Pqurh()
val imcmt: String = box()
val purbl: String = kadnb.getValue("fyzaf", "qwsza")
val tnmva: Array = qtmen.nflp
qtmen.nflp = TODO()
}
// oldKlib.kt
// isKlib=true
data class Delegate  (val value: String){

    operator fun getValue(thisRef: Any?, kProperty: Any?) = value

}

fun box(): String {
    val x by Delegate("O")

    open class Local  (val y: String = "ljrza"){


        val fn  = { x + y }
    

}

    return Local("K").fn()
}
open class Pqurh{
lateinit var nflp: Array<Byte>
}
suspend private fun  eizgc(): Delegate { TODO() }
// newKlib.kt
// isKlib=true
data class Delegate  private fun  Delegate(val value: String){

    operator fun getValue(thisRef: Any?, kProperty: Any?) = value

}

fun box(): String {
    val x by Delegate("O")

    open class Local  (val y: String = "ljrza"){


        val fn  = { x + y }
    

}

    return Local("K").fn()
}
open class Pqurh{
lateinit var nflp: Array<Byte>
}
suspend private fun  eizgc(): Delegate { TODO() }


Combined output:
====================
One type argument expected for class Array<T>