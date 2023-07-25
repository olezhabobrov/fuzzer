open class Foo(val x: Int) {
    var y: String = "a"
}

interface FooInterface

class Bar(y: String): Foo(3), FooInterface