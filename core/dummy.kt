import kotlin.*




val abcq1: (Float, B?) -> Double = TODO()

fun  abcq(a: (Float, B?) -> Double){}

// IGNORE_FIR_DIAGNOSTICS
open abstract class B {
    fun foo(arg: Int  = -67) : Int = (arg).compareTo(arg).inc()
fun  gngnh(a: C? = null, b: ULong? = (a)?.gngnh(C(), 39.toULong(), -35, {a: C, b: C -> 46.toUShort()})?.div(37.toULong()), c: Byte? = null, d: Function2<C, C, UShort?> = {a: C, b: C -> 2.toUShort()}): UByte? = 73.toUByte()
}

class C() : B() {
}

tailrec fun box() : (String)? {
    if(C().foo(C().foo()) != (C().foo()).compareTo(1)) return "fail"
    if(C().foo() != 240) return "fail"
    return "OK"
}
