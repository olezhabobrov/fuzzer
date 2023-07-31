//old
interface Foo

class Bar: Foo
//new
interface Foo

class Bar
//main
fun main() {
    val x: Foo = Bar()
}

// compiling, though wtf
