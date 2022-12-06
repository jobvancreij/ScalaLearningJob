package aoc2022

import aoc2022.standardAoc.readInput

import scala.io.Source

case class Calories(calories: List[Int]):
  val totalCalories = calories.sum

object Calories:
  def fromString(cal: List[String]): Calories =
    Calories(cal.map(_.toInt))


object exercise01 extends App:
  val output = readInput("exercise01")
             .mkString("\n")
             .split("\n\n")
             .map(_.split("\n").toList)
             .toList
             .map(Calories.fromString(_))
             .map(_.totalCalories)

  println(s"Answer exercise 1 = ${output.max}")
  println(s"Answer exercise 2 = ${output.sortWith(_ > _).slice(0,3).sum}")


//11576949
//11585666