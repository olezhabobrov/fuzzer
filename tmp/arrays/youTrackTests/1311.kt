// Original bug: KT-32358

  class MyChild {
    val nullableString: String? = null
    val notNull = ""
  }

  class MyParent {
    val child: MyChild? = MyChild()
  }

  fun myFun() {
    val myParent = MyParent()
    myParent.child?.nullableString ?: run { return }

    myParent.child.notNull   // <- No smart cast in plugin
  }
