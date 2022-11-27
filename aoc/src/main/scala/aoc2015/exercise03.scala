package aoc2015

import scala.io.Source

object exercise03 extends App:

  def oddOrEven(list: List[String],even: Boolean): List[String] =
    // One does the odd actions and the other one the even
    // So divide the list
    val filter = if even then  0 else 1
    list.zipWithIndex
      .filter(_(1) %2 == filter)
      .map(_(0))


  def getInput = Source.fromFile("aoc/src/resources/2015/exercise03.txt")
    .getLines
    .flatMap(_.split(""))
    .toList

  def changeLoc(action: String): (Int,Int) =
    // Change in horizontal and vertical coordinates by a action
    action match {
      case ">" => (1,0)
      case "<" => (-1,0)
      case "^" => (0,1)
      case "v" => (0,-1)
      case _ => (0,0)
    }

  def alLocations(list: List[String]): List[Tuple] =
    list.map(changeLoc(_))
      .scanLeft(0,0)((x,y) => (x(0) + y(0), x(1) + y(1))) // get tuple of the sum of current and previous coordinates
      .toList

  val input = getInput

  val part1 = alLocations(input).distinct.length
  val part2 = List.concat(
                  alLocations(oddOrEven(input,true)),
                  alLocations(oddOrEven(input,false))
  ).distinct.length


  println(s"Nbr of unique adresses = ${(part1)}")
  println(s"Nbr of unique adresses  part2 = ${(part2)}")




