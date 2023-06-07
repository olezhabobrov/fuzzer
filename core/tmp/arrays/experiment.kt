class FooClass(val x: FooClass2) {

    private constructor(x: Int)

    constructor(y: String)

    constructor(y: String, x: FooClass2)

    inner class FooInner {
        constructor(x: FooClass)
    }

    fun fooFunc(val x: FooClass2) {

    }
}

class FooClass2(val x : Int)

interface FooInterface

object FooObject

fun main() {
    FooClass2(10)
    val a = FooClass2(-1)
    var b = FooClass2(123456)
}