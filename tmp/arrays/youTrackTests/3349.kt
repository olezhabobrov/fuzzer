// Original bug: KT-36194

fun foo(list: List<String>) {
    if (true) {
        println(list)
    }

    // printing...
    println("hi")

    for (l in list) {
        println(l)
    }

    // printing...
    println("hi")
}
