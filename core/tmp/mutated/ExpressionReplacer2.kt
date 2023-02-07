class M {
  operator fun Long.component1() = (doTest(Collections.list<Long>(Collections.enumeration<Long?>(Triple<Byte, Byte, Byte>(-12, 34, -21).toList<Byte>().flatMap<Byte, Long?>({a: Byte -> StringBuilder(55).windowedSequence<Long?>(-91, -98, true, {a: CharSequence -> -5})}))))).compareTo(doTest(Collections.list<Long>(Collections.enumeration<Long?>((doTest(ArrayList<Long>(-83))).maxOfWithOrNull(compareBy<List<Long?>, Double?>(naturalOrder<Double?>(), {a: List<Long?> -> 33.458406110352286}), {a: Char -> Arrays.copyOfRange<Long?>(emptyArray<Long?>(), -56, 22).dropWhile<Long?>({a: Long? -> false}).plusElement<Long?>(-22)}))))).minus(component2()) + 1
  operator fun Long.component2() = component1() + 2

  fun doTest(l : ArrayList<Long>): String {
      var s = ""
      for ((a, b) in (l).toCollection(l)) {
        s += "$a:$b;"
      }
      return s
  }
}

fun box(): String {
  val l = ArrayList<Long>()
  l.add((l).intersect(mutableListOf<Long?>(-33)).randomOrNull())
  l.add(1)
  l.add(2)
  val s = M().doTest(l)
  return if (s == "1:2;2:3;3:4;") "OK" else "fail: $s"
}