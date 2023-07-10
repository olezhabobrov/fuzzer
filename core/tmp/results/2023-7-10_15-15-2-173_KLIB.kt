// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {


val pqkid: Foo = Foo(-90)
val nqusg: FooRef = FooRef("uhejm")
val dapgr: FooLong = FooLong(-26)
val hzzef: FooDouble = FooDouble(-0.3141234931701007)
val fhtkc: Mcbfy = Mcbfy()
val sixxc: FooDouble.Giate = FooDouble.Giate
val uwjok: String = box()
val xbibu: FooRef = zytyh()
val nkyiw: ULong = pqkid.odas
pqkid.odas = TODO()
}
// oldKlib.kt
// isKlib=true
// !LANGUAGE: +InlineClasses

inline class Foo(val x: Int){
public var odas: ULong  
get() = TODO()
set(value) = TODO()
}
inline class FooRef(val y: String)
inline class FooLong(val x: Long)
inline class FooDouble(val y: Double){
object Giate
}

fun box(): String {
    val f = Foo(42)
    if (f.toString() != "Foo(x=42)") return "Fail 1: $f"

    if (!f.equals(f)) return "Fail 2"

    val g = Foo(43)
    if (f.equals(g)) return "Fail 3"

    if (42.hashCode() != f.hashCode()) return "Fail 4"

    val fRef = FooRef("42")
    if (fRef.toString() != "FooRef(y=42)") return "Fail 5: $fRef"

    if (!fRef.equals(fRef)) return "Fail 6"

    val gRef = FooRef("43")
    if (fRef.equals(gRef)) return "Fail 7"

    if ("42".hashCode() != fRef.hashCode()) return "Fail 8"

    val fLong = FooLong(42)
    if (fLong.toString() != "FooLong(x=42)") return "Fail 9: $fLong"

    if (!fLong.equals(fLong)) return "Fail 10"

    val gLong = FooLong(43)
    if (fLong.equals(gLong)) return "Fail 11"

    if (42L.hashCode() != fLong.hashCode()) return "Fail 12"

    val fDouble = FooDouble(42.1)
    if (fDouble.toString() != "FooDouble(y=42.1)") return "Fail 13: $fDouble"

    if (!fDouble.equals(fDouble)) return "Fail 14"

    val gDouble = FooDouble(43.0)
    if (fDouble.equals(gDouble)) return "Fail 15"

    if (42.1.hashCode() != fDouble.hashCode()) return "Fail 16"

    return "OK"
}
class Mcbfy
public fun  zytyh(): FooRef? { TODO() }
// newKlib.kt
// isKlib=true
// !LANGUAGE: +InlineClasses

inline class Foo(val x: Int){
public var odas: ULong  
get() = TODO()
set(value) = TODO()
}
inline class FooRef(val y: String)
inline inner class FooLong  (val x: Long)
inline class FooDouble(val y: Double){
object Giate
}

fun box(): String {
    val f = Foo(42)
    if (f.toString() != "Foo(x=42)") return "Fail 1: $f"

    if (!f.equals(f)) return "Fail 2"

    val g = Foo(43)
    if (f.equals(g)) return "Fail 3"

    if (42.hashCode() != f.hashCode()) return "Fail 4"

    val fRef = FooRef("42")
    if (fRef.toString() != "FooRef(y=42)") return "Fail 5: $fRef"

    if (!fRef.equals(fRef)) return "Fail 6"

    val gRef = FooRef("43")
    if (fRef.equals(gRef)) return "Fail 7"

    if ("42".hashCode() != fRef.hashCode()) return "Fail 8"

    val fLong = FooLong(42)
    if (fLong.toString() != "FooLong(x=42)") return "Fail 9: $fLong"

    if (!fLong.equals(fLong)) return "Fail 10"

    val gLong = FooLong(43)
    if (fLong.equals(gLong)) return "Fail 11"

    if (42L.hashCode() != fLong.hashCode()) return "Fail 12"

    val fDouble = FooDouble(42.1)
    if (fDouble.toString() != "FooDouble(y=42.1)") return "Fail 13: $fDouble"

    if (!fDouble.equals(fDouble)) return "Fail 14"

    val gDouble = FooDouble(43.0)
    if (fDouble.equals(gDouble)) return "Fail 15"

    if (42.1.hashCode() != fDouble.hashCode()) return "Fail 16"

    return "OK"
}
class Mcbfy
public fun  zytyh(): FooRef? { TODO() }


Combined output:
====================
Type mismatch: inferred type is FooRef? but FooRef was expected