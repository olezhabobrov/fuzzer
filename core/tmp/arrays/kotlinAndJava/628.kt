//File MySet.java
import java.util.HashSet;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class MySet extends HashSet {
   public boolean remove(@NotNull Integer element) {
      return super.remove(element);
   }
}


//File Main.kt


fun box(): String {
    val a = MySet()
    a.add(1)
    a.add(2)
    a.add(3)

    if (!a.remove(1)) return "fail 1"
    if (a.remove(1)) return "fail 2"
    if (a.contains(1)) return "fail 3"

    return "OK"
}

