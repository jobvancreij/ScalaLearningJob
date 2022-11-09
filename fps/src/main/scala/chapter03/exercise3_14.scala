
package chapter03

import chapter03.listImplementation.*
import chapter03.listImplementation.List.*

import scala.annotation.tailrec


object exercise3_14 extends App:

//  def appendLeft[A](as: List[A], z: List[A]): List[A] =
//      foldLeft(z,as)((x,y) => (y, Cons(x,Nil)))
//
//      //foldLeft(as, z)((x,y) => Cons()


  // Is append left possible??
  println(appendRight(List(1,2,3), List(100)))

  println(appendLeft(List(1,2,3), List(100,101,102)))
