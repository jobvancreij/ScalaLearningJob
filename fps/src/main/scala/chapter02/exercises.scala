package chapter02
import java.security.KeyStore.TrustedCertificateEntry
import annotation.tailrec

object exercises:
  def fib(n: Int): Int =
    @tailrec
    def inner(index: Int, prev: Int, current: Int): Int =
      if (index<=0)
        current
      else
        inner(index - 1, prev = prev + current, current = prev)
    inner(n, 1, 0 )


  def isSorted[A](as: Array[A], ordered: (A,A) => Boolean): Boolean =
    
    @tailrec
    def inner(a: List[A]): Boolean =
      a match
        case Nil    => true
        case _::Nil => true
        case h::t   => if ordered(h,t.head) then inner(t) else false

    inner(as.toList)
  
  def curry[A,B,C](f: (A,B) => C): A => (B => C) =
    a => b => f(a,b)

  def uncurry[A,B,C](f: A => B => C): (A, B) => C =
    (a,b) => f(a)(b)
    
  def compose[A,B,C](f: B => C, g: A => B): A => C =
    a => f(g(a))




