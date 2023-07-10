// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {


val wdvvs: Udkdo = Udkdo()
val kqefe: Int = facWhile(77)
val oiiau: Int = facBreak(-100)
val uhzkn: Int = facDoWhile(-34)
val sulst: String = box()
val vlfyy: UByte = wdvvs.xfoi
wdvvs.xfoi = TODO()
}
// oldKlib.kt
// isKlib=true
// WITH_RUNTIME

import kotlin.test.assertEquals

fun facWhile(i: Int): Int {
    var count  = 1;
    var result = 1;
    while(count < i) {
        count = count + 1;
        result = result * count;
    }
    return result;
}

fun facBreak(i: Int): Int {
    var count = 1;
    var result = 1;
    while(true) {
        count = count + 1;
        result = result * count;
        if (count == i) break;
    }
    return result;
}

fun  facDoWhile(i: Int): Int {
    var count = 1;
    var result  = 1;
    do {
        count = count + 1;
        result = result * count;
    } while(count != i);
    return result;
}

fun box(): String {
    assertEquals(6, facWhile(3))
    assertEquals(6, facBreak(3))
    assertEquals(6, facDoWhile(3))
    assertEquals(120, facWhile(5))
    assertEquals(120, facBreak(5))
    assertEquals(120, facDoWhile(5))
    return "OK"
}
class Udkdo{
private var xfoi: UByte  
get() = TODO()
set(value) = TODO()
}
// newKlib.kt
// isKlib=true
// WITH_RUNTIME

import kotlin.test.assertEquals

fun facWhile(i: Int): Int {
    var count  = 1;
    var result = 1;
    while(count < i) {
        count = count + 1;
        result = result * count;
    }
    return result;
}

fun facBreak(i: Int): Int {
    var count = 1;
    var result = 1;
    while(true) {
        count = count + 1;
        result = result * count;
        if (count == i) break;
    }
    return result;
}

fun  facDoWhile(i: Int): Int {
    var count = 1;
    var result  = 1;
    do {
        count = count + 1;
        result = result * count;
    } while(count != i);
    return result;
}

fun box(): String {
    assertEquals(6, facWhile(3))
    assertEquals(6, facBreak(3))
    assertEquals(6, facDoWhile(3))
    assertEquals(120, facWhile(5))
    assertEquals(120, facBreak(5))
    assertEquals(120, facDoWhile(5))
    return "OK"
}
class Udkdo{
private var xfoi: UByte  
get() = TODO()
set(value) = TODO()
private var pdkt: Udkdo?  = Udkdo()
get() = Udkdo()
private set
}


Combined output:
====================
Cannot access 'xfoi': it is private in 'Udkdo'
Cannot access 'xfoi': it is private in 'Udkdo'