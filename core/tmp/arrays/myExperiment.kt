fun box() {
    val variable = 5
    val foo = Foo(3)
    foo.foo()
}

val a = "a variable"

class Foo(val x: Int) {
     fun foo() {
         println("In foo")
     }
}