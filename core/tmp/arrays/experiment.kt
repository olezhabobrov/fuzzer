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

fun blablabla(x: Int, y: FooClass2): FooInterface {}

class FooClass2(val x : Int)

interface FooInterface

object FooObject {
    fun fooObjecvtFun(): Int {

    }
}
