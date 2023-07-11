// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {


val thsdb: SuperFoo = SuperFoo()
val vilzv: Foo = Foo()
val vaozu: String = box()
val ecgxo: String = thsdb.bar()
val cjbts: String = thsdb.baz()
val gjhkl: Unit = vilzv.superFoo()
val qmnqs: Int = thsdb.fodj
thsdb.fodj = TODO()
}
// oldKlib.kt
// isKlib=true

open class SuperFoo {
    public fun bar(): String {
        if (this is Foo) {
            superFoo() // Smart cast
            return baz() // Cannot be cast
        }
        return baz()
    }

    public fun baz() = "OK"
private var fodj: LinkedHashSet<Short>  = LinkedHashSet<Short>(-3)
}

class Foo : SuperFoo() {
    public fun superFoo() {}
}

fun box(): String = Foo().bar()

// newKlib.kt
// isKlib=true

open class SuperFoo {
    public fun bar(): String {
        if (this is Foo) {
            superFoo() // Smart cast
            return baz() // Cannot be cast
        }
        return baz()
    }

    public fun baz() = "OK"
private var fodj: LinkedHashSet<Short>  = LinkedHashSet<Short>(-3)
}

class Foo : SuperFoo() {
    public fun superFoo() {}
}

abstract fun  box(): String



Combined output:
====================
Type mismatch: inferred type is LinkedHashSet<Short> /* = HashSet<Short> */ but Int was expected
Cannot access 'fodj': it is private in 'SuperFoo'
Cannot access 'fodj': it is private in 'SuperFoo'