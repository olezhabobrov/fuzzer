// INCOMPATIBLE_LINKING
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]
// result:[-Xinclude=main.klib, -l, lib.klib, -Xpartial-linkage-loglevel=error]
// result:[-p, library, -o, lib.klib, projectTmp/newKlib.kt]
// result:[-Xinclude=main.klib, -l, lib.klib, -Xpartial-linkage-loglevel=error]

// files
// oldKlib.kt
// isKlib=true

var result = "fail"

open class Base(val o: String, val k: String)
class Derived : Base(k = { result = "O"; "K"}() , o = {result += "K"; "O"}()) {}

fun box(): String {
    val derived = Derived()

    if (result != "OK") return "fail $result"
    return derived.o + derived.k
}
// main.kt
// isKlib=false
fun main() {


val ttfob: Base = Base("fdudi", "ctgqu")
val yldhh: Derived = Derived()
val zbaoi: String = box()
val akqrn: String = result
result = TODO()
}
// newKlib.kt
// isKlib=true

internal var result  = "fail"

open class Base(val o: String, val k: String)
class Derived : Base(k = { result = "O"; "K"}() , o = {result += "K"; "O"}()) {}

fun box(): String {
    val derived = Derived()

    if (result != "OK") return "fail $result"
    return derived.o + derived.k
}


Combined output:
====================
====================
====================
====================
