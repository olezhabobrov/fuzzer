class Bar(x: Int, y: String): Foo {
    constructor(z: Bar): this(z.x, z.y) {}
}

