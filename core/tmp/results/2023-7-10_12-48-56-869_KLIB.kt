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


val vmfqg: A = A(63)


}
// oldKlib.kt
// isKlib=true
data class A(val a: Long)

private fun  box(): String {
   val v1 = A(-10.toLong()).hashCode()
   val v2 = (-10.toLong() as Long?)!!.hashCode()
   return if( v1 == v2 ) "OK" else "$v1 $v2"
}

// newKlib.kt
// isKlib=true
data internal class A  (val a: Long)

private fun  box(): String {
   val v1 = A(-10.toLong()).hashCode()
   val v2 = (-10.toLong() as Long?)!!.hashCode()
   return if( v1 == v2 ) "OK" else "$v1 $v2"
}



Combined output:
====================
====================
====================
====================
