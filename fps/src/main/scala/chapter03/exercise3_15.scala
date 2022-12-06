
package chapter03

import chapter03.listImplementation.*
import chapter03.listImplementation.List.*

import scala.annotation.tailrec


object exercise3_15 extends App:



  // foldLeft(as, (b: List[Int]) => List[Int])

      //(g,y) => b => Cons(b, Cons(y, Cons(x,y)))


  println(concat(List(List(1,2,3), List(1,2,3))))
//  println(concat(List(List(1,2,3), List(1,2,3))))