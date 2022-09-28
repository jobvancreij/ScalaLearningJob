package AOC

import scala.io.Source

object day2_part_two extends App {

  var allWordsOg = readFile()
  var x = comparableWords(allWordsOg, allWordsOg)
  println(x)
  //  println("dfada")


  def comparableWords(allWords: List[String],
                      lookUpWords: List[String]): String = {
    var currentWord = allWords.head
    var currentLookupWord = lookUpWords.head
    var similarChar = currentWord
      .zip(currentLookupWord)
      .map((a, b) => if (a == b) a else "")
      .mkString
    if (lookUpWords.tail.length == 0) comparableWords(allWords.tail, allWordsOg)
    else if (similarChar.length == currentLookupWord.length - 1) similarChar
    else comparableWords(allWords, lookUpWords.tail)

  }

  def readFile(): List[String] = {
    Source.fromFile("src/resources/day2_part2.txt").getLines.toList

  }

}
