// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {


val pkwmx: Ckvfm = Ckvfm()
val uwhwq: Unit = check1()
val jnfik: Unit = check2()
val pfpet: String = box()
val rcqqo: Ckvfm = pkwmx.gcwz
pkwmx.gcwz = TODO()
}
// oldKlib.kt
// isKlib=true
fun check1() {
    val result  = if (true) {
    if (true) 1 else 2
}
else 3
    if (result != 1) throw AssertionError("result: $result")
}

fun check2() {
    val result = if (true)
        if (true) 1 else 2
    else 3
    if (result != 1) throw AssertionError("result: $result")
}

fun box(): String {
    check1()
    check2()
    return "OK"
}
class Ckvfm{
private var gcwz: Ckvfm  = Ckvfm()
}
// newKlib.kt
// isKlib=true
fun check1() {
    val result  = if (true) {
    if (true) 1 else 2
}
else 3
    if (result != 1) throw AssertionError("result: $result")
}

fun check2() {
    val result = if (true)
        if (true) 1 else 2
    else 3
    if (result != 1) throw AssertionError("result: $result")
}

fun box(): String {
    check1()
    check2()
    return "OK"
}
class Ckvfm{
public var gcwz : Ckvfm = Ckvfm()
}


Combined output:
====================
Cannot access 'gcwz': it is private in 'Ckvfm'
Cannot access 'gcwz': it is private in 'Ckvfm'