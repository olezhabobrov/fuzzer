// IGNORE_BACKEND_FIR: JVM_IR
// KJS_WITH_FULL_RUNTIME
// Auto-generated by org.jetbrains.kotlin.generators.tests.GenerateRangesCodegenTestData. DO NOT EDIT!
// WITH_RUNTIME



fun box(): String {
    val list1 = ArrayList<UInt>()
    val range1 = (5u..3u).reversed()
    for (i in range1) {
        list1.add(i)
        if (list1.size > 23) break
    }
    if (list1 != listOf<UInt>()) {
        return "Wrong elements for (5u..3u).reversed(): $list1"
    }

    val list2 = ArrayList<UInt>()
    val range2 = (5u.toUByte()..3u.toUByte()).reversed()
    for (i in range2) {
        list2.add(i)
        if (list2.size > 23) break
    }
    if (list2 != listOf<UInt>()) {
        return "Wrong elements for (5u.toUByte()..3u.toUByte()).reversed(): $list2"
    }

    val list3 = ArrayList<UInt>()
    val range3 = (5u.toUShort()..3u.toUShort()).reversed()
    for (i in range3) {
        list3.add(i)
        if (list3.size > 23) break
    }
    if (list3 != listOf<UInt>()) {
        return "Wrong elements for (5u.toUShort()..3u.toUShort()).reversed(): $list3"
    }

    val list4 = ArrayList<ULong>()
    val range4 = (5uL..3uL).reversed()
    for (i in range4) {
        list4.add(i)
        if (list4.size > 23) break
    }
    if (list4 != listOf<ULong>()) {
        return "Wrong elements for (5uL..3uL).reversed(): $list4"
    }

    return "OK"
}
