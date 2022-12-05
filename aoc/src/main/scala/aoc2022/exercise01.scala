package aoc2022

import aoc2022.exercise01.cals
import aoc2022.standardAoc.readInput

import scala.io.Source

case class Calories2(calories: List[Int]):
  val totalCalories = calories.sum

object Calories2:
  def fromString(cal: List[String]): Calories2 =
    Calories2(cal.map(_.toInt))


object exercise01_test extends App:
  val output = readInput("exercise01")
             .mkString("\n")
             .split("\n\n")
             .map(_.split("\n").toList)
             .toList
             .map(Calories2.fromString(_))
             .map(_.totalCalories)
  println(output)
  println(s"Answer exercise 1 = ${output.max}")
  println(s"Answer exercise 2 = ${output.sortWith(_ > _).slice(0,3)}")


//11576949
//11585666