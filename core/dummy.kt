import java.util.*
import kotlin.*




val abcq1: LinkedHashSet<Byte> = TODO()

fun  abcq(a: LinkedHashSet<Byte>){}

// !LANGUAGE: +InlineClasses

inline class IC(val x: Int)

abstract class A<T> {
    var t: T? = null
    final fun foo(): T = t!!
}

class B: A<IC>()

interface I {
    fun foo(): IC
}

class B2: A<IC>(), I{
private var aroo: Function2<I, List<Int>, Function1<Short?, Function2<Map<Long, B2>, ULong, ArrayList<Collection<Set<I>>>>>>  = {a: I, b: List<Int> -> {a: Short? -> {a: Map<Long, B2>, b: ULong -> ArrayList<Collection<Set<I>>>()}}}
get() = {a: I, b: List<Int> -> {a: Short? -> {a: Map<Long, B2>, b: ULong -> ArrayList<Collection<Set<I>>>()}}}
private set
}


fun box(): String {
    val b = B()
    b.t = IC(10)
    if (b.foo() != IC(10)) return "Fail 1"

    val b2 = B2()
    b2.t = IC(10)
    if (b2.foo() != IC(10)) return "Fail 2"

    val b2i: I = b2
    if (b2i.foo() != IC(10)) return "Fail 3"

    return "OK"
}
internal fun  bvfia(): I { TODO() }