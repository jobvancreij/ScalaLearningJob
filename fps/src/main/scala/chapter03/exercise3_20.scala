
package chapter03

import chapter03.listImplementation.*
import chapter03.listImplementation.List.*

import scala.annotation.tailrec


object exercise3_20 extends App:


  println(flatMap(List(1,2,3))((x) => List(x,x)))