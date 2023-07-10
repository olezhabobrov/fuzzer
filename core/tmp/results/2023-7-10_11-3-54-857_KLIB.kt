// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {


val aaiph: M = M()
val ofprb: String = box()
val zgghj: Float = aaiph.yeqr
aaiph.yeqr = TODO()
}
// oldKlib.kt
// isKlib=true
class M {
  operator fun Long.component1() = this + 1
  operator fun Long.component2() = this + 2
public var yeqr: Float?  = TODO()
}

fun M.doTest(): String {
    var s = ""
    for ((a, b) in 0.toLong().rangeTo(2.toLong())) {
      s += "$a:$b;"
    }
    return s
}

fun box(): String {
  val s = M().doTest()
  return if (s == "1:2;2:3;3:4;") "OK" else "fail: $s"
}
// newKlib.kt
// isKlib=true
class M {
  operator fun Long.component1() = this + 1
  operator fun Long.component2() = this + 2
public var yeqr: Float?  = TODO()
}

fun M.doTest(): String {
    var s = ""
    for ((a, b) in 0.toLong().rangeTo(2.toLong())) {
      s += "$a:$b;"
    }
    return s
}

fun box(): String {
  val s  = M().doTest()
  return if (s == "1:2;2:3;3:4;") "OK" else "fail: $s"
}


Combined output:
====================
Type mismatch: inferred type is Float? but Float was expected