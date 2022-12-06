
package chapter03

import chapter03.listImplementation.*
import chapter03.listImplementation.List.*

import scala.annotation.tailrec


object exercise3_21 extends App:


  println(flatMap(List(1,2,3,1,2,1,2))((x) => filter(List(x))((i) => i ==1)))