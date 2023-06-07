class FooClass(val x: Int) {
    class FooInner {

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