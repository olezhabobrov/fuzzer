import ann.*

@Test1<ARG>(42)
@Test2<String, String>(38)
@Test3<String, C<String>>(Test1(39))
@Test4([Test3(Test1(40)), Test3(Test1(50)), Test3(Test1(60))])
//@Test5<ARG>(*arrayOf(Test3(Test1(70))), *arrayOf(Test3(Test1(80)))) <-- KT-45414
class O {
    fun test(): String = "O"
}