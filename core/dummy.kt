package foo
import kotlin.*




val abcq1: Short = TODO()

fun  abcq(a: Short){}

// TARGET_BACKEND: JVM
// IGNORE_BACKEND: ANDROID

// FULL_JDK

class WithNative {
    external fun foo()
}

fun box(): String {
    try {
        WithNative().foo()
        return "Link error expected"
    }
    catch (e: java.lang.UnsatisfiedLinkError) {
        return "OK"
    }
}
