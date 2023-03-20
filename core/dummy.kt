package foo
public sealed class Mju  (tcb: Sequence<WithNative>, vararg wuu: Array<ULong?>, val lqb: Byte?, zsx: Set<UInt>){


public var etdj: ArrayList<WithNative>  
get() = TODO()
set(value) = TODO()


public val xbuv: HashMap<Sequence<UInt?>, Function2<Set<WithNative?>, Float, WithNative>>?  
get() = TODO()


internal var qfae: ULong  = 18.toULong()
get() = 18.toULong()
private set




}
public open class Ycl  (val iyj: Mju, var ert: MutableMap<MutableMap<ULong, UShort?>, ULong?>, val akf: Short){


tailrec protected final fun  xxhnr(a: UShort): Collection<WithNative?> = TODO()

inline tailrec  final fun <reified T, S>  rhfsr(a: Short, b: Mju): Mju = TODO()

internal var ornv: Mju  
get() = TODO()
set(value) = TODO()


internal val fowx: Function1<Byte, WithNative>  = {a: Byte -> WithNative()}
companion object {

inline tailrec internal  fun   WithNative.iouud(a: WithNative, b: Function2<Short, HashMap<UByte, Float>, Mju>): Set<ULong> = TODO()

}



}
private data  class Uxh  (var dqm: WithNative): Ycl(){


tailrec public  fun <T: ServiceConfigurationError, S: IntSummaryStatistics>  cquqq(a: String, b: Char, c: MutableMap<Function1<UShort?, UInt>, WithNative>, d: Short): Mju = TODO()

inline tailrec internal  fun <T, S>  lirdm(a: T, b: T, c: T): Function2<WithNative, Mju, ULong> = TODO()
companion object {

inline suspend internal final fun  ponxe(a: UInt, b: Map<Function2<Boolean, WithNative, Float>, Pair<Ycl, ULong>>): Mju? = TODO()

internal var ygio: ArrayList<UShort>  
get() = TODO()
set(value) = TODO()


private val paok: Byte  
get() = TODO()


}



}
internal data  class Gnw  (var ebc: Long){


infix protected final fun   String.ukkdb(a: MutableMap<ULong, UInt>): ULong = TODO()

public final fun  nsdix(a: Collection<UInt>, b: Int?): Mju? = TODO()

infix   fun   HashMap<String, Float?>.zctxt(a: Double): Short = TODO()



}
private final class Yzx <in T: Ycl>  (vararg buy: T, var put: MutableMap<Uxh, List<Pair<Float, Int?>>?>, czq: T, var fxf: Ycl?){


private var inxp: T  
get() = TODO()
set(value) = TODO()

companion object {

private infix   fun   Mju.impwq(a: Triple<List<Short>, LinkedHashSet<Gnw>, Long?>): UByte? = TODO()

private val sxhe: Char  
get() = TODO()


public val flsg: Byte?  
get() = TODO()


}



}
private sealed class Pxd  : Ycl(){


tailrec  final fun <T: Asserter, S: KotlinNullPointerException>  sobkm(a: Int?, b: Float?): WithNative = TODO()

internal  fun  utcqb(): WithNative = TODO()

external   fun  saidw(a: Gnw, b: Collection<WithNative?>, c: LinkedHashSet<Double>, d: ULong): Gnw

public val xdsa: Collection<Function1<Byte, Mju>>  = TODO()

internal val kxai: Byte  
get() = TODO()


private var ziva: Uxh  
get() = TODO()
set(value) = TODO()




}
private inline  final class Onw <T: UnknownFormatFlagsException, S>  (val xan: Triple<Long, Triple<WithNative, Byte, LinkedHashMap<UShort, Function1<UInt, HashSet<Gnw>>>?>, Char> = Triple<Long, Triple<WithNative, Byte, LinkedHashMap<UShort, Function1<UInt, HashSet<Gnw>>>?>, Char>(63, Triple<WithNative, Byte, LinkedHashMap<UShort, Function1<UInt, HashSet<Gnw>>>?>(WithNative(), -78, LinkedHashMap<UShort, Function1<UInt, HashSet<Gnw>>>()), 'ꌄ')){


private external   fun <T: MissingFormatArgumentException, S: Ycl>  xtlzt(a: UByte?, b: T): Ycl

private external   fun  dsgmy(a: HashMap<Triple<Pxd, Char, Gnw>, ULong>, b: Mju, c: Double): S

public val kjwa: HashMap<Gnw?, Short>  
get() = TODO()


public val omim: Long  
get() = TODO()


private val pjtt: UByte  
get() = TODO()

companion object {

tailrec internal final fun  krvlf(a: String, b: Long, c: LinkedHashMap<HashMap<Mju, Int>, UInt>): Char = TODO()

private  fun  qtdaa(a: UShort, b: ULong?, c: WithNative): ULong = TODO()

public var wjri: List<Map<UByte?, UByte>>?  
get() = TODO()
set(value) = TODO()


private val isos: Gnw  
get() = TODO()


}



}
internal  class Rpk  (val kfw: String?){


private var cjzy: Short  = 47

internal val lltx: UShort  
get() = 48.toUShort()

companion object {

public val zxuz: List<Gnw>  
get() = Collections.nCopies<Gnw>(-64, Gnw(46))


lateinit var ezgd: Pxd

}



}
private final class Ooh <in T, S>  : Ycl(){


inline infix private  fun   Uxh?.sgwrl(a: in T): List<UByte> = TODO()

internal  fun <T: Ycl>  uqnnm(a: Array<Double>, b: Array<Int>, c: S, d: Byte?):  = TODO()

private var csyv: T  
get() = TODO()
set(value) = TODO()


internal var rhjb: T  
get() = TODO()
set(value) = TODO()


internal var qgzb: T  
get() = TODO()
set(value) = TODO()




}




val abcq1: Byte = TODO()

fun  abcq(a: Byte){}

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
