// !LANGUAGE: +ProperVisibilityForCompanionObjectInstanceField
// IGNORE_BACKEND_FIR: JVM_IR
// FILE: lambdaInPropertyInitializer.kt
import c.C

fun box() = C().test()

// FILE: a.kt
package a

open class A {
    protected companion object {
        fun getO() = "O"
    }
}

// FILE: b.kt
package b

import a.A

open class B : A() {
    protected companion object {
        fun getK() = "K"
    }
}

// FILE: c.kt
package c

import b.B

class C : B() {
    val test = { getO() + getK() }
}
