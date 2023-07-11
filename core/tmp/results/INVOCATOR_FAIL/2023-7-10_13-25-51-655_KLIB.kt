// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {


val uovri: A = A(-63)
val nssme: Rtxxi = Rtxxi()
val nxoss: Ehrhu = object: Ehrhu {
}
val wqiba: A.Nsgml = A.Nsgml()
val espei: Rtxxi.Gfxqr = Rtxxi.Gfxqr()
val mgujn: String = box()
val vebpo: Int = uovri.f()
val vwhre: Int = nssme.uaay
}
// oldKlib.kt
// isKlib=true
// !LANGUAGE: +InlineClasses
// IGNORE_BACKEND: JVM

inline class A(val x: Int) {
    fun f(): Int = super.hashCode()
class Nsgml{
public var papv: Rtxxi  = Rtxxi()
}
}

inline fun  box(): String {
    val a  = A(1).f()
    return "OK"
}
class Rtxxi{
class Gfxqr
private val uaay: HashMap<UByte, Boolean?>?  
get() = HashMap<UByte, Boolean?>(87, -78.700134f)
}
interface Ehrhu
// newKlib.kt
// isKlib=true
// !LANGUAGE: +InlineClasses
// IGNORE_BACKEND: JVM

inline class A(val x: Int) {
    fun f(): Int = super.hashCode()
class Nsgml{
public var papv: Rtxxi  = Rtxxi()
}
}

inline fun  box(): String {
    val a  = A(1).f()
    return "OK"
}
class Rtxxi{
class Gfxqr
private inline var uaay : HashMap<UByte, Boolean?>? 
get() = HashMap<UByte, Boolean?>(87, -78.700134f)
}
interface Ehrhu


Combined output:
====================
Type mismatch: inferred type is HashMap<UByte, Boolean?>? but Int was expected
Cannot access 'uaay': it is private in 'Rtxxi'