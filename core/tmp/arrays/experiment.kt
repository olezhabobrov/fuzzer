open class Foo(val x: Int) {
    var y: String = "a"

    fun bar(x: Int): String { TODO() }
}

interface FooInterface

class Bar(y: String): Foo(3), FooInterface