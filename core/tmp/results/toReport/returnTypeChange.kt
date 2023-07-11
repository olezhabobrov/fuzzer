// found manually

// oldKlib

class Foo {
    val a = 42
}

fun foo(): Foo {
    return Foo()
}

// newKlib

class Foo {
    val a = 42
}

fun foo(): String {
    return "a"
}

//main

fun main() {
    foo().a
}

// Doesn't fail, returns size of String