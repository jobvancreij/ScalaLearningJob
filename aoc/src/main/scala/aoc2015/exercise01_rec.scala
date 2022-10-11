package aoc2015

import scala.io.Source

object exercise01_rec extends App:
   // recusrive fix
    val input = Source.fromFile("aoc/src/resources/2015/exercise01.txt")
                      .getLines
                      .map(_.split(""))
                      .toList
                      .flatten
    println(input)
    def goToFloor(input: List[String], curFloor: Int = 0): Int =
      input match {
        case Nil=> curFloor
        case head :: tail =>  if head == "(" then  goToFloor(tail, curFloor +1) else  goToFloor(tail, curFloor -1)
      }

    println(goToFloor(input))




