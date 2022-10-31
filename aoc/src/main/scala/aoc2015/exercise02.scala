package aoc2015

import scala.io.Source

object exercise02 extends App:

  case class Box(l: Int, w: Int, h: Int):
    def area: Int =
      lwArea * 2 + lhArea * 2 + whArea *2
    def lwArea: Int =
      l * w
    def lhArea: Int =
      l * h
    def whArea: Int =
      w * h
    def wrapArea: Int =
      val margin  = List(lwArea, lhArea, whArea).min
      margin + area

  object Box:
    def fromString(s: String): Box =
      s match
        case s"${l}x${w}x${h}" => Box(l.toInt, w.toInt, h.toInt)
        case _ => sys.error(s"Format unknown $s")



  val part1 = Source.fromFile("aoc/src/resources/2015/exercise02.txt")
    .getLines
    .map(Box.fromString)
    .map(_.wrapArea)
    .sum

  assert(part1 == 1598415)

  val part2 = Source.fromFile("aoc/src/resources/2015/exercise02.txt")
    .getLines
    .map(_.split("x"))
    .map(x => x.map(_.toInt))
    .map(x => x.toList.sorted.reverse.tail.sum * 2 + x.product)
    .sum

  assert(part2 == 3812909)


  println(part1)
  println(part2)




