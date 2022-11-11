
package chapter03

import chapter03.exercise3_17.doubleToString
import chapter03.listImplementation.*
import chapter03.listImplementation.List.*

import scala.annotation.tailrec


object exercise3_19 extends App:

  def filter[A](as: List[A])(f: A => Boolean): List[A] =
    as match {
      case Nil => Nil
      case Cons(x,y) => if  (!f(x)) Cons(x,filter(y)(f))  else filter(y)(f)
    }

  println(filter(List(1,2,3))((x) => x ==2))
  println(filter(List(1,2,3))((x) => x ==1))