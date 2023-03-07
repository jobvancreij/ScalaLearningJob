package chapter03

sealed trait List[+A]
case object Nil extends List[Nothing]
case class Cons[+A](head: A, tail: List[A]) extends List[A]
object List:

  extension (l: List[Int]) def sum: Option[Int] = l match
    case Nil => None
    case Cons(x,xs) => Some(x + xs.sum.getOrElse(0))

  extension (l: List[Int]) def product: Option[Double] = l match
    case Nil          => None
    case Cons(0.0, _) => Some(0.0)
    case Cons(x,xs)   => Some(x * xs.product.getOrElse(1.0))

  extension[A] (l: List[A]) def tail: List[A] = l match
    case Nil => Nil
    case Cons(_, x) => x

  extension[A] (l: List[A]) def setHead(arg: A): List[A] = l match
    case Nil => Cons(arg, Nil)
    case x => Cons(arg, x)

  extension[A] (l: List[A]) def dropN(n: Int): List[A] =
    def inner(inp: List[A], xn: Int): List[A] = inp match
      case Nil => Nil
      case _   => if xn > 1 then inner(inp.tail, xn-1) else inp.tail

    inner(l, n-1) // -1 for first iteration

  extension[A] (l: List[A]) def dropWhile(f: A => Boolean): List[A] = l match
    case Nil               => Nil
    case Cons(h,t) if f(h) => t.dropWhile(f)
    case Cons(h,t)         => Cons(h, t.dropWhile(f))
  
  extension[A](l: List[A]) def initList: List[A] = l match
    case Nil          => Nil
    case Cons(_,Nil)  => Nil
    case Cons(x,y)    => Cons(x, y.initList)
    
  extension[A](l: List[A]) def foldRight[B](startVal: B)(f: (A, B) => B):  B = l match
    case Nil       => startVal
    case Cons(x,y) => f(x, y.foldRight(startVal)(f))

  extension[A] (l: List[A]) def foldLeft[B](startVal: B)(f: (B,A) => B): B = l match
    case Nil => startVal
    case Cons(x, y) => y.foldLeft(f(startVal,x))(f)
  
  def apply[A](as: A*): List[A] =
    if (as.isEmpty) then Nil
    else Cons(as.head, apply(as.tail: _*))


