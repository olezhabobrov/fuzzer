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


val fizsu: S = S("cfaov")
val kphvh: Test = Test(S("dbneu"), S("pnxtz"))
val sonei: String = box()
val zahtt: String = kphvh.test
}
// oldKlib.kt
// isKlib=true
// !LANGUAGE: +InlineClasses

inline class S(val string: String)

open class Test  (val x: S, val y: S = S("K")){

    val test = x.string + y.string

private class Ytkzq
}

fun box() = Test(S("O")).test
// newKlib.kt
// isKlib=true
// !LANGUAGE: +InlineClasses

inline class S(val string: String)

open class Test  (val x: S, val y: S = S("K")){

    val test = x.string + y.string

 
}

fun box() = Test(S("O")).test


Combined output:
====================
====================
====================
====================
