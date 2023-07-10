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


val wwcma: INum = object: INum {
override fun get(): Number { TODO() }
}
val seomf: Kctye = Kctye()
val wkqvq: String = box()
val rwozw: Number = wwcma.get()

}
// oldKlib.kt
// isKlib=true
// TARGET_BACKEND: JVM
// JVM_TARGET: 1.8
// SAM_CONVERSIONS: INDY

// CHECK_BYTECODE_TEXT
// JVM_IR_TEMPLATES
// 1 java/lang/invoke/LambdaMetafactory\.metafactory

fun interface INum {
    fun get(): Number
}

fun box(): String {
    val num = INum { 42 }
    if (num.get() != 42)
        return "Failed"
    return "OK"
}
class Kctye
// newKlib.kt
// isKlib=true
// TARGET_BACKEND: JVM
// JVM_TARGET: 1.8
// SAM_CONVERSIONS: INDY

// CHECK_BYTECODE_TEXT
// JVM_IR_TEMPLATES
// 1 java/lang/invoke/LambdaMetafactory\.metafactory

fun interface INum {
    abstract fun  get(): Number
}

fun box(): String {
    val num = INum { 42 }
    if (num.get() != 42)
        return "Failed"
    return "OK"
}
class Kctye


Combined output:
====================
====================
====================
====================
