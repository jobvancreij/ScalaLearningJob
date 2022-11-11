
package chapter03

import chapter03.listImplementation.*
import chapter03.listImplementation.List.*

import scala.annotation.tailrec


object exercise3_24 extends App:

 
  def hasSubSequence[A](sup: List[A], sub: List[A]): Boolean =

    def innerloop[A](supsup: List[A],checks: List[A] = List.empty): Boolean =
      // Make sure that that no combination of something starting with the first val
      // in the list exists.
      // So if the input is List(1,2,3,4). Check if there is no combination with 1
      // For exmaple --> List(1), List(1,2), List(1,2,3), List(1,2,3,4).
      // Other combinations such as List(2,3) are looked for when the outerloop calls again.
      supsup match {
        case Nil => false
        case Cons(x,y) =>
          if(append(checks, x) == sub) true else innerloop(y, append(checks, x))
      }
    def outerLoop(outerSup: List[A], el: List[A] = List.empty): Boolean =
      // This function loop through the list and makes it 1 item smaller every time
      // Moreover, List(1,2,3) calls innerloop :
      // List(1,2,3) --> List(2,3) --> List(3)
      // If the sub value is founed in the meanwhile it returns true
      // If nothing is found and the list is empty return false
      outerSup match {
        case Nil => false
        case Cons(x,y) => if(innerloop(Cons(x,y))) true else outerLoop(y,append(el, x))
      }


    outerLoop(sup)


  println(hasSubSequence(List(1,2,3,4,5), List(2,2)))
  println(hasSubSequence(List(1,2,3,4,5), List(1,2)))






