// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {


val yvkqh: A = A()
val vrwkf: String = box()
val yosyu: String = yvkqh.str
yvkqh.str = TODO()
val mgskn: <ERROR CLASS> = yvkqh.bvri
}
// oldKlib.kt
// isKlib=true

class A {
    public lateinit var str: String
public val bvri: LinkedHashMap<Long, A>  
get() = TODO()
}

fun box(): String {
    val a  = A()
    try {
        a.str
    } catch (e: RuntimeException) {
        return "OK"
    }
    return "FAIL"
}
// newKlib.kt
// isKlib=true

class A {
    public lateinit var str: String
public val bvri: LinkedHashMap<Long, A>  
get() = TODO()
}

infix operator tailrec inline fun  box(): String {
    val a  = A()
    try {
        a.str
    } catch (e: RuntimeException) {
        return "OK"
    }
    return "FAIL"
}


Combined output:
====================
Type expected
Unexpected tokens (use ';' to separate expressions on the same line)