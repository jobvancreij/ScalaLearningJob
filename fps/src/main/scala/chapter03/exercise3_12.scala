
package chapter03

import chapter03.listImplementation.*
import chapter03.listImplementation.List.*

import scala.annotation.tailrec


object exercise3_12 extends App:
  // Foldleft implementation of reverse
  // Take the first item of the list and make that the z
  // The tail of the list is as
  // Every time the functions puts z in front of the new input
  // And so the last item comes first


  // ??? Is reverse right possible, didn't work for me
  def reverse(as: List[Int]): List[Int] =
    foldLeft(getTail(as),getHead(as))((x,y) => Cons(y, x))



  println(reverse(List(1,2,3,4)))





