import kotlin.*




val abcq1: ULong = TODO()

fun  abcq(a: ULong){}

class FinalClass(val name: String?): AbstractClass(){

    fun sayHello() {}

override fun  abstractMethod(): String { TODO() }

object Tlqej: AbstractClass(), MyInterface{
override fun  abstractMethod(): String { TODO() }

override fun  interfaceMethod(): String { TODO() }


}
}

open class OpenClass(val id: Int?) {
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

open class ConcreteClass: AbstractClass(){

    override fun abstractMethod(): String {
        TODO()
    }

}
object Nzkrr: MyInterface{
override fun  interfaceMethod(): String { TODO() }


}