
package chapter03

import chapter03.listImplementation.*
import chapter03.listImplementation.List.*

import scala.annotation.tailrec


object exercise3_17 extends App:

  def doubleToString(as: List[Double]): List[String] =
    map(as)(_.toString)

  println(doubleToString(List(1,2,3,4)))