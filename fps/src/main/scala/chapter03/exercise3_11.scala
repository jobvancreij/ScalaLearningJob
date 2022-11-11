
package chapter03

import chapter03.listImplementation.*
import chapter03.listImplementation.List.*

import scala.annotation.tailrec


object exercise3_11 extends App:
  def sumLeft(as: List[Int]): Int =
    foldLeft(as,0)(_+_)

  def productLeft(as: List[Int]): Int =
    foldLeft(as,1)(_*_)

  println(sumLeft(List(1,2,4)))
  println(productLeft(List(1,2,4)))




