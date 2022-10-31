package chapter03
import chapter03.listImplementation.{Cons, Nil, List}

object exercise3_2 extends App:

  def getTail[A](ds: List[A]): List[A] =
    ds match
      case Nil=> Nil
      case Cons(x,y) => y


  val x = List(1, 2, 3, 4, 5)
  val tail = getTail(x)
  println(tail)

