
package chapter03

import chapter03.Trees.*

object exercise3_29 extends App:

  def fold[A,B](as: Tree[A], f: (A) => B, g: (B,B) => B): B =
    as match {
      case Leaf(x) => f(x)
      case Branch(x,y) => g(fold(x,f,g),fold(y,f,g))
    }

  def maximum2[A](as: Tree[Int]): Int =
    fold(as, (x: Int) => x * 1, (x,y) => x  max y)

  def size[A](as: Tree[A]): Int =
    fold(as, (x: A) => 1, (_+_))

  def mapFold[A,B](as: Tree[A], f: A => B): Tree[B] =
    fold(as, a =>  Leaf(f(a)), (a: Tree[B], b: Tree[B]) => Branch(a,b))

  def depthFold[A](as: Tree[A]): Int =
    fold(as, (x: A) => 1, (x,y) => 1 + (x max y))

  // Cannot get this one working
//  def maxFold[A](as: Tree[A]): Int =
//    fold(as, (x: A) => x: Int, (x,y) => (x max y))

  println("size " + size(Branch(Leaf(1), Branch(Leaf(1), Leaf(2)))))
  println("depht " + depthFold(Branch(Leaf(1), Branch(Branch(Leaf(1), Leaf(22)), Leaf(2)))))
  println("mapFilter " + mapFold(Branch(Leaf(1), Branch(Branch(Leaf(1), Leaf(22)), Leaf(2))), (x) => x*2))
  println("max " + maximum2(Branch(Leaf(1), Branch(Branch(Leaf(1), Leaf(22)), Leaf(2)))))





