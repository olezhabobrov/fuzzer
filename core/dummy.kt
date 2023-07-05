package test
import kotlin.reflect.typeOf
import kotlin.reflect.KTypeParameter
import kotlin.test.assertEquals
import kotlin.collections.*
import kotlin.*
import java.util.*



val abcq1: ArrayDeque<Pair<B<B<Short>>, HashMap<UShort?, Container<UInt>>>> = TODO()

fun  abcq(a: ArrayDeque<Pair<B<B<Short>>, HashMap<UShort?, Container<UInt>>>>){}

// TARGET_BACKEND: JVM
// WITH_RUNTIME

class Container<T>

class B<W>

class C<X> {
    val <Y> B<Y>.createY: KTypeParameter where Y : X
        get() = typeOf<Container<Y>>().arguments.single().type!!.classifier as KTypeParameter
}

fun box(): String {
    with(C<Any>()) {
        val y = B<Any>().createY
        assertEquals("X (Kotlin reflection is not available)", y.upperBounds.joinToString())
    }
    return "OK"
}
