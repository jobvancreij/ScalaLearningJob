package aoc2015

import scala.io.Source

object exercise02 extends App:

  case class Box(l: Int, w: Int, h: Int):
    val lwArea: Int = l*w
    val whArea: Int = w*h
    val hlArea: Int = h*l
    val surfaceArea: Int = 2*lwArea+ 2*whArea + 2*hlArea
    val slackArea: Int = List(lwArea,whArea,hlArea).min
    val wrappingArea: Int = wrappingArea + slackArea

  object Box:
    def fromString(s: String): Box =
      s match
        case s"${l}x${w}x${h}" => Box(l.toInt,w.toInt,h.toInt)
        case _ => sys.error(s"Invalid input value ${s}")

  


  val part1 = Source.fromFile("aoc/src/resources/2015/exercise02.txt")
    .getLines
    .map(Box.fromString)
    .map(_.wrappingArea)
    .sum
//  val part2 = Source.fromFile("aoc/src/resources/2015/exercise02.txt")
//    .getLines
//    .map(_.split("x"))
//    .map(x => x.map(_.toInt))
//    .map(x => x.toList.sorted.reverse.tail.sum * 2 + x.product)
//    .sum



  println(part1)
  //println(part2)




