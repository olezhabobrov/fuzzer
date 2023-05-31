//NativeCompiler ver 1.8.0
//not failed with arguments: result:[-p, library, -l, projectTmp/kt14243_2322498621459, -o, projectTmp/kt14243_23212139125262, projectTmp/kt14243_2321.kt, projectTmp/kt14243_231.kt] klib:result:[-p, library, -Xpartial-linkage, -o, projectTmp/kt14243_2322498621459, projectTmp/kt14243_2322.kt]
//failed with arguments: result:[-p, library, -l, projectTmp/kt14243_2322498621459, -o, projectTmp/kt14243_23212139125262, projectTmp/kt14243_2321.kt, projectTmp/kt14243_231.kt] klib:result:[-p, library, -Xpartial-linkage, -o, projectTmp/kt14243_2322498621459, projectTmp/kt14243_2322.kt]
//failed with arguments: result:[-p, library, -Xpartial-linkage, -o, projectTmp/kt14243_2322498621459, projectTmp/kt14243_2322.kt]
//not failed with arguments: result:[-p, library, -l, projectTmp/kt14243_2322498621459, -Xpartial-linkage, -o, projectTmp/kt14243_23212139125262, projectTmp/kt14243_2321.kt, projectTmp/kt14243_231.kt] klib:result:[-p, library, -Xpartial-linkage, -o, projectTmp/kt14243_2322498621459, projectTmp/kt14243_2322.kt]

// files
// kt14243_2321.kt
// isKlib=false




open class ZImpl : Z<String>


// kt14243_2322.kt
// isKlib=true




// JVM_TARGET: 1.8

interface Z<T> {
    fun test(p: T): T {
        return p!!
    }
}


// kt14243_231.kt
// isKlib=false




// !JVM_DEFAULT_MODE: all

// TARGET_BACKEND: JVM

// WITH_RUNTIME

open class ZImpl2 : Z<String>, ZImpl()

class ZImpl3 : ZImpl2() {

    override fun test(p: String): String {
        return super.test(p)
    }
private var xvhu: LinkedHashMap<ZImpl2, UInt?>  = LinkedHashMap<ZImpl2, UInt?>()
get() = LinkedHashMap<ZImpl2, UInt?>()
private set
}

fun box(): String {for (b in HashSet<Float?>(-6, -42.177643f)) { 
 
}
    return ZImpl3().test("OK")
}



// STACKTRACE:
// 