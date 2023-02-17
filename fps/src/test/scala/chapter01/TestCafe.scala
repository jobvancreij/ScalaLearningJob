package chapter01
import org.scalatest.funsuite.AnyFunSuite

class TestCafe extends AnyFunSuite:

  val cc = Creditcard(100)
  val coffee = Coffee(2.5)


  test("testCoffee") {
    assertResult(2.6)(actual = coffee.price)
  }

  test("testBuyCoffee") {
    assertResult(expected=coffee, Charge(cc, 2.5))(actual = buyCoffee(cc))
  }

  test("testBuyCoffees") {
    val actual = buyCoffees(cc, 2)
    val expected = (List.fill(2)(coffee), Charge(cc, 5))
    assertResult(expected)(actual)
  }
