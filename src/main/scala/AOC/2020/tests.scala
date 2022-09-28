package AOC

import scala.io.Source

object tests extends App {

  println(comparableWords(readFile()))

  def comparableWords(allWords: List[String]): String = {
    val head = allWords.head
    val tail = allWords.tail
    for (word <- tail) {
      var similarChar = head
        .toList
        .zip(word.toList)
        .map((a, b) => if (a == b) 1 else 0)
      if (similarChar.filter(_ == 1).length == similarChar.length - 1) {
        return head
          .zip(similarChar)
          .filter((_, b) => b == 1)
          .map((a, _) => a)
          .mkString
      }

    }
    return comparableWords(tail)

  }

  def readFile(): List[String] = {
    Source.fromFile("src/resources/day2_part2.txt").getLines.toList

  }

}
