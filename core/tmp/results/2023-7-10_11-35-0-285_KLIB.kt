// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {


val ehqhd: C = C()
val thkav: Foo = Foo()
val jdizv: Bar = Bar()

val nodau: String = ehqhd.todo
val ddyfe: String = ehqhd.uninitializedVal
val cosvv: String = ehqhd.uninitializedVar
ehqhd.uninitializedVar = TODO()
val dssbk: Sequence = ehqhd.xsxw
val xmalt: String = thkav.uninitializedVal
val csxlu: String = thkav.uninitializedVar
thkav.uninitializedVar = TODO()
val mecbj: Int = jdizv.initializedVal
val btrll: String = jdizv.uninitializedVal
val yehtm: String = jdizv.uninitializedVar
jdizv.uninitializedVar = TODO()
}
// oldKlib.kt
// isKlib=true
// WITH_RUNTIME
// KT-44496

open class C  {

    val todo: String = TODO()

    val uninitializedVal: String

    var uninitializedVar: String

protected val xsxw: Sequence<Bar>  
get() = TODO()
}

class Foo {
    init {
        TODO()
    }

    val uninitializedVal: String

    var uninitializedVar: String
}

class Bar {
    val initializedVal = 43

    init {
        TODO()
    }

    val uninitializedVal: String

    var uninitializedVar: String
}

 

// newKlib.kt
// isKlib=true
// WITH_RUNTIME
// KT-44496

open class C  {

    val todo: String = TODO()

    val uninitializedVal: String

    val uninitializedVar : String

protected val xsxw: Sequence<Bar>  
get() = TODO()
}

class Foo {
    init {
        TODO()
    }

    val uninitializedVal: String

    var uninitializedVar: String
}

class Bar {
    val initializedVal = 43

    init {
        TODO()
    }

    val uninitializedVal: String

    var uninitializedVar: String
}

 



Combined output:
====================
One type argument expected for interface Sequence<out T>
Cannot access 'xsxw': it is protected in 'C'