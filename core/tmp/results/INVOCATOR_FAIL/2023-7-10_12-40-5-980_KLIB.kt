// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {


val wpuar: Foo = Foo("yhcbq")
val pyheq: Foo = Foo(-91)
val yskvo: Foo = Foo('琒')
val ivyim: Foo = Foo(52, 40)
val jcceu: Foo = Foo(9)
val bphvc: Xclqe = Xclqe
val ijvod: Ezbpr = object: Ezbpr {
}
val xwlkx: Dvhnk = Dvhnk()
val zcydr: Mkdqx = Mkdqx()
val jgdip: Foo.Qiwrm = Foo.Qiwrm
val tnqzt: String = box()
val gdsrj: String = global
global = TODO()
val hsexp: Pair = zcydr.iwon
zcydr.iwon = TODO()
}
// oldKlib.kt
// isKlib=true
// !LANGUAGE: +InlineClasses

var global  = "wrong"

inline class Foo(val x: Int) {
    constructor(y: String) : this(y.length)

    constructor(z: Long) : this(z.toInt() + 1)

    @Suppress("SECONDARY_CONSTRUCTOR_WITH_BODY_INSIDE_INLINE_CLASS")
    constructor(other: Char) : this(other.toInt().toString()) {
        global = "OK"
    }

    constructor(a: Int, b: Int) : this(a + b)
object Qiwrm
}

fun box(): String {
    var f  = Foo("42")
    if (f.x != 2) return "Fail 1: ${f.x}"

    f = Foo(43L)
    if (f.x != 44) return "Fail 2: ${f.x}"

    f = Foo('a')
    if (f.x != 2) return "Fail 3: ${f.x}"

    f = Foo(1, 2)
    if (f.x != 3) return "Fail 4: ${f.x}"

    return global
}
object Xclqe
interface Ezbpr
class Dvhnk
class Mkdqx{
public var iwon: Pair<Foo, Function1<UByte, Mkdqx>>?  = Pair<Foo, Function1<UByte, Mkdqx>>(Foo(-28, 20), {a: UByte -> Mkdqx()})
}
// newKlib.kt
// isKlib=true
// !LANGUAGE: +InlineClasses

var global  = "wrong"

inline class Foo(val x: Int) {
    constructor(y: String) : this(y.length)

    constructor(z: Long) : this(z.toInt() + 1)

    @Suppress("SECONDARY_CONSTRUCTOR_WITH_BODY_INSIDE_INLINE_CLASS")
    constructor(other: Char) : this(other.toInt().toString()) {
        global = "OK"
    }

    constructor(a: Int, b: Int = 73): this(a + b)
object Qiwrm
}

fun box(): String {
    var f  = Foo("42")
    if (f.x != 2) return "Fail 1: ${f.x}"

    f = Foo(43L)
    if (f.x != 44) return "Fail 2: ${f.x}"

    f = Foo('a')
    if (f.x != 2) return "Fail 3: ${f.x}"

    f = Foo(1, 2)
    if (f.x != 3) return "Fail 4: ${f.x}"

    return global
}
object Xclqe
interface Ezbpr
class Dvhnk
class Mkdqx{
public var iwon: Pair<Foo, Function1<UByte, Mkdqx>>?  = Pair<Foo, Function1<UByte, Mkdqx>>(Foo(-28, 20), {a: UByte -> Mkdqx()})
}


Combined output:
====================
2 type arguments expected for class Pair<out A, out B>