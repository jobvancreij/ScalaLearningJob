package chapter03
import chapter03.listImplementation.{Cons, Nil, List}

object exercise3_5 extends App:

  def dropWhile[A](ds: List[A], f: A => Boolean): List[A] =
      ds match
        case Nil=> Nil
        case Cons(x,y) if f(x) =>  dropWhile(y, f)
        case Cons(x,y) =>  Cons(x,dropWhile(y, f))

  val x = List(1, 2, 3, 4, 5)
  val tail = dropWhile(x, (x) => x == 2)
  println(tail)
