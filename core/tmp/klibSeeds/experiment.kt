abstract class Foo {
    abstract fun foo()
}

interface Bar {
    val x: Foo

    fun foo(): String
}

