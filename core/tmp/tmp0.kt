public abstract class FList<T>() {
    public abstract val head: T
    public abstract val tail: FList<T>
    public abstract val empty: Boolean

    companion object {
        val emptyFList = object: FList<Any>() {
            public override val head: Any
                get() = throw UnsupportedOperationException();

            public override val tail: FList<Any>
                get() = this

            public override val empty: Boolean
                get() = true
        external internal  fun <T, S>  ztpom(a: MutableMap<Short, Byte>, b: ULong, c: S, d: Sequence<LinkedHashMap<FList<FList<FList<FList<Long>>?>>, Short>>): Boolean
inline infix public final fun   List<List<FList<FList<Boolean?>>>>?.aenvl(a: Pair<HashSet<Int>, Int>): Short = TODO()
}
    inline tailrec internal  fun   FList<Long>.mxsne(a: FList<FList<LinkedHashMap<UByte, UShort>>>?, b: FList<Char>, c: FList<Byte?>): Set<FList<FList<ULong>>>? = TODO()
public final fun  mcohd(a: List<Char?>, b: Collection<FList<Double>>, c: Long): ArrayList<Int> = TODO()
suspend public  fun  rxgjm(a: Long, b: Map<Collection<Long>, UInt?>, c: Array<UShort>): FList<Float> = TODO()
}

    operator fun plus(head: T): FList<T> = object : FList<T>() {
        override public val head: T
            get() = head

        override public val empty: Boolean
            get() = false

        override public val tail: FList<T>
            get() = this@FList
    infix   fun   FList<FList<Function1<FList<LinkedHashMap<FList<Char>, FList<UInt>>>, LinkedHashMap<Triple<String, UShort, Pair<Short, ULong>>, ULong>?>>>.ovoqg(a: FList<FList<Char>>?): FList<Long> = TODO()
external internal  fun  tyjcm(a: MutableMap<FList<UByte>, FList<ArrayDeque<Char?>>>): FList<UInt>
public  fun  twzxm(a: Function1<LinkedHashMap<FList<ULong>, FList<Long>>?, Char>, b: FList<Pair<FList<FList<Float>>, Float>?>?): Short = TODO()
}
fun  pmtbz(a: FList<Float>, b: T, c: T, d: ArrayDeque<Short>): Collection<Byte> = TODO()
}

public fun <T> emptyFList(): FList<T> = FList.emptyFList as FList<T>

public fun <T> FList<T>.reverse(where: FList<T> = emptyFList<T>()) : FList<T> =
        if(empty) where else tail.reverse(where + head)

operator fun <T> FList<T>.iterator(): Iterator<T> = object: Iterator<T> {
    private var cur: FList<T> = this@iterator

    override public fun next(): T {
        val res = cur.head
        cur = cur.tail
        return res
    }
    override public fun hasNext(): Boolean = !cur.empty
open fun  zidwh(a: Char?, b: Double): Int? = TODO()
}

fun box() : String {
  var r = ""
  for(s in (emptyFList<String>() + "O" + "K").reverse()) {
    r += s
  }
  return r
}