abstract class Foo {
    val x = 5

    val y: Int
        get() = TODO()

    abstract val z: String

    abstract fun dada()
}

interface BarInterface {
    fun foo(): String

    fun too(x: Int) = x + 5
}