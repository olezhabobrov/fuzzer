class FinalClass(val name: String) {
    fun sayHello() {}
}

open class OpenClass(val id: Int) {
    open fun displayId() {}
}

abstract class AbstractClass {
    abstract fun abstractMethod(): String
}

interface MyInterface {
    fun interfaceMethod(): String
}

object MyObject {
    const val CONSTANT = "I'm a constant in an object."

    fun objectMethod() {}
}

class ChildClass(id: Int) : OpenClass(id), MyInterface {
    override fun interfaceMethod(): String {
        TODO()
    }

    override fun displayId() {
        TODO()
    }
}

class ConcreteClass : AbstractClass() {
    override fun abstractMethod(): String {
        TODO()
    }
}
