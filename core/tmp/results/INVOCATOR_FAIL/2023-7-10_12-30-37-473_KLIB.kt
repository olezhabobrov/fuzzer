// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {


val fzqau: Base = Base("ietxa", "qxsqf")
val nyqth: Derived = Derived()
val fvtjz: String = box()
val buaow: String = result
result = TODO()
val rncef: Derived = fzqau.ewnh
fzqau.ewnh = TODO()
}
// oldKlib.kt
// isKlib=true

var result = "fail"

open class Base(val o: String, val k: String){
private var ewnh: Derived  = Derived()
get() = Derived()
private set
}
class Derived : Base(k = { result = "O"; "K"}() , o = {result += "K"; "O"}()) {interface Rzgwi

}

fun box(): String {
    var derived  = Derived()

    if (result != "OK") return "fail $result"
    return derived.o + derived.k
}
// newKlib.kt
// isKlib=true

var result = "fail"

open class Base(val o: String, val k: String){
private var ewnh: Derived  = Derived()
get() = Derived()
private set
}
class Derived : Base(k = { result = "O"; "K"}() , o = {result += "K"; "O"}()) {interface Rzgwi

}

fun box(): String {
    var derived  = Derived()

    if (result != "OK") return "fail $result"
    return derived.o + derived.k
}
class Eksve


Combined output:
====================
Cannot access 'ewnh': it is private in 'Base'
Cannot access 'ewnh': it is private in 'Base'