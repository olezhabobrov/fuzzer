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


val nnjvf: A = A(-67)
val cbcxm: Zwaht = Zwaht
val zcbwd: Dlaum = Dlaum()
val rmmtj: Zwaht.Wmdnq = Zwaht.Wmdnq()
val jgnqw: Boolean = isNullVacuousLeft(A(83))
val gxfiz: Boolean = isNullVacuousRight(A(-6))
val ihtqj: Boolean = isNullLeft(A(-50))
val auuqc: Boolean = isNullRight(A(74))
val svdly: Boolean = isEqualSame(A(-81), A(33))
val iiopx: Boolean = isEqualAnyLeft(A(-80), "zceaw")
val ofkpn: Boolean = isEqualAnyRight("ejqfj", A(-73))
val hqlmr: Boolean = isEqualSameNullable(A(73), A(-2))
val sqxxk: Boolean = isEqualAnyNullableLeft(A(-57), "cjlbv")
val fnthc: Boolean = isEqualAnyNullableRight("lshht", A(-91))
val jauon: Boolean = isEqualNullableUnboxedLeft(A(-69), A(-18))
val ezzxz: Boolean = isEqualNullableUnboxedRight(A(53), A(90))
val fydoo: String = box()

}
// oldKlib.kt
// isKlib=true
// !LANGUAGE: +InlineClasses

inline class A(val x: Int = 12){
interface Juefy
}

fun isNullVacuousLeft(s: A) = s == null
fun isNullVacuousRight(s: A) = null == s
fun isNullLeft(s: A?) = s == null
fun isNullRight(s: A?) = null == s
fun isEqualSame(s: A, t: A) = s == t
fun isEqualAnyLeft(s: A, t: Any?) = s == t
fun isEqualAnyRight(s: Any?, t: A) = s == t
fun isEqualSameNullable(s: A?, t: A?) = s == t
fun isEqualAnyNullableLeft(s: A?, t: Any?) = s == t
fun isEqualAnyNullableRight(s: Any?, t: A?) = s == t
fun isEqualNullableUnboxedLeft(s: A, t: A?) = s == t
fun isEqualNullableUnboxedRight(s: A?, t: A) = s == t

fun box(): String {
    if (isNullVacuousLeft(A(0))) return "Fail 1"
    if (isNullVacuousRight(A(0))) return "Fail 2"
    if (isNullLeft(A(0))) return "Fail 3"
    if (isNullRight(A(0))) return "Fail 4"
    if (!isNullLeft(null)) return "Fail 5"
    if (!isNullRight(null)) return "Fail 6"
    if (!isEqualSame(A(0), A(0))) return "Fail 7"
    if (isEqualSame(A(0), A(1))) return "Fail 8"
    if (isEqualAnyLeft(A(0), 0)) return "Fail 9"
    if (isEqualAnyLeft(A(0), null)) return "Fail 10"
    if (!isEqualAnyLeft(A(0), A(0))) return "Fail 11"
    if (isEqualAnyRight(0, A(0))) return "Fail 12"
    if (isEqualAnyRight(null, A(0))) return "Fail 13"
    if (!isEqualAnyRight(A(0), A(0))) return "Fail 14"
    if (!isEqualSameNullable(null, null)) return "Fail 15"
    if (!isEqualSameNullable(A(0), A(0))) return "Fail 16"
    if (isEqualSameNullable(null, A(0))) return "Fail 17"
    if (isEqualSameNullable(A(0), null)) return "Fail 18"
    if (isEqualSameNullable(A(0), A(1))) return "Fail 19"
    if (!isEqualAnyNullableLeft(null, null)) return "Fail 20"
    if (!isEqualAnyNullableLeft(A(0), A(0))) return "Fail 21"
    if (isEqualAnyNullableLeft(A(0), 0)) return "Fail 22"
    if (isEqualAnyNullableLeft(null, 0)) return "Fail 23"
    if (isEqualAnyNullableLeft(A(0), null)) return "Fail 24"
    if (isEqualAnyNullableLeft(A(0), A(1))) return "Fail 25"
    if (!isEqualAnyNullableRight(null, null)) return "Fail 26"
    if (!isEqualAnyNullableRight(A(0), A(0))) return "Fail 27"
    if (isEqualAnyNullableRight(0, A(0))) return "Fail 28"
    if (isEqualAnyNullableRight(0, null)) return "Fail 29"
    if (isEqualAnyNullableRight(null, A(0))) return "Fail 30"
    if (isEqualAnyNullableRight(A(0), A(1))) return "Fail 31"
    if (isEqualNullableUnboxedLeft(A(0), A(1))) return "Fail 32"
    if (!isEqualNullableUnboxedLeft(A(0), A(0))) return "Fail 33"
    if (isEqualNullableUnboxedRight(A(0), A(1))) return "Fail 34"
    if (!isEqualNullableUnboxedRight(A(1), A(1))) return "Fail 35"
    if (isEqualNullableUnboxedLeft(A(0), null)) return "Fail 36"
    if (isEqualNullableUnboxedRight(null, A(1))) return "Fail 37"
    return "OK"
}
object Zwaht{
class Wmdnq
}
open class Dlaum
// newKlib.kt
// isKlib=true
// !LANGUAGE: +InlineClasses

inline class A(val x: Int = 12){
private interface Juefy
}

fun isNullVacuousLeft(s: A) = s == null
fun isNullVacuousRight(s: A) = null == s
fun isNullLeft(s: A?) = s == null
fun isNullRight(s: A?) = null == s
fun isEqualSame(s: A, t: A) = s == t
fun isEqualAnyLeft(s: A, t: Any?) = s == t
fun isEqualAnyRight(s: Any?, t: A) = s == t
fun isEqualSameNullable(s: A?, t: A?) = s == t
fun isEqualAnyNullableLeft(s: A?, t: Any?) = s == t
fun isEqualAnyNullableRight(s: Any?, t: A?) = s == t
fun isEqualNullableUnboxedLeft(s: A, t: A?) = s == t
fun isEqualNullableUnboxedRight(s: A?, t: A) = s == t

fun box(): String {
    if (isNullVacuousLeft(A(0))) return "Fail 1"
    if (isNullVacuousRight(A(0))) return "Fail 2"
    if (isNullLeft(A(0))) return "Fail 3"
    if (isNullRight(A(0))) return "Fail 4"
    if (!isNullLeft(null)) return "Fail 5"
    if (!isNullRight(null)) return "Fail 6"
    if (!isEqualSame(A(0), A(0))) return "Fail 7"
    if (isEqualSame(A(0), A(1))) return "Fail 8"
    if (isEqualAnyLeft(A(0), 0)) return "Fail 9"
    if (isEqualAnyLeft(A(0), null)) return "Fail 10"
    if (!isEqualAnyLeft(A(0), A(0))) return "Fail 11"
    if (isEqualAnyRight(0, A(0))) return "Fail 12"
    if (isEqualAnyRight(null, A(0))) return "Fail 13"
    if (!isEqualAnyRight(A(0), A(0))) return "Fail 14"
    if (!isEqualSameNullable(null, null)) return "Fail 15"
    if (!isEqualSameNullable(A(0), A(0))) return "Fail 16"
    if (isEqualSameNullable(null, A(0))) return "Fail 17"
    if (isEqualSameNullable(A(0), null)) return "Fail 18"
    if (isEqualSameNullable(A(0), A(1))) return "Fail 19"
    if (!isEqualAnyNullableLeft(null, null)) return "Fail 20"
    if (!isEqualAnyNullableLeft(A(0), A(0))) return "Fail 21"
    if (isEqualAnyNullableLeft(A(0), 0)) return "Fail 22"
    if (isEqualAnyNullableLeft(null, 0)) return "Fail 23"
    if (isEqualAnyNullableLeft(A(0), null)) return "Fail 24"
    if (isEqualAnyNullableLeft(A(0), A(1))) return "Fail 25"
    if (!isEqualAnyNullableRight(null, null)) return "Fail 26"
    if (!isEqualAnyNullableRight(A(0), A(0))) return "Fail 27"
    if (isEqualAnyNullableRight(0, A(0))) return "Fail 28"
    if (isEqualAnyNullableRight(0, null)) return "Fail 29"
    if (isEqualAnyNullableRight(null, A(0))) return "Fail 30"
    if (isEqualAnyNullableRight(A(0), A(1))) return "Fail 31"
    if (isEqualNullableUnboxedLeft(A(0), A(1))) return "Fail 32"
    if (!isEqualNullableUnboxedLeft(A(0), A(0))) return "Fail 33"
    if (isEqualNullableUnboxedRight(A(0), A(1))) return "Fail 34"
    if (!isEqualNullableUnboxedRight(A(1), A(1))) return "Fail 35"
    if (isEqualNullableUnboxedLeft(A(0), null)) return "Fail 36"
    if (isEqualNullableUnboxedRight(null, A(1))) return "Fail 37"
    return "OK"
}
object Zwaht{
class Wmdnq
}
open class Dlaum


Combined output:
====================
====================
====================
====================
