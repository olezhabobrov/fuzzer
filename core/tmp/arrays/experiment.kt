
class Foo {
    companion object {
        fun funCompanionObject(): String {

        }

    }

    inner class FooInner {
        companion object something {
            fun fooSomething(): Foo {}
        }
    }

}


