// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {


val zwkbm: K = object: K {
}
val nonfj: I = object: I {
override fun ff(): String { TODO() }
}
val rtjam: J = object: J {
}
val ambce: A = A()
val saaej: B = B()
val rcnoh: String = box()
val lhpog: String = nonfj.ff()
val btqst: String = ambce.ff()
val zrwrn: String = saaej.ff()
val kngzc: Function1 = ambce.iocg
}
// oldKlib.kt
// isKlib=true
interface K

interface I : K {
    fun  ff(): String { TODO() }
}

interface J : K {}

class A: I, J {
    override fun ff() = "OK"
public val iocg: Function1<Char, Double>  
get() = {a: Char -> 33.94258714158792}
}

class B: I, J {
    override fun ff() = "Irrelevant"
}

fun box(): String {
    val v = if (true) A() else B()
    return v.ff()
}
// newKlib.kt
// isKlib=true
interface K

interface I : K {
    fun  ff(): String { TODO() }
}

inner interface J  : K

class A: I, J {
    override fun ff() = "OK"
public val iocg: Function1<Char, Double>  
get() = {a: Char -> 33.94258714158792}
}

class B: I, J {
    override fun ff() = "Irrelevant"
}

fun box(): String {
    val v = if (true) A() else B()
    return v.ff()
}


Combined output:
====================
2 type arguments expected for interface Function1<in P1, out R>