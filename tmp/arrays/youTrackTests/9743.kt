// Original bug: KT-6958

fun f(x: Int, y: Int): Int = x * y
fun f(x: Int, y: Int, fn: Int.(Int)->Int): Int = x.fn(y)

fun main(args : Array<String>) {
  // renamed y -> it 
  println(f(2, 3) { this * it })
}
