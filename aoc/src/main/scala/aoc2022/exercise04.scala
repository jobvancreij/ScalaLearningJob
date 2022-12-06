package aoc2022

import aoc2022.exercise03.RuckSack
import aoc2022.standardAoc.readInput

import scala.io.Source


def getRange(inp: String): List[Int] =
  inp match {
    case s"${x}-${y}" => List.range(x.toInt,y.toInt+1)
    case _            => sys.error("Wrong input string")
  }
case class cleaningPair(pair1: List[Int], pair2: List[Int]):

  val uniqueItems1 = pair1.filter(!pair2.contains(_))
  val uniqueItems2 = pair2.filter(!pair1.contains(_))
  val fullyContained = uniqueItems1.length == 0 || uniqueItems2.length == 0
  val anyOverlap = uniqueItems1.length < pair1.length ||  uniqueItems2.length < pair2.length

object cleaningPair:
  def fromString(a: String, b: String):  cleaningPair =
    cleaningPair(getRange(a),getRange(b))

object exercise04 extends App:
  val outputEx1 = readInput("exercise04")
          .map(_.split(",").toList)
          .toList
          .map((x) => cleaningPair.fromString(x(0), x(1)))
          .filter(_.fullyContained)
          .length

  assert(getRange("10-13") == List(10,11,12,13), "getRange broken")
  assert(cleaningPair.fromString("1-10","5-7").fullyContained, "fullycontained example rigth")
  assert(cleaningPair.fromString("2-5","2-9").fullyContained, "fullycontained example left")
  assert(!cleaningPair.fromString("2-5","4-9").fullyContained, "not fully contained example")

  val outputEx2 = readInput("exercise04")
    .map(_.split(",").toList)
    .toList
    .map((x) => cleaningPair.fromString(x(0), x(1)))
    .filter(_.anyOverlap)
    .length

  println(s"Answer exercise 1 = ${outputEx1}")
  println(s"Answer exercise 2 = ${outputEx2}")
//

//11576949
//11585666