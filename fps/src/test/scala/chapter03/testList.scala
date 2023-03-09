package chapter03

import org.scalatest.funsuite.AnyFunSuite

class testList extends AnyFunSuite:
  val emptyList = List()
  val oneItemList = List(1)
  val twoItemList = List(1,2)

  test("testWhile") {
    assertResult(Nil)(actual = emptyList.dropWhile((x) => x == 1))
    assertResult(Nil)(actual = oneItemList.dropWhile((x) => x == 1))
    assertResult(List(1))(actual = oneItemList.dropWhile((x) => x == 2))
    assertResult(List(2))(actual = twoItemList.dropWhile((x) => x == 1))
  }


  test("testDropN") {
      assertResult(Nil)(actual = emptyList.dropN(1))
      assertResult(Nil)(actual = oneItemList.dropN(10))
      assertResult(List(2))(actual = twoItemList.dropN(1))
    }

  test("testSetHead") {
    assertResult(List(1))(actual = emptyList.setHead(1))
    assertResult(List(1,1))(actual = oneItemList.setHead(1))
    assertResult(List(1,1,2))(actual = twoItemList.setHead(1))
  }


  test("testTail") {
    assertResult(Nil)(actual = emptyList.tail)
    assertResult(Nil)(actual = oneItemList.tail)
    assertResult(List(2))(actual = twoItemList.tail)
  }


  test("testSum") {
    assertResult(None)(actual = emptyList.sum)
    assertResult(Some(1))(actual = oneItemList.sum)
    assertResult(Some(3))(actual = twoItemList.sum)

  }

  test("testProduct") {
    assertResult(None)(actual = emptyList.product)
    assertResult(Some(1))(actual = oneItemList.product)
    assertResult(Some(2))(actual = twoItemList.product)

  }

  test("testfoldSum") {
    assertResult(0)(actual = emptyList.foldSum)
    assertResult(1)(actual = oneItemList.foldSum)
    assertResult(3)(actual = twoItemList.foldSum)

  }

  test("testfoldProduct") {
    assertResult(1)(actual = emptyList.foldProduct)
    assertResult(1)(actual = oneItemList.foldProduct)
    assertResult(2)(actual = twoItemList.foldProduct)

  }

  test("testInitList") {
    assertResult(Nil)(actual = emptyList.initList)
    assertResult(Nil)(actual = oneItemList.initList)
    assertResult(List(1))(actual = twoItemList.initList)

  }

