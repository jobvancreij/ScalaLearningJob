
package chapter03

import chapter03.Trees.*

object exercise3_28 extends App:

  def map[A,B](as: Tree[A])(f: A => B): Tree[B] =
    as match {
      case Leaf(x) => Leaf(f(x))
      case Branch(x,y) => Branch(map(x)(f), map(y)(f))
    }
  println(map(Branch(Branch(Leaf(2),Leaf(2)), Leaf(4)))((x) => x*2))





