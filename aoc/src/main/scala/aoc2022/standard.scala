package aoc2022

import scala.io.Source

object standardAoc:
  def readInput(fileName: String): Iterator[String] = 
    Source.fromFile(s"aoc/src/resources/2022/${fileName}.txt")
      .getLines
    