package chapter02

object exercise2_3 extends App {
  def testfunc(x: String, z: String): Boolean = {
    (x,z) == ("a","b")
  }

  def curry[A,B,C](f: (A, B) => C): A => (B => C) = {
    (a: A) => (b: B) => f(a,b)
  }

  println(curry((a,b) => (a,b) == ("a","z"))("a")("z"))
  println(curry(testfunc)("a")("z"))

}

