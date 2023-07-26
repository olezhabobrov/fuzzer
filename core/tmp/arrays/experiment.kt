class Foo(y: Int, z: String = "aboba") {
    fun bar(y = 5) {}
}

fun foo(x: Foo = Foo(5))