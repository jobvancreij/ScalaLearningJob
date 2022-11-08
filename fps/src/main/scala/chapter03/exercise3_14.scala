
package chapter03

import chapter03.listImplementation.*
import chapter03.listImplementation.List.*

import scala.annotation.tailrec


object exercise3_14 extends App:

//  def appendLeft[A](as: List[A], z: List[A]): List[A] =
//      foldLeft(z,as)((x,y) => (y, Cons(x,Nil)))
//
//      //foldLeft(as, z)((x,y) => Cons()

  def appendRight[A](as: List[A], z: List[A]): List[A] = // Doesn't work when I try A instead of List[A]
    foldRight(as,z)(Cons(_,_))
    
  // Is append left possible??
  println(appendRight(List(1,2,3), List(100)))

  def appendLeft[A](as: List[A], z: List[A]): List[A] =
    foldLeft(reverse(as), z)((x,y) => Cons(y,x))

  println(appendLeft(List(1,2,3), List(100)))
