
package chapter03

import chapter03.listImplementation.*
import chapter03.listImplementation.List.*

import scala.annotation.tailrec


object exercise3_16 extends App:

  def addOne(as: List[Int]): List[Int] =
    println(as)
    as match {
      case Nil => Nil
      case Cons(x, Nil) => Cons(x + 1, Nil)
      case Cons(x, y) => Cons(x + 1, addOne(y))
    }

  println(addOne(List(1,2,3,4)))