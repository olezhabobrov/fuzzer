//File A.java
import java.util.HashSet;
import kotlin.Metadata;

public final class A extends HashSet {
}


//File Main.kt


fun box(): String {
    val a = A()
    val b = A()

    a.iterator()

    a.add(0L)
    a.remove(0L)

    a.addAll(b)
    a.removeAll(b)
    a.retainAll(b)
    a.clear()

    return "OK"
}

