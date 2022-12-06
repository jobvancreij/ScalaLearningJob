package aoc2022

import aoc2022.standardAoc.readInput
import scala.collection.mutable.Map
import scala.io.Source



object exercise05 extends App:
  // Read input
  var input = readInput("exercise05")
    .mkString("\n")
    .split("\n\n")
    .toList

  // Get the action part
  val actions = readInput("exercise05")
    .mkString("\n")
    .split("\n\n")
    .toList(1).split("\n").toList

  // Get the stack part
  var stacks = input
    .toList(0)
    .replace("    ", "[XX]")
    .replace(" ", "")
    .replace("][", "],[")
    .split("\n").dropRight(1)
    .map(_.split(",").toList)
    .toList
    .transpose
    .map(_.filter(_ !="[XX]"))
    .map(Stack(_))


  case class Stack(crates: List[String]):
    // Stack contains crates which can be removed /added / asked
    def addCrates(cr: List[String], newMover: Boolean): Stack =
      if newMover then Stack(cr.concat(crates))
      else Stack(cr.reverse.concat(crates))

    def removeNCrates(n: Int): Stack =
      Stack(crates.slice(n, crates.length))

    def getNCrates(n: Int): List[String] =
      crates.slice(0,n)

  case class stackField(Stacks: Map[Int, Stack]):
    // the field with different crates

    def firstCrates = Stacks
      .keys
      .toList
      .sorted
      .map((x: Int) =>Stacks(x)
        .crates(0)
        .replace("[","")
        .replace("]","")
      ).mkString("")


    def move(act: String, newMover: Boolean = false) =
      val (a,b,c) = act match
        case s"move ${x} from ${y} to ${z}" =>  (x.toInt,y.toInt,z.toInt)
        case _ => sys.error("Wrong patter match")
      val crates = Stacks(b).getNCrates(a)
      Stacks(b) = Stacks(b).removeNCrates(a)
      Stacks(c) = Stacks(c).addCrates(crates,newMover)

  case object stackField:
    def fromStack(inp: List[Stack]): stackField =
      stackField(Map(inp.zipWithIndex.map((x, i) => (i+1, x)) : _*))



  var Ex1 = stackField.fromStack(stacks)
  actions.foreach(Ex1.move(_,newMover = false))
  println(s"Answer exercise 1 = ${Ex1.firstCrates}")

  var Ex2 = stackField.fromStack(stacks)
  actions.foreach(Ex2.move(_,newMover = true))
  println(s"Answer exercise 2 = ${Ex2.firstCrates}")


