// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {


val jhlob: Qizjm = Qizjm()
val ryqzo: Vwzms = Vwzms()
val cmuvf: Vwzms.Mdhnt = Vwzms.Mdhnt()
val pwekm: Int = foo(false)
val aobpb: Sequence = ryqzo.pwnl
}
// oldKlib.kt
// isKlib=true
fun foo(b: Boolean) =
    when (b) {
        false -> 0
        true -> 1
        else -> 2
    }

 

class Qizjm
class Vwzms{
class Mdhnt
val Sequence<Double>.pwnl: Triple<LinkedHashMap<Qizjm, Qizjm>, Qizjm, Short>  
get() =  TODO()
}
// newKlib.kt
// isKlib=true
fun foo(b: Boolean) =
    when (b) {
        false -> 0
        true -> 1
        else -> 2
    }

 

class Qizjm{
protected var oewu: UByte  = 19.toUByte()
}
class Vwzms{
class Mdhnt
val Sequence<Double>.pwnl: Triple<LinkedHashMap<Qizjm, Qizjm>, Qizjm, Short>  
get() =  TODO()
}


Combined output:
====================
One type argument expected for interface Sequence<out T>
Unresolved reference: pwnl