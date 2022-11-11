package chapter03

import chapter03.listImplementation.*
import chapter03.listImplementation.List.*

object exercise3_9 extends App:
  def length(as: List[Int]): Int =
    // Take the z every time and do this +1
    // Works becasue z is previous value
    // Only have to count
    foldRight(as, 0)((_,z) => (z + 1))

  println(length(List(1,2,3, 4, 5)))