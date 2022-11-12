
package chapter03

import chapter03.Trees.*

object exercise3_25 extends App:

  def count[A](as: Tree[A]): Int =
    as match
      case Leaf(x) => 1
      case Branch(x,y) => 1 + count(x) + count(y)



  println(count(Branch(Leaf(2), Branch(Leaf(1), Leaf(2)))))





