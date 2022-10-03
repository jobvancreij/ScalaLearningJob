package chapter02

object exercise2_4 extends App {

  def test(a: String)(b: String): String = {
    a + b
  }


  def uncurry[A,B,C](f: A => B => C): (A, B) => C = {
    (a: A, b: B) => f(a)(b)
  }
  println(uncurry((a: String) => (b: String) => a==b)("a","a"))
  println(uncurry(test)("a","b"))

}

