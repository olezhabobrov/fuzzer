// IGNORE_BACKEND_FIR: JVM_IR
// KJS_WITH_FULL_RUNTIME
// Auto-generated by org.jetbrains.kotlin.generators.tests.GenerateRangesCodegenTestData. DO NOT EDIT!
// WITH_RUNTIME


const val MaxI = Int.MAX_VALUE
const val MinI = Int.MIN_VALUE
const val MaxB = Byte.MAX_VALUE
const val MinB = Byte.MIN_VALUE
const val MaxS = Short.MAX_VALUE
const val MinS = Short.MIN_VALUE
const val MaxL = Long.MAX_VALUE
const val MinL = Long.MIN_VALUE
const val MaxC = Char.MAX_VALUE
const val MinC = Char.MIN_VALUE

fun box(): String {
    val list1 = ArrayList<Int>()
    for (i in MaxI..MinI step 1) {
        list1.add(i)
        if (list1.size > 23) break
    }
    if (list1 != listOf<Int>()) {
        return "Wrong elements for MaxI..MinI step 1: $list1"
    }

    val list2 = ArrayList<Int>()
    for (i in MaxB..MinB step 1) {
        list2.add(i)
        if (list2.size > 23) break
    }
    if (list2 != listOf<Int>()) {
        return "Wrong elements for MaxB..MinB step 1: $list2"
    }

    val list3 = ArrayList<Int>()
    for (i in MaxS..MinS step 1) {
        list3.add(i)
        if (list3.size > 23) break
    }
    if (list3 != listOf<Int>()) {
        return "Wrong elements for MaxS..MinS step 1: $list3"
    }

    val list4 = ArrayList<Long>()
    for (i in MaxL..MinL step 1) {
        list4.add(i)
        if (list4.size > 23) break
    }
    if (list4 != listOf<Long>()) {
        return "Wrong elements for MaxL..MinL step 1: $list4"
    }

    val list5 = ArrayList<Char>()
    for (i in MaxC..MinC step 1) {
        list5.add(i)
        if (list5.size > 23) break
    }
    if (list5 != listOf<Char>()) {
        return "Wrong elements for MaxC..MinC step 1: $list5"
    }

    return "OK"
}
