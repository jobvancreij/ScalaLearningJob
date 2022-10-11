package chapter02

object exercise2_5 extends App {

  def compose[A,B,C](f: B => C, g: A => B): A => C = {
    (a: A) => f(g(a))
  }
  println(compose((b: Int) => b+ 10, (a: Int) => a*2)(10))
}


