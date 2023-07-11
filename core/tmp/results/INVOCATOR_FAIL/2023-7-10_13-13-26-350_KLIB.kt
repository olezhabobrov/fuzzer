// INVOCATOR_FAIL
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]

// files
// main.kt
// isKlib=false
fun main() {


val prfwp: Outer.Nested = Outer.Nested()
val ziyex: Outer.Companion = Outer.Companion
val lktmv: String = box()
val aeckn: String = prfwp.foo("kixvu")

}
// oldKlib.kt
// isKlib=true
private class Outer  {

    class Nested{
        fun foo(s: String) = s.extension()
    }

    companion object {
        private fun String.extension(): String = this
    }

}

fun box(): String {
    return Outer.Nested().foo("OK")
}
// newKlib.kt
// isKlib=true
private class Outer  {

    private class Nested  {

        fun foo(s: String) = s.extension()
    
}

    companion object {
        private fun String.extension(): String = this
    }

}

fun box(): String {
    return Outer.Nested().foo("OK")
}


Combined output:
====================
Cannot access 'Outer': it is private in file
Cannot access 'Nested': it is public in 'Outer'
Cannot access 'Outer': it is private in file
Cannot access 'Outer': it is private in file
Cannot access 'Outer': it is private in file
Cannot access 'Companion': it is public in 'Outer'
Cannot access 'Outer': it is private in file
Cannot access 'Outer': it is private in file
Cannot access 'Outer': it is private in file