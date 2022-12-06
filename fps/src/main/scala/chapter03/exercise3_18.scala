
package chapter03

import chapter03.listImplementation.*
import chapter03.listImplementation.List.*

import scala.annotation.tailrec


object exercise3_18 extends App:


  println(map(List(1,2,3))((x) => x *2))
  println(map(List(1,2,3))((x) => x / 2))