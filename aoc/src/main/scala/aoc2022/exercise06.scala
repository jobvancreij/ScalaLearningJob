package aoc2022

import aoc2022.standardAoc.readInput

import scala.collection.mutable.Map
import scala.io.Source


object exercise06 extends App:
  // Read input
  //var input = readInput("exercise05")

  case class Buffer[A](bufferVals: List[A] = List.empty, i: Int = 0,seqLen: Int=4):

    def add(x: A): Buffer[A] =
      if bufferVals.length < seqLen then
        this.copy(bufferVals = bufferVals.appended(x),i=i+1)
      else
        this.copy(bufferVals = bufferVals.tail.appended(x),i=i+1)

    def uniqueSeq: Boolean =
      bufferVals.length == seqLen & bufferVals.length == bufferVals.distinct.length


  def sequence[A](inp: List[A],buffer: Buffer[A]): Option[Int] =
    inp match {
      case Nil => None
      case h::t =>
        val newBuf = buffer.add(h)
        if newBuf.uniqueSeq then Option(newBuf.i)
        else sequence(t, newBuf)
    }

  // Assert examples
  assert(!Buffer(List("a","b", "c","c")).uniqueSeq, "test1")
  assert(Buffer(List("a","b", "c","d")).uniqueSeq, "test2")
  assert(!Buffer(List("a","b", "c")).uniqueSeq, "test3")
  assert(!Buffer(List("a","b", "c","d","e")).uniqueSeq, "test4")

  val testBuf = Buffer[String](seqLen = 4) // Can I do this without [String] ?
  assert(sequence("bvwbjplbgvbhsrlpgdmjqwftvncz".toList.map(_.toString), buffer = testBuf) == Some(5))
  assert(sequence("nppdvjthqldpwncqszvftbrmjlhg".toList.map(_.toString), buffer = testBuf) == Some(6))
  assert(sequence("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg".toList.map(_.toString), buffer = testBuf) == Some(10))
  assert(sequence("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw".toList.map(_.toString), buffer = testBuf) == Some(11))

  val testBuf2 = Buffer[String](seqLen = 14) // Can I do this without [String] ?
  assert(sequence("mjqjpqmgbljsphdztnvjfqwrcgsmlb".toList.map(_.toString), buffer = testBuf2) == Some(19))
  assert(sequence("bvwbjplbgvbhsrlpgdmjqwftvncz".toList.map(_.toString), buffer = testBuf2) == Some(23))
  assert(sequence("nppdvjthqldpwncqszvftbrmjlhg".toList.map(_.toString), buffer = testBuf2) == Some(23))
  assert(sequence("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg".toList.map(_.toString), buffer = testBuf2) == Some(29))

  // Implementation
  val inp = readInput("exercise06")
    .map(_.split(""))
    .flatten
    .toList

  val ex1 = sequence(inp,buffer = Buffer(seqLen = 4 ))
  println(ex1)

  val ex2 = sequence(inp,buffer = Buffer(seqLen = 14 ))
  println(ex2)






