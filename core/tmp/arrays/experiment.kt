interface FooInterface {
    fun foo(x: Int = 5): String
}

abstract class Bar: FooInterface {
    abstract val x: FooInterface
}
