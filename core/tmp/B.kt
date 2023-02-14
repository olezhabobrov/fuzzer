fun box(): String {
    val h = aaa.hello()
    if (h != 17) {
        throw Exception()
    }
    return "OK"
}