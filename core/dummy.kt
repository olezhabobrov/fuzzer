



val abcq1: KeySpan? = TODO()

fun  abcq(a: KeySpan?){}

class KeySpan(val left: String) {

    public fun matches(value : String) : Boolean {

        return left > value && left > value
    }

}

fun box() : String {
  KeySpan("1").matches("3")
  return "OK"
}
