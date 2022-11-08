//
//package chapter03
//
//import chapter03.listImplementation.*
//import chapter03.listImplementation.List.*
//
//import scala.annotation.tailrec
//
//
//object exercise3_15 extends App:
//
//  def concat[A](as: List[List[Int]]): List[Int] =
//    as match
//      case Nil => Nil
//      case Cons(x: List[A],y: List[A]) => Cons(concat(y),concat(x))
//      case Cons(x: List[A],y) => Cons(y,concat(x))
//      case Cons(x,y: List[A]) => Cons(x,concat(y))
//      case Cons(x,y)=> Cons(x,y)
//
//
//
//  println(concat(List(List(1,2,3), List(1,2,3))))