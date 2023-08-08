
class C(val x: Pair<Int, String>): Foo {
    constructor(x: Int): this(x to "a")
    constructor(y: String): this(5 to y)

}