package aoc2015

import scala.io.Source

object exercise01 extends App:

  val input = Source.fromFile("aoc/src/resources/2015/exercise01.txt")
                      .getLines
                      .map(_.split(""))
                      .toList
                      .flatten

  val floorNbr = input.groupBy(identity) // groupby
                       .mapValues(_.size) //count
                       .map((x,y) => if x == "(" then y else y * -1) // change going down to minus
                       .sum // take sum of going up (+) and going down (-) and get correct floor


  println(s"Going to floor $floorNbr")



