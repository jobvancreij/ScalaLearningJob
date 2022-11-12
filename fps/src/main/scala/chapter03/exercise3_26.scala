
package chapter03

import chapter03.Trees.*

object exercise3_26 extends App:


  def maximum(as: Tree[Int]): Int =
    as match {
      case Leaf(x) => x
      case Branch(x,y) => maximum(x) max maximum(y)
    }
  println(maximum(Branch(Branch(Leaf(1), Leaf(2)), Branch(Leaf(1), Branch(Leaf(9), Leaf(2))))))





