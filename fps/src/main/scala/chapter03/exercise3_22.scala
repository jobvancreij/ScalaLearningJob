
package chapter03

import chapter03.listImplementation.*
import chapter03.listImplementation.List.*

import scala.annotation.tailrec


object exercise3_22 extends App:

  // Write a function that accepts two lists and constructs a new list by adding corresponding element.
  // Fore example, List(1,2,3) and List(4,5,6) become List(5,7,9)

 
  println(map(zip(List(1,2,3), List(3,4,5)))((x,y) => x + y))