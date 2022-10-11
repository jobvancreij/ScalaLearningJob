package aoc2015

import scala.io.Source

object exercise02 extends App:

  def calcSides(l:Int, w: Int, h: Int): List[Int] =
    List(l*w,  w*h, h*l)



  val part1 = Source.fromFile("aoc/src/resources/2015/exercise02.txt")
    .getLines
    .map(_.split("x"))
    .map(x => x.map(_.toInt))
    .map(x => calcSides(x(0), x(1), x(2)))
    .map(x => x.sum*2+ x.min)
    .sum

  val part2 = Source.fromFile("aoc/src/resources/2015/exercise02.txt")
    .getLines
    .map(_.split("x"))
    .map(x => x.map(_.toInt))
    .map(x => x.toList.sorted.reverse.tail.sum * 2 + x.product)
    .sum



  println(part1)
  println(part2)




