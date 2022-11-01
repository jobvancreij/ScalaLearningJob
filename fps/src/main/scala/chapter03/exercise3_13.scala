
package chapter03

import chapter03.listImplementation.*
import chapter03.listImplementation.List.*

import scala.annotation.tailrec


object exercise3_13 extends App:

  ???

  // GRAAG SAMEN DOEN

//  def foldLeftFromRight(as: List[Int], z: Int)(f: (List[Int],Int) => Int): Int =
//    //foldRight(as,z)((B,A) => A)
//    foldRight(as,z)(f())
//
//
//  println(foldLeftFromRight(List(1,2,3), 0)((x,y) => (x + y)))
//
////
////  def foldRight[A, B](as: List[A], z: B)(f: (A, B) => B): B =
////    as match
////      case Nil => z
////      case Cons(x, xs) => f(x, foldRight(xs, z)(f))
////
////
////  @tailrec
////  def foldLeft[A,B](as: List[A], z: B)(f: (B,A) => B): B =
////    as match
////      case Nil => z
////      case Cons(x,xs) =>foldLeft(xs,f(z,x))(f)
////
////
////



