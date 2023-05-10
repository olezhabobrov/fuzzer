import kotlin.*
import kotlin.collections.*
import java.util.*




val abcq1: Array<ArrayDeque<Function2<Abr<UShort, Abr<Abr<UShort?, Short>, Collection<Abr<String, Long?>>>>?, LinkedHashSet<Int>, Abr<Abr<Triple<Long, Abr<Char, Int>, Abr<UInt, String>>, UInt>?, Boolean?>?>>> = TODO()

fun  abcq(a: Array<ArrayDeque<Function2<Abr<UShort, Abr<Abr<UShort?, Short>, Collection<Abr<String, Long?>>>>?, LinkedHashSet<Int>, Abr<Abr<Triple<Long, Abr<Char, Int>, Abr<UInt, String>>, UInt>?, Boolean?>?>>>){}


public sealed class Abr <T, S>  (val ygf: Collection<LinkedHashSet<Long>>, wyl: S, snd: Float){




}
fun returnNullable(): String? = null

inline fun Array<String>.matchAll(fn: (String) -> Unit) {
    for (string in this) {
        fn(null ?: continue)
    }
}

fun Array<String>.matchAll2(fn: (String) -> Unit) {
    matchAll(fn)
}

inline fun Array<String>.matchAll3(crossinline fn: (String) -> Unit) {
    matchAll2 { fn(it) }
}

fun test(a: Array<String>) {
    a.matchAll b@{}
    
val c = false
if (c) {a.matchAll2 {}} else {a.matchAll2 {}}

    a.matchAll3 {}
}

fun box(): String {
    test(arrayOf(""))
    return "OK"
}