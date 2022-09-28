package aoc2020

import scala.io.Source

object day2_part2_two extends App {

  var allWordsOg = readFile()
  var x = comparableWords(allWordsOg)
  println(x)
  //  println("dfada")


  def comparableWords(allWords: List[String]): Unit = {

    val (w1, w2, diff) = (for {
      w1 <- allWords
      w2 <- allWords
      diff = w1.zipWithIndex.diff(w2.zipWithIndex)
      if diff.length == 1
    } yield (w1, w2, diff.head)).head

    w1.zipWithIndex
      .filter((value, index) => (diff._2 != index))
      .map(x => x._1)
      .mkString


  }

  def readFile(): List[String] = {
    Source.fromFile("src/resources/day2_part2.txt").getLines.toList

  }

}
