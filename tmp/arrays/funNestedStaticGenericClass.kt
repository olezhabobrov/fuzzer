// TARGET_BACKEND: JVM
// MODULE: lib
// FILE: J.java

public class J<T> {
    public static class Inner<S> {
        protected static String protectedFun() {
            return "OK";
        }
    }
}

// MODULE: main(lib)
// FILE: 1.kt

class Derived : J.Inner<String>() {
    fun test(): String {
        return J.Inner.protectedFun()!!
    }
}

fun box(): String {
    return Derived().test()
}
