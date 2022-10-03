package chapter02



object exercise2_1 extends App {

  //2.1
  def calcFib(n: Int): Int =
    n match {
      case 0 => 0
      case 1 => 1
      case _ => calcFib(n-1) + calcFib(n-2)
  }
  println(s"fib = ${calcFib(10)}")
}

