
package chapter03

import chapter03.exercise3_17.doubleToString
import chapter03.exercise3_18.map
import chapter03.exercise3_19.filter
import chapter03.listImplementation.*
import chapter03.listImplementation.List.*

import scala.annotation.tailrec


object exercise3_20 extends App:

  def flatMap[A,B](as: List[A])(f: A => (List[B])): List[B] =
     as match
      case Nil => Nil
      case Cons(x,y) =>  if (y == Nil) f(x) else appendLeft(f(x), flatMap(y)(f))


  println(flatMap(List(1,2,3))((x) => List(x,x,x,x,x)))