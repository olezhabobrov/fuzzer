// INCOMPATIBLE_LINKING
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]
// result:[-Xinclude=main.klib, -l, lib.klib, -Xpartial-linkage-loglevel=error]
// result:[-p, library, -o, lib.klib, projectTmp/newKlib.kt]
// result:[-Xinclude=main.klib, -l, lib.klib, -Xpartial-linkage-loglevel=error]

// files
// oldKlib.kt
// isKlib=true
// !LANGUAGE: +ProperArrayConventionSetterWithDefaultCalls
var inc: String = ""

class X {
    var result: String = "fail"

    operator fun get(name: String, type: Int = 100) = name + type

    operator fun set(name: String, defaultParam: String = "_default_", value: String) {
        result = name + defaultParam + value;
    }
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

    var y = Y()
    y["index"] += "OK"
    if (y.result != "index_default_in_setter_index_default2__default1_OK") return "fail 2: ${y.result}"

    return "OK"
}
// main.kt
// isKlib=false
fun main() {


val xzqzp: X = X()
val ahvfl: Y = Y()
val wwary: String = box()
val njvlp: String = xzqzp.get(name = "rctqw", type = -12)
val wzjll: Unit = xzqzp.set(name = "wvyzy", defaultParam = "aidai", value = "hlxgd")
val vduln: String = ahvfl.get(name = "xhcvu")
val zwyqz: Unit = ahvfl.set(value = "twmne", name = "vqvkn")
val wggaq: String = inc
inc = TODO()
val isavo: String = xzqzp.result
xzqzp.result = TODO()
val aacvk: String = ahvfl.result
ahvfl.result = TODO()
}
// newKlib.kt
// isKlib=true
// !LANGUAGE: +ProperArrayConventionSetterWithDefaultCalls
var inc: String = ""

class X {
    var result: String = "fail"

    operator fun get(name: String, type: Int = 100) = name + type

    operator fun set(name: String, defaultParam: String = "_default_", value: String) {
        result = name + defaultParam + value;
    }
}

class Y {
    var result: String = "fail"

    operator fun get(name: String, type: String = "_default2_", type2: String = "_default1_") = name + type + type2

    operator fun set(name: String, defaultParam: String = "_default_in_setter_", value: String) {
        result = name + defaultParam + value;
    }
}

fun box(): String {
    val x  = X()
    x["index"] += "OK"
    if (x.result != "index_default_index100OK") return "fail 1: ${x.result}"

    var y = Y()
    y["index"] += "OK"
    if (y.result != "index_default_in_setter_index_default2__default1_OK") return "fail 2: ${y.result}"

    return "OK"
}


Combined output:
====================
====================
====================
====================
