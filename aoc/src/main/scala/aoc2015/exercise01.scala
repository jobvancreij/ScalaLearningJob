package aoc2015

import scala.io.Source

object exercise01 extends App:

  val input = Source.fromFile("aoc/src/resources/2015/exercise01.txt")
                      .getLines
                      .map(_.split(""))
                      .toList
                      .flatten
  def enterBasement(floorNumbers: List[Int]): Int =
    if (floorNumbers.head < 0)
      1
    else
      1 + enterBasement(floorNumbers.tail)

  val floorNbr = input.map(x => if (x == "(") 1 else -1)
  val currentFloorNbr = floorNbr
                  .tail
                  .scanLeft(floorNbr.head)(_ + _)
                  .toList


  println(s"Going to floor ${floorNbr.sum}")
  println(s"First enter basesement at index: ${enterBasement(currentFloorNbr)}")




