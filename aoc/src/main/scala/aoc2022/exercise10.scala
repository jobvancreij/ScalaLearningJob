package aoc2022
import aoc2022.standard.readInput
import scala.io.Source
import scala.collection.mutable.Map


object exercise10 extends App:

//  case class Cycle(i: Int, x: Int,buff: Int = 0):
//    val s: Int = i * x

  case class Cycle(startI: Int, startX: Int, addBuff: Int = 0, startBuff: Int = 0):
    val endI: Int = startI + 1
    val endX: Int = startX + startBuff
    val endBuff = addBuff
    val s: Int = endI * endX
    val pixelW = startI % 40
    val pixelH = math.ceil(startI / 40).toInt
    val sprite = Set(startX-1, startX, startX +1)
    val lit: String = if sprite.contains(startI %40) then "█"  else "."//"█" else "."


    def nextCycle(addBuff: Int = 0): Cycle =
      Cycle(startI= this.endI, startX=this.endX, addBuff=addBuff, startBuff=this.endBuff)

    def pictureParser(h: Int): Int =
      // println was to late. fix this func to be nicer
      if this.pixelH == h then

        print(this.lit)
      else
        println("")
        print(this.lit)
      this.pixelH

  // get indexes and loop through the list with actions
  // Based on input (index,action) say in which index it changes
  // then loop through list

// Option 2 case class with add in 1 it and at in 2 it

  object Cycle:
    def inputTrans(inp: String, prevCycle: Cycle): List[Cycle] =
      // Is dit wel functioneel omdat ik eigenlijk zowel de string vertaal als al direct de logica toepas van meerdere cycles bij addx
       inp match
        case "noop" => List(prevCycle.nextCycle(addBuff=0))
        case s"addx ${x}" =>
                            val cycleA = prevCycle.nextCycle(addBuff=x.toInt)
                            val cycleB = cycleA.nextCycle(addBuff=0)
                            List(cycleA, cycleB)
        case _ => sys.error("Wrong input")

  println(Cycle.inputTrans("addx 10", Cycle(1,5)))
  // check inputTrans
  assert(Cycle.inputTrans("addx 10", Cycle(1,5)) == List(Cycle(2,5,10,0), Cycle(3,5,0,10)), "addX failed")
  assert(Cycle.inputTrans("noop", Cycle(1,5,0,0)) == List(Cycle(2,5,0,0)), "noop failed")
  assert(Cycle(1,5,0,10).endX == 15, "buff release end")



  val Ex1 = readInput("exercise10")
    .scanLeft(List(Cycle(0, 1)))((c, inp) => Cycle.inputTrans(inp, c.reverse.head))
    .toList
    .tail// Take tail don't want I=0
    .flatten
    .filter((c) => Set(20,60,100,140,180,220).contains(c.endI))
    .map(_.s)
    .sum
  assert(Ex1==11220, "answer 1 correctly")


  println(Ex1)
  println("")

  val Ex2 = readInput("exercise10")
  .scanLeft(List(Cycle(-1,1)))((c, inp) => Cycle.inputTrans(inp, c.reverse.head))
  .toList
  .tail// Take tail don't want I=0
  .flatten
  //.foreach((x) => println(s"${x.startI}, ${x.pixelH}, ${x.startI %40}, ${x.lit}, ${x.sprite}"))
  .foldLeft(0)((h, c) => c.pictureParser(h)) // beetje misbruik van foldleft, kan ik dit ook in een map doen? Dacht wel namelijk maar lukte nog niet

  //println(Ex2)
  println("")


//"BZPAJELK"






//  println(s"Answer to question 1 = ${Ex1}")
//  println(s"Answer to question 2 = ${Ex2}")

//  FFFFF
//  FFFFF
//  FFFFF
//  FFFFT
//  FFFTF
