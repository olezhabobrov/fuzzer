package ann

annotation class Test1<T>(val x: Int)

annotation class Test2<T1 : Any, T2>(val x: Int = 0)

interface I<T>

annotation class Test3<T1, T2 : I<T1>>(val x: Test1<I<T2>>)

class C<T> : I<T>

annotation class Test4(val x: Array<Test3<Int, C<Int>>>)

class ARG

annotation class Test5<T>(vararg val xs: Test3<T, C<T>>)