interface Foo {
    fun foo(): String
}


abstract class C: Foo {
    constructor(x: Int)
    constructor(y: String)

}