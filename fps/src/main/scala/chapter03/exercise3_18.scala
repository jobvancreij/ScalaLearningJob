
package chapter03

import chapter03.exercise3_17.doubleToString
import chapter03.listImplementation.*
import chapter03.listImplementation.List.*

import scala.annotation.tailrec


object exercise3_18 extends App:

  def map[A,B](as: List[A])(f: A => B): List[B] =
    as match {
      case Nil => Nil
      case Cons(x,y) => Cons(f(x), if (y == Nil) Nil else map(y)(f))
    }

  println(map(List(1,2,3))((x) => x *2))
  println(map(List(1,2,3))((x) => x / 2))