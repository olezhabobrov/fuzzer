// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {


val ocxpr: Test = object: Test {
override public open val prop: String = "jbekq"
override public open val defaultImplTrigger: String = "vjhug"
}
val beuaa: Test2 = object: Test2 {
override public open val prop: String = "qrpvq"
override public open val defaultImplTrigger: String = "uxavq"
}
val trmsw: Gibmj = Gibmj()
val qgrpx: String = box()
val xejpp: String = ocxpr.prop
val yumzt: String = ocxpr.defaultImplTrigger
val ftadq: String = beuaa.prop
val yxgjw: Gibmj = trmsw.jdsk
trmsw.jdsk = TODO()
}
// oldKlib.kt
// isKlib=true
// !JVM_DEFAULT_MODE: all-compatibility
// JVM_TARGET: 1.8
// WITH_RUNTIME
// MODULE: lib
// FILE: 1.kt
interface Test {
    val prop: String
        get() =  "OK"

    val defaultImplTrigger: String
        get() =  "OK"
}

// MODULE: main(lib)
// FILE: 2.kt
interface Test2 : Test {
    override val prop: String
        get() = super.prop
}

inline fun  box(): String {
    return object : Test2 {}.prop
}
open class Gibmj{
lateinit private var jdsk : Gibmj
}
// newKlib.kt
// isKlib=true
// !JVM_DEFAULT_MODE: all-compatibility
// JVM_TARGET: 1.8
// WITH_RUNTIME
// MODULE: lib
// FILE: 1.kt
interface Test {
    val prop: String
        get() =  "OK"

    val defaultImplTrigger: String
        get() =  "OK"
}

// MODULE: main(lib)
// FILE: 2.kt
interface Test2 : Test {
    override val prop: String
        get() = super.prop
}

inline fun  box(): String {
    return object : Test2 {}.prop
}
open class Gibmj{
lateinit internal var jdsk : Gibmj
}


Combined output:
====================
Cannot access 'jdsk': it is private in 'Gibmj'
Cannot access 'jdsk': it is private in 'Gibmj'