package aoc2020

import scala.io.Source

object dayTwo {

  def main(args: Array[String]): Unit = {
    val changes = readFile()
    //countInterestingSeq(changes)
  }


  def readFile(): List[String] = {
    Source.fromFile("src/resources/day2.txt").getLines.toList

  }


  def countInterestingSeq(inputSeq: List[String]): Unit = {
    //println(sequenceCount("abcdddd"))
    var allVal = inputSeq.map(x => sequenceCount(x)).toList
    var allTwo = allVal.count(_ contains 2)
    var allthree = allVal.count(_ contains 3)
    println(allTwo * allthree)


  }

  def sequenceCount(inputSeq: String): List[Int] = {
    inputSeq.groupBy(c => c).map(e => e._2.length).toList

  }
}
