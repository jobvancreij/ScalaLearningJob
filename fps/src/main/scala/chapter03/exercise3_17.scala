
package chapter03

import chapter03.listImplementation.*
import chapter03.listImplementation.List.*

import scala.annotation.tailrec


object exercise3_17 extends App:

  def doubleToString(as: List[Double]): List[String] =
    as match {
      case Nil => Nil
      case Cons(x, y) => Cons(x.toString, if (y == Nil) Nil else doubleToString(y))
    }

  println(doubleToString(List(1,2,3,4)))