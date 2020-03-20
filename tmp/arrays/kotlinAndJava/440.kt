//File A.java
import kotlin.Metadata;

public final class A {
   public final int component1() {
      return 1;
   }

   public final int component2() {
      return 2;
   }
}


//File Main.kt


fun box() : String {
    val (a, b) = A()

    val run = {
        a
    }
    return if (run() == 1 && b == 2) "OK" else "fail"
}

