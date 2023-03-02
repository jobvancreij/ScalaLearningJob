package chapter03

sealed trait List[+A]
case object Nil extends List[Nothing]
case class Cons[+A](head: A, tail: List[A]) extends List[A]
object List:

  extension (l: List[Int]) def sum: Int = l match
    case Nil => 0
    case Cons(x,xs) => x + xs.sum

  extension (l: List[Int]) def product: Double = l match
    case Nil          => 1.0
    case Cons(0.0, _) => 0.0
    case Cons(x,xs)   => x * xs.product

  def apply[A](as: A*): List[A] =
    if (as.isEmpty) then Nil
    else Cons(as.head, apply(as.tail: _*))


