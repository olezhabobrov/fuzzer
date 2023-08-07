class A(val a: A?) {
    constructor(x: Int): this(null)
}