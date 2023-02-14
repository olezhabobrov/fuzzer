import ann.*

@Test1<ARG>(24)
@Test2<String, String>(83)
@Test3<String, C<String>>(Test1(93))
@Test4([Test3(Test1(44)), Test3(Test1(55)), Test3(Test1(66))])
//@Test5<ARG>(*arrayOf(Test3(Test1(77))), *arrayOf(Test3(Test1(88)))) <-- KT-45414
class K {
    fun test(): String = "K"
}

fun box(): String = O().test() + K().test()