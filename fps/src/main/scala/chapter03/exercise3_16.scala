
package chapter03

import chapter03.exercise3_17.doubleToString
import chapter03.listImplementation.*
import chapter03.listImplementation.List.*

import scala.annotation.tailrec


object exercise3_16 extends App:

  def addOne(as: List[Int]): List[Int] =
    as match {
      case Nil => Nil
      case Cons(x, y) => Cons(x+1, if (y == Nil) Nil else addOne(y))

    }

  println(addOne(List(1,2,3,4)))