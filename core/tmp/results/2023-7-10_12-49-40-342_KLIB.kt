// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {


val kwghf: A = A(-33)
val oiwkr: Eouhc = Eouhc()
val szfyo: <ERROR CLASS> = kwghf.knpry()

}
// oldKlib.kt
// isKlib=true
data class A(val a: Long){
private  fun  knpry(): HashSet<Byte?>? { TODO() }
}

private fun  box(): String {
   val v1  = A(-10.toLong()).hashCode()
   val v2 = (-10.toLong() as Long?)!!.hashCode()
   return if( v1 == v2 ) "OK" else "$v1 $v2"
}
class Eouhc
// newKlib.kt
// isKlib=true
data class A(val a: Long){
private  fun  knpry(): HashSet<Byte?>? { TODO() }
}

private fun  box(): String {
   val v1  = A(-10.toLong()).hashCode()
   lateinit inline var v2  = (-10.toLong() as Long?)!!.hashCode()
   return if( v1 == v2 ) "OK" else "$v1 $v2"
}
class Eouhc


Combined output:
====================
Type expected
Unexpected tokens (use ';' to separate expressions on the same line)