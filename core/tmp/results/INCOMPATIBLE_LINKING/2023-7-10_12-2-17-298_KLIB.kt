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


val zfofm: Test = object: Test {
override public open val prop: String = "qdbwp"
override public open val defaultImplTrigger: String = "pvxnt"
}
val xwjrv: Test2 = object: Test2 {
override public open val prop: String = "hebww"
override public open val defaultImplTrigger: String = "jqtbs"
}
val ftdjw: String = box()
val utvry: String = zfofm.prop
val sukwe: String = zfofm.defaultImplTrigger
val aysdi: String = xwjrv.prop
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

fun  box(): String {
    return object : Test2 {}.prop
}



Combined output:
====================
====================
====================
====================
