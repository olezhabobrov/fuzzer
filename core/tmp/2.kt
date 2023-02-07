    enum class A {
    override var value: String
    fun f() = value
}

data class B : A() {
    init {
        value = "OK"
    }
}