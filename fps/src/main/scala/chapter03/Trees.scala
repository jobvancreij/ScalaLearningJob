package chapter03
import scala.annotation.tailrec
object Trees:
  sealed trait Tree[+A]
  case class Leaf[A](value: A) extends Tree[A]
  case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

  object Tree:
    def size[A](t: Tree[A]): Int = // is tailrec nodig? Lukte mij niet met inner
      t match
        case Branch(l,r) => 1 + size(l) + size(r)
        case Leaf(_)     => 1

    def depth[A](t: Tree[A]): Int =
       t match
         case Branch(l,r) => 1 + depth(l) max depth(r)
         case Leaf(_)     => 1

    def map[A, B](t: Tree[A])(f: A => B): Tree[B] =
      t match
       case Branch(l,r) => Branch(map(l)(f), map(r)(f))
       case Leaf(x)     => Leaf(f(x))

    def fold[A,B](t: Tree[A], f: A => B, g: (B, B) => B): B =
      t match
       case Branch(l,r) => g(fold(l,f,g),fold(r,f,g))
       case Leaf(x)     => f(x)

    def sizeViaFold[A](t: Tree[A]): Int =
      fold(t, x => 1 ,_+_ )

    def maximumViaFold[A](t: Tree[Int]): Int =
      fold(t, x => x, _ max _ )

    def depthViaFold[A](t: Tree[A]): Int =
      fold(t, x => 1, (d1, d2) => 1 + (d1 max d2))


    def leaf[A](a: A): Tree[A] =
      Leaf(a)

    def branch[A](l: Tree[A], r: Tree[A]): Tree[A] =
      Branch(l,r)

    def mapViaFold[A, B](t: Tree[A])(f: A => B): Tree[B] =
      fold(t, a => leaf(f(a)), (l,r) => branch(l,r))



  extension(t: Tree[Int])
    def max: Int =
      t match
        case Branch(l,r) => l.max max r.max
        case Leaf(x)     => x
