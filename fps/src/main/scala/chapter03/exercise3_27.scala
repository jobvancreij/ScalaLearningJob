
package chapter03

import chapter03.Trees.*

object exercise3_27 extends App:

  def depth[A](as: Tree[A]): Int =
    as match {
      case Leaf(x) => 0
      case Branch(x,y) => 1 + (depth(x) max depth(y))
    }
  println(depth(Branch(Branch(Leaf(1), Leaf(2)), Branch(Leaf(1), Branch(Leaf(1), Leaf(2))))))




