// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {


val dulvj: X = X()
val inuag: Y = Y()
val dguuz: X.Deeow = X.Deeow
val duxho: String = box()
val vlete: Map = pulmp<X>()
val aoyzt: String = dulvj.get(name = "jvvjh", type = 88)
val wuito: Unit = dulvj.set(name = "efjnq", value = "fkzjl")
val pufsl: String = inuag.get(name = "dapht", type = "piwja", type2 = "odifg")
val pqpiy: Unit = inuag.set(value = "tswxz", name = "ztyql")
val ejhvj: String = inc
inc = TODO()
val chbnt: String = dulvj.result
dulvj.result = TODO()
val ehbmn: String = inuag.result
inuag.result = TODO()
}
// oldKlib.kt
// isKlib=true
// !LANGUAGE: +ProperArrayConventionSetterWithDefaultCalls
var inc: String = ""

open class X  {

    var result: String = "fail"

    operator fun get(name: String, type: Int = 100) = name + type

    operator fun set(name: String, defaultParam: String = "_default_", value: String) {
        result = name + defaultParam + value;
    }
object Deeow

}

class Y {
    var result: String = "fail"

    operator fun get(name: String, type: String = "_default2_", type2: String = "_default1_") = name + type + type2

    operator fun set(name: String, defaultParam: String = "_default_in_setter_", value: String) {
        result = name + defaultParam + value;
    }
}

fun box(): String {
    var x = X()
    x["index"] += "OK"
    if (x.result != "index_default_index100OK") return "fail 1: ${x.result}"

    var y  = Y()
    y["index"] += "OK"
    if (y.result != "index_default_in_setter_index_default2__default1_OK") return "fail 2: ${y.result}"

    return "OK"
}
public fun <T>  pulmp(): Map<Y, X?>? { TODO() }
// newKlib.kt
// isKlib=true
// !LANGUAGE: +ProperArrayConventionSetterWithDefaultCalls
var inc: String = ""

open class X  {

    var result: String = "fail"

    operator fun get(name: String, type: Int = 100) = name + type

    operator fun set(name: String, defaultParam: String = "_default_", value: String) {
        result = name + defaultParam + value;
    }
object Deeow

}

class Y {
    var result: String = "fail"

    operator fun get(name: String, type: String = "_default2_", type2: String = "_default1_") = name + type + type2

    operator fun set(name: String, defaultParam: String = "_default_in_setter_", value: String) {
        result = name + defaultParam + value;
    }
}

fun box(): String {
    var x = X()
    x["index"] += "OK"
    if (x.result != "index_default_index100OK") return "fail 1: ${x.result}"

    var y  = Y()
    y["index"] += "OK"
    if (y.result != "index_default_in_setter_index_default2__default1_OK") return "fail 2: ${y.result}"

    return "OK"
}
public fun <T>  pulmp(): Map<Y, X?>? { TODO() }
internal fun  bddgd(): ULong { TODO() }


Combined output:
====================
2 type arguments expected for interface Map<K, out V>