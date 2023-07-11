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



val ndrqn: String = box()

}
// oldKlib.kt
// isKlib=true
// KJS_WITH_FULL_RUNTIME
// WITH_RUNTIME
// USE_OLD_INLINE_CLASSES_MANGLING_SCHEME

fun box(): String {
    if (Char.MAX_VALUE in Char.MAX_VALUE until Char.MAX_VALUE) return "Fail in Char.MAX_VALUE"
    if (!(Char.MAX_VALUE !in Char.MAX_VALUE until Char.MAX_VALUE)) return "Fail !in Char.MAX_VALUE"

    if (Int.MAX_VALUE in Int.MAX_VALUE until Int.MAX_VALUE) return "Fail in Int.MAX_VALUE"
    if (!(Int.MAX_VALUE !in Int.MAX_VALUE until Int.MAX_VALUE)) return "Fail !in Int.MAX_VALUE"

    if (Long.MAX_VALUE in Long.MAX_VALUE until Long.MAX_VALUE) return "Fail in Long.MAX_VALUE"
    if (!(Long.MAX_VALUE !in Long.MAX_VALUE until Long.MAX_VALUE)) return "Fail !in Long.MAX_VALUE"

    if (UInt.MAX_VALUE in UInt.MAX_VALUE until UInt.MAX_VALUE) return "Fail in UInt.MAX_VALUE"
    if (!(UInt.MAX_VALUE !in UInt.MAX_VALUE until UInt.MAX_VALUE)) return "Fail !in UInt.MAX_VALUE"

    if (ULong.MAX_VALUE in ULong.MAX_VALUE until ULong.MAX_VALUE) return "Fail in ULong.MAX_VALUE"
    if (!(ULong.MAX_VALUE !in ULong.MAX_VALUE until ULong.MAX_VALUE)) return "Fail !in ULong.MAX_VALUE"

    return "OK"
}
 private object Rnmhx{
interface Wchay
}
// newKlib.kt
// isKlib=true
// KJS_WITH_FULL_RUNTIME
// WITH_RUNTIME
// USE_OLD_INLINE_CLASSES_MANGLING_SCHEME

fun box(): String {
    if (Char.MAX_VALUE in Char.MAX_VALUE until Char.MAX_VALUE) return "Fail in Char.MAX_VALUE"
    if (!(Char.MAX_VALUE !in Char.MAX_VALUE until Char.MAX_VALUE)) return "Fail !in Char.MAX_VALUE"

    if (Int.MAX_VALUE in Int.MAX_VALUE until Int.MAX_VALUE) return "Fail in Int.MAX_VALUE"
    if (!(Int.MAX_VALUE !in Int.MAX_VALUE until Int.MAX_VALUE)) return "Fail !in Int.MAX_VALUE"

    if (Long.MAX_VALUE in Long.MAX_VALUE until Long.MAX_VALUE) return "Fail in Long.MAX_VALUE"
    if (!(Long.MAX_VALUE !in Long.MAX_VALUE until Long.MAX_VALUE)) return "Fail !in Long.MAX_VALUE"

    if (UInt.MAX_VALUE in UInt.MAX_VALUE until UInt.MAX_VALUE) return "Fail in UInt.MAX_VALUE"
    if (!(UInt.MAX_VALUE !in UInt.MAX_VALUE until UInt.MAX_VALUE)) return "Fail !in UInt.MAX_VALUE"

    if (ULong.MAX_VALUE in ULong.MAX_VALUE until ULong.MAX_VALUE) return "Fail in ULong.MAX_VALUE"
    if (!(ULong.MAX_VALUE !in ULong.MAX_VALUE until ULong.MAX_VALUE)) return "Fail !in ULong.MAX_VALUE"

    return "OK"
}
 private object Rnmhx{
object Wchay
}


Combined output:
====================
====================
====================
====================
