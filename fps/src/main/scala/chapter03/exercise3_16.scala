
package chapter03

import chapter03.listImplementation.*
import chapter03.listImplementation.List.*

import scala.annotation.tailrec


object exercise3_16 extends App:

  def append[A](as: List[A], a: A): List[A] =
    reverse(Cons(a, reverse(as)))


  def addOne(as: List[Int]): List[Int] =
    foldLeft(as,List.empty[Int])((a,i) => append(a, i+1))

  println(addOne(List(1,2,3,4)))