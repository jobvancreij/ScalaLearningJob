package chapter03

import chapter03.listImplementation.*
import chapter03.listImplementation.List.*

object exercise3_3 extends App:

  def setHead[A](ds: List[A], newVal: A): List[A] =
    ds match
      case Nil=> Nil
      case Cons(x,y) => Cons(newVal, y)



  val x = List(1, 2, 3, 4, 5)
  val tail = setHead(x, 4)
  println(tail)

