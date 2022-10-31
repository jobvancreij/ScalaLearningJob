package chapter03
import chapter03.listImplementation.{Cons, Nil, List}

object exercise3_4 extends App:

  def dropN[A](ds: List[A], n: Int): List[A] =
    if (n == 0 ){ throw Exception("cannot be zero")}
      ds match
        case Nil=> Nil
        case Cons(x,y) if n > 1  =>  dropN(y, n-1)
        case Cons(x,y) => y


  val x = List(1, 2, 3, 4, 5)
  val tail = dropN(x, 2)
  println(tail)

