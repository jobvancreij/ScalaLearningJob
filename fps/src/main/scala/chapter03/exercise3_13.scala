
package chapter03
import scala.util.Random

import chapter03.listImplementation.*
import chapter03.listImplementation.List.*

import scala.annotation.tailrec


object exercise3_13 extends App:

  def getStartVal(x: Int): Int =
    x

  def sum(a: Int, b: Int): Int =
    a * b

  def funcTimes(f: (a: Int, b: Int) => Int)(a: Int)(b: Int): Int =
    f(b,a)

  // Try to build up the func step for step
  // First, just a simple right fold that does * the prev value and start with 1
  println(foldRight(List(1,2,3,4), 1)((a,b) => a*b))
  // Replace the start value. Instead of giving a 1 we give a function that gets a int and gives 1
  // Afterwards, we get a function that takes a int and another function which returns a function that returns another one
  // This has to be called with the start value
  // The value is the outcome of the previous func times de func that gets an input
  println(foldRight(List(1,2,3, 4), (b: Int) => b)((d,e) => b => b * e(d))(1))



  def leftFold2[A,B](as: List[A], z: B)(f: (B,A) => B): B =
    foldRight(as, (b: B) => b)((a,g) => b => f(g(b),a))(z)

  def foldRight2[A,B](l: List[A], z: B)(f: (A,B) => B): B =
    foldLeft(l, (b: B) => b)((g,a) => b => g(f(a,b)))(z)


  println(leftFold2(List(1,2,3,4),1)(_*_))
  println(foldRight2(List(1,2,3,4),1)(_*_))

//  //print(funcTimes((a: Int, b: Int) => a * b)(4)(5))
//  println(foldRight(List(1,2,3), 1)(_*_))
//  println("----")
//  println(foldRight(List(1,2,3), (b: Int)=> b) ((c,d) => z => d(c) * z)(5))
//



