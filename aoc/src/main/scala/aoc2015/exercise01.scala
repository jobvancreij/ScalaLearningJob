package aoc2015

import .input

import scala.io.Source

object exercise01 extends App:

  val input = Source.fromFile("aoc/src/resources/2015/exercise01.txt")
                      .getLines
                      .map(_.split(""))
                      .toList
                      .flatten

  val floorNbr = input.map(x => if (x == "(") 1 else -1).sum


  println(s"Going to floor $floorNbr")



