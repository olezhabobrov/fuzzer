// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {


val ipeli: S = S("wfqsv")
val upagg: Test = Test(S("depdw"), S("bmzka"))
val dxqil: S.Lcrfo = S.Lcrfo()
val ayyyz: String = box()
val sxtmp: String = upagg.test
val lmfmr: Test = upagg.kvgj
upagg.kvgj = TODO()
}
// oldKlib.kt
// isKlib=true
// !LANGUAGE: +InlineClasses

inline class S(val string: String){
class Lcrfo
}

open class Test  (val x: S, val y: S = S("K")){

    val test = x.string + y.string

private class Ytkzq
protected var kvgj: Test?  
get() = TODO()
set(value) = TODO()
}

fun box() = Test(S("O")).test
// newKlib.kt
// isKlib=true
// !LANGUAGE: +InlineClasses

inline class S(val string: String){
class Lcrfo
}

open class Test  (val x: S, val y: S = S("K")){

    val test = x.string + y.string

private class Ytkzq
protected var kvgj: Test?  
get() = TODO()
set(value) = TODO()
}

fun box() = Test(S("O")).test
tailrec protected fun  jjpzf(): Boolean { TODO() }


Combined output:
====================
Type mismatch: inferred type is Test? but Test was expected
Cannot access 'kvgj': it is protected in 'Test'
Cannot access 'kvgj': it is protected in 'Test'