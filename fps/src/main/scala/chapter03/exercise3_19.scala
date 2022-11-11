
package chapter03

import chapter03.listImplementation.*
import chapter03.listImplementation.List.*

import scala.annotation.tailrec


object exercise3_19 extends App:


  println(filter(List(1,2,3))((x) => x ==2))
  println(filter(List(1,2,3))((x) => x ==1))