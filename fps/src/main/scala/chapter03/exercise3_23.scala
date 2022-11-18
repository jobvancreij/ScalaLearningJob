
package chapter03

import chapter03.listImplementation.*
import chapter03.listImplementation.List.*

import scala.annotation.tailrec


object exercise3_23 extends App:

 
  println(map(zip(List("a","b","c"), List("d","e","f")))(_+_))