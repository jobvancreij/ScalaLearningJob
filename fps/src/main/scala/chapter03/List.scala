package chapter03
import scala.annotation.tailrec

sealed trait List[+A]
case object Nil extends List[Nothing]
case class Cons[+A](head: A, tail: List[A]) extends List[A]
object List:

  extension (l: List[Int]) def sum: Option[Int] = l match
    case Nil        => None
    case Cons(x,xs) => Some(x + xs.sum.getOrElse(0))

  extension (l: List[Int]) def product: Option[Double] = l match
    case Nil          => None
    case Cons(0.0, _) => Some(0.0)
    case Cons(x,xs)   => Some(x * xs.product.getOrElse(1.0))

  extension[A] (l: List[A]) def tail: List[A] = l match
    case Nil        => Nil
    case Cons(_, x) => x

  extension[A] (l: List[A]) def head: Option[A] = l match
    case Nil        => None
    case Cons(x, _) => Some(x)
  extension[A] (l: List[A]) def setHead(arg: A): List[A] = l match
    case Nil => Cons(arg, Nil)
    case x   => Cons(arg, x)

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
    
  extension[A](l: List[A])
    def foldRight[B](startVal: B)(f: (A, B) => B):  B = l match
      case Nil       => startVal
      case Cons(x,y) => f(x, y.foldRight(startVal)(f))

  extension[A] (l: List[A])
    @tailrec
    def foldLeft[B](startVal: B)(f: (B,A) => B): B = l match
      case Nil        => startVal
      case Cons(x, y) => y.foldLeft(f(startVal,x))(f)

  extension[A] (l: List[Int])
    def foldSum: Int = l.foldLeft[Int](0)(_+_)

  extension[A] (l: List[Int])
    def foldProduct: Int = l.foldLeft[Int](1)(_ * _)

  extension[A] (l: List[A])
    def reverse: List[A] = l.foldLeft(List[A]())((x,y) => Cons(y,x))

  extension[A] (l: List[A])
    def foldLeftFromRight[B](startVal: B)(f: (B,A) => B): B =

      l.foldLeft((b: B) => b)((g,a) => c => g(f(c,a)))(startVal)

  extension[A] (l: List[A])
    def foldRightFromLeft[B](startVal: B)(f: (A,B) => B): B =
      l.foldRight((b: B) => b)((a,g) => b => g(f(a,b)))(startVal)


  extension[A] (l: List[A])
    def append(inp: List[A]): List[A] =
      l.foldRight(inp)(Cons(_,_))

  extension[A] (l: List[List[A]])
    def concat: List[A] =
      l.foldRight(Nil: List[A])((a,b) => b.append(a))

  extension[A] (l: List[Int])
    def addOne: List[Int] =
       l.foldLeft(Nil: List[Int])((a, b) => a.append(List(b+1)))

  extension[A] (l: List[Double])
    def doubleToString: List[String] =
      l.foldLeft(Nil: List[String])((a, b) => a.append(List(b.toString)))

  extension[A] (l: List[A])
    def map[B](f:A => B): List[B] =
      l.foldLeft(Nil: List[B])((a, b) => a.append(List(f(b))))

  extension[A] (l: List[A])
    def filter(f: A => Boolean): List[A] =
      l.foldLeft(Nil: List[A])((a, b) => if f(b) then a.append(List(b)) else a)

  extension[A] (l: List[List[A]])
    def flatMap[B](f: A => B): List[B] =
      l.foldLeft(Nil: List[B])((a, b) => a.append(b.map(f)))

  //      (B,A) => B
//        ((B,A) => B,A) =>
//
//
//      x2 voor List(1,2,3)
//      val g = (x,y) => x * y
//      g * (x,y) => x*y
//
//    (1 * 1 * (2) * 3



  def apply[A](as: A*): List[A] =
    if (as.isEmpty) then Nil
    else Cons(as.head, apply(as.tail: _*))


