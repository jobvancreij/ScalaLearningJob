package chapter03

import chapter03.listImplementation.List.foldRight

import scala.annotation.tailrec

object listImplementation {

  sealed trait List[+A]:

    def sum3: Int =
      foldRight(this.asInstanceOf[List[Int]], 0)((_ + _))

  case object Nil extends List[Nothing]

  case class Cons[+A](head: A, tail: List[A]) extends List[A]

  object List {

    // Constructor utility function which constructs Nil with its element type A
    // Infererred by the compiler
    def empty[A]: List[A] =
      Nil

    def foldRight[A, B](as: List[A], z: B)(f: (A, B) => B): B =
      as match
        case Nil => z
        case Cons(x, xs) => f(x, foldRight(xs, z)(f))

    def getTail[A](ds: List[A]): List[A] =
      ds match
        case Nil=> Nil
        case Cons(x,y) => y

    def dropN[A](ds: List[A], n: Int): List[A] =
      if (n == 0 ){ throw Exception("cannot be zero")}
      ds match
        case Nil=> Nil
        case Cons(x,y) if n > 1  =>  dropN(y, n-1)
        case Cons(x,y) => y

    def getHead[A](ds: List[A]): List[A] =
      ds match
        case Nil => Nil
        case Cons(x,y) => Cons(x,Nil)

    @tailrec
    def foldLeft[A,B](as: List[A], z: B)(f: (B,A) => B): B =
      as match
        case Nil => z
        case Cons(x,xs) =>foldLeft(xs,f(z,x))(f)


    def sum2(as: List[Int]): Int =
      foldRight(as, 0)((_ + _))

    def product2(as: List[Int]): Int =
      foldRight(as, 1)((_ * _))

    def sum(ints: List[Int]): Int =
      ints match
        case Nil => 0
        case Cons(x, xs) => x + sum(xs)

    def product(ints: List[Int]): Int =
      ints match {
        case Nil => 0
        case Cons(x, y) => x + product(y)
      }

    def reverse[A](as: List[A]): List[A] =
        foldLeft(getTail(as),getHead(as))((x,y) => Cons(y, x))


    def apply[A](as: A*): List[A] =
      if (as.isEmpty) Nil
      else Cons(as.head, apply(as.tail: _*))

    def appendRight[A](as: List[A], z: List[A]): List[A] = // Doesn't work when I try A instead of List[A]
      foldRight(as,z)(Cons(_,_))

    def appendLeft[A](as: List[A], z: List[A]): List[A] =
      foldLeft(reverse(as), z)((x,y) => Cons(y,x))

    def map[A,B](as: List[A])(f: A => B): List[B] =
      as match
        case Nil => Nil
        case Cons(x,y) => Cons(f(x), if (y == Nil) Nil else map(y)(f))

    def concat[A](as: List[List[A]]): List[A] =
        foldLeft(as, List.empty[A])((z,a) => appendLeft(z,a ))

  }
}

