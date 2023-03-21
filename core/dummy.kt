package foo
import kotlin.*

public open class Hxq  (val omv: Int? = -76, val ysb: Long){


external   fun  ervef(a: Byte, b: UByte): WithNative

tailrec private final fun  ggbmo(a: ArrayList<Float?>, b: WithNative): WithNative = TODO()

infix   fun <T>   Float?.vsfue(a: T): Byte = TODO()

private var mbol: Short  
get() = TODO()
set(value) = TODO()

companion object {

tailrec internal  fun  xiwxo(a: LinkedHashMap<Int, Collection<Double>>, b: Short, c: UByte): Float = TODO()

infix internal  fun   WithNative.lqbqz(a: Pair<Char, WithNative>): WithNative = TODO()

}



}




val abcq1: UInt = TODO()

fun  abcq(a: UInt){}

// TARGET_BACKEND: JVM
// IGNORE_BACKEND: ANDROID

// FULL_JDK

class WithNative {
    external fun foo()
}

fun box(): String {
    try {
        WithNative().foo()
        return "Link error expected"
    }
    catch (e: java.lang.UnsatisfiedLinkError) {
        return "OK"
    }
}
