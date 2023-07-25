class Foo(val a: String) {
    private constructor(): this("a") {}
    internal constructor(a: Int): this("a") {}
}