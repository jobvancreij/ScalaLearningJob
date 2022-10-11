package chapter03

import chapter03.exercise3_1.List.sum
import chapter03.exercise3_2.List

object exercise3_4 extends App {
  sealed trait List[+A]
  case object Nil extends List[Nothing]
  case class Cons[+A](head: A, tail: List[A]) extends List[A]

  object List {
    def sum(ints: List[Int]): Int = ints match {
      case Nil => 0
      case Cons(x, xs) => x + sum(xs)
    }

    def product(ds: List[Double]): Double = ds match {
      case Nil => 1.0
      case Cons(0.0, _) => 0.0
      case Cons(x, xs) => x * product(xs)
    }
    def dropN[A](ds: List[A], n: Int): List[A] = {
      if (n == 0 ){ throw Exception("cannot be zero")}
        ds match {
          case Nil=> Nil
          case Cons(x,y) if n > 1  =>  dropN(y, n-1)
          case Cons(x,y) => y
        }
      }




    def apply[A](as: A*): List[A] =
      if (as.isEmpty) Nil
      else Cons(as.head, apply(as.tail: _*))

  }

  val x = List(1, 2, 3, 4, 5)
  val tail = List.dropN(x, 10)
  println(tail)}

