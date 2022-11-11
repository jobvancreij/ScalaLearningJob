
package chapter03

import chapter03.listImplementation.*
import chapter03.listImplementation.List.*

import scala.annotation.tailrec


object exercise3_15 extends App:

  def concat[A](as: List[List[A]]): List[A] =
    foldLeft(as, List.empty[A])((z,a) => appendLeft(z,a ))


  // foldLeft(as, (b: List[Int]) => List[Int])

      //(g,y) => b => Cons(b, Cons(y, Cons(x,y)))


  println(concat(List(List(1,2,3), List(1,2,3))))
//  println(concat(List(List(1,2,3), List(1,2,3))))