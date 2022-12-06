package aoc2022

import aoc2022.exercise03.RuckSack
import aoc2022.standardAoc.readInput

import scala.io.Source

val letterMapper = ('a' to 'z')
  .zipWithIndex
  .map(x => x(0).toString -> x(1))
  .toMap

def getPoints(letter: String, startVal: Int ): Int =
  // add the startnumber with the index in the alphabete. Lc starts at 1 and cap at 27
  startVal + letterMapper.getOrElse(letter.toLowerCase, 0)


object exercise03 extends App:

  case class RuckSack(inp: List[String]):
    // Foldleft of tail that keeps elements that occur in every sublist. Make set to avoid doubles
    val matchLetter = inp.tail
                         .foldLeft(inp.head.toSet)((x,y) => x.filter(y.contains(_)).toSet)
                         .toList(0)
                         .toString
    val matchPoints = getPoints(matchLetter, if matchLetter != matchLetter.toLowerCase then 27 else 1)
    val output = (matchLetter, matchPoints) // only used for validation



  assert(RuckSack(List("vJrwpWtwJgWrhcsFMMfFFhFp","jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL","PmmdzqPrVvPwwTWBwg")).output == ("r",18), "as4")
  assert(RuckSack(List("wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn","ttgJtRGJQctTZtZT","CrZsJsPPZsGzwwsLwLmpwMDw")).output == ("Z",52), "as5")




  val outputEx1 = readInput("exercise03")
    .map((x) => x.sliding(x.length/2,x.length/2))
    .map(_.toList)
    .map((x) => RuckSack(x)).map(_.matchPoints)
    .sum

  val outputEx2 = readInput("exercise03")
    .grouped(3)
    .map(_.toList)
    .map((x) => RuckSack(x)).map(_.matchPoints)
    .sum


  println(s"Answer exercise 1 = ${outputEx1}")
  println(s"Answer exercise 2 = ${outputEx2}")


//11576949
//11585666