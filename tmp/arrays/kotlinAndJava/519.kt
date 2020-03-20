//File E.java
import kotlin.Metadata;

public enum E {
   I;
}


//File Main.kt


fun box(): String {
    val i = (E::name).get(E.I)
    if (i != "I") return "Fail $i"
    val n = (E::ordinal).get(E.I)
    if (n != 0) return "Fail $n"
    return "OK"
}

