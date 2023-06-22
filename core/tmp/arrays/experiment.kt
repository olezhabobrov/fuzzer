abstract class Foo: BarInterface, Bar2 {
    val x = 5

    val y: Int
        get() = TODO()

    abstract val z: String

    abstract fun dada()
}

interface Bar2

interface BarInterface {
    fun foo(): String

    fun too(x: Int) = x + 5
}