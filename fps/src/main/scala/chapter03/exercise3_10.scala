package chapter03

import chapter03.listImplementation.*
import chapter03.listImplementation.List.*
import scala.annotation.tailrec


object exercise3_10 extends App:
  // Added in list implementation

  // Why is foldLeft tail rec:
  // Foldleft solves the calc on the left side first
  // As a result it only has to store the outcome
  // So for example sum impl of foldleft
  // sum(1,2,3) => (((0 + 1) + 2) + 3)
  // First solve 0 + 1 and then you only have to remeber 1
  // Foldright gets => (1 + ( 2 + (3 + (0)))) and has to remember till it reaches the last element

  // KLOPT DIT??

  // Exaple for sum
  println(foldLeft(List(1,2,5), 0)((_+_)))




