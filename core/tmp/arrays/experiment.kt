interface Foo {
    fun foo(): String
}

abstract class AbstractFoo: Foo {
    abstract fun bar(x: Int)

    open fun barOpen() {}
}

