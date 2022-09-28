package aoc2020

import scala.io.Source

object dayOne {

  def main(args: Array[String]): Unit = {
    val changes = readFile()
    val currentFreq = newFreq(0, changes)
    println(againSameFreq(0, changes, changes.length, Set()))

  }

  def readFile(): Array[Int] = {
    Source.fromFile("src/resources/info.txt").getLines.toArray.map(x => x.toInt)

  }

  def againSameFreq(currentFreq: Int,
                    changes: Array[Int],
                    currentIndex: Int,
                    freqHist: Set[Int]
                   ): Int = {
    val newFreq = currentFreq + changes(currentIndex % changes.length)
    if (freqHist contains newFreq) {
      newFreq
    } else {
      againSameFreq(newFreq, changes, currentIndex + 1, freqHist.incl(newFreq))
    }

  }


  def newFreq(currentFreq: Int, changes: Array[Int]): Int = {
    currentFreq + changes.sum

  }

}
