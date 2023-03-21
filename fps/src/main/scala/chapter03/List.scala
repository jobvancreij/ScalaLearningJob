package chapter03
import scala.annotation.tailrec



sealed trait List[+A]
case object Nil extends List[Nothing]
case class Cons[+A](head: A, tail: List[A]) extends List[A]
object List:

  case class Buffer[A](buff: List[A], maxLen: Int):
    // Used for hasSubSequence
    // Buffer should reach a max length en then drop the first val before appending
    private lazy val isMax = buff.length == maxLen
    private lazy val baseForNewBuff = if isMax then buff.tail else buff
    def append(inp: A): Buffer[A] =
      copy(buff = baseForNewBuff.append(List(inp)))
    def equalToBuff(seq: List[A]): Boolean =
      // is list equal to buffer
      seq == buff

  object Buffer:
    def emptyBuffer[A](maxLen: Int): Buffer[A] =
      Buffer(Nil: List[A], maxLen)

  extension[A] (l: List[List[A]]) // Kan ik hier ook een list[A] van maken?
    def concat: List[A] =
      l.foldRight(Nil: List[A])((a, b) => b.append(a))

  extension[A] (l: List[Double])
    def doubleToString: List[String] =
      l.foldLeft(Nil: List[String])((a, b) => a.append(List(b.toString)))

  extension (l: List[Int])
    def sum: Option[Int] = l match
      case Nil        => None
      case Cons(x,xs) => Some(x + xs.sum.getOrElse(0))

    def product: Option[Double] = l match
      case Nil          => None
      case Cons(0.0, _) => Some(0.0)
      case Cons(x,xs)   => Some(x * xs.product.getOrElse(1.0))

    def foldSum: Int = l.foldLeft[Int](0)(_ + _)

    def foldProduct: Int = l.foldLeft[Int](1)(_ * _)

    def addOne: List[Int] =
      l.foldLeft(Nil: List[Int])((a, b) => a.append(List(b + 1)))

    def addLists(m: List[Int]): List[Int] =
      (l, m) match
        case (Nil, _)                 => Nil
        case (_, Nil)                 => Nil
        case (Cons(a, b), Cons(c, d)) => Cons(a + c, b.addLists(d))

  extension[A] (l: List[A])
    def tail: List[A] = l match
      case Nil        => Nil
      case Cons(_, x) => x

    def head: Option[A] = l match
      case Nil        => None
      case Cons(x, _) => Some(x)

    def setHead(arg: A): List[A] = l match
      case Nil => Cons(arg, Nil)
      case x   => Cons(arg, x)

    def dropN(n: Int): List[A] =
      @tailrec
      def inner(inp: List[A], xn: Int): List[A] = inp match
        case Nil => Nil
        case _   => if xn > 1 then inner(inp.tail, xn-1) else inp.tail
      inner(l, n-1) // -1 for first iteration

    def dropWhile(f: A => Boolean): List[A] = l match
      case Nil               => Nil
      case Cons(h,t) if f(h) => t.dropWhile(f)
      case Cons(h,t)         => Cons(h, t.dropWhile(f))
  
    def initList: List[A] = l match
      case Nil          => Nil
      case Cons(_,Nil)  => Nil
      case Cons(x,y)    => Cons(x, y.initList)

    def foldRight[B](startVal: B)(f: (A, B) => B):  B = l match
      case Nil       => startVal
      case Cons(x,y) => f(x, y.foldRight(startVal)(f))

    @tailrec
    def foldLeft[B](startVal: B)(f: (B,A) => B): B = l match
      case Nil        => startVal
      case Cons(x, y) => y.foldLeft(f(startVal,x))(f)

    def reverse: List[A] = l.foldLeft(List[A]())((x,y) => Cons(y,x))

    def foldLeftFromRight[B](startVal: B)(f: (B,A) => B): B =
      l.foldLeft((b: B) => b)((g,a) => c => g(f(c,a)))(startVal)

    def foldRightFromLeft[B](startVal: B)(f: (A,B) => B): B =
      l.foldRight((b: B) => b)((a,g) => b => g(f(a,b)))(startVal)

    def append(inp: List[A]): List[A] =
      l.foldRight(inp)(Cons(_,_))

    def flatMap[B](f: A => List[B]): List[B] =
      l.foldLeft(Nil: List[B])((a, b) => a.append(f(b)))

    def map[B](f: A => B): List[B] =
      l.foldLeft(Nil: List[B])((a, b) => a.append(List(f(b))))

    def filter(f: A => Boolean): List[A] =
      l.foldLeft(Nil: List[A])((a, b) => if f(b) then a.append(List(b)) else a)

    def zipWith(m: List[A])(f: (A, A) => A): List[A] =
      (l, m) match
        case (Nil, _)                     => Nil
        case (_, Nil)                     => Nil
        case (Cons(x1, y1), Cons(x2, y2)) => Cons(f(x1, x2), y1.zipWith(y2)(f))

    def length: Int =
      l.foldLeft(0)((acc, _) => acc+1)

    def hasSubsequence(sub: List[A]): Boolean =
      @tailrec
      def inner(innerL: List[A], buffer: Buffer[A]): Boolean =
        innerL match
          case Nil       => false
          case Cons(x,y) =>
            val newBuffer = buffer.append(x)
            if newBuffer.equalToBuff(sub) then true else inner(y,newBuffer)

      inner(l, Buffer.emptyBuffer(sub.length))

  def apply[A](as: A*): List[A] =
    if as.isEmpty then Nil
    else Cons(as.head, apply(as.tail: _*))

