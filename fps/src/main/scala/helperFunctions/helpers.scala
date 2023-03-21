package helperFunctions
import java.nio.file.Paths

object helpers:

  case object Tracker:
    val startTime = curTime
    def runTime = (curTime - startTime) / 1e9d
    def outputRunTime = println(s"The runtime is: $runTime")

    def curTime = System.nanoTime

  def printAnswer[A](ex: String, inp: A): Unit =
    println(Console.YELLOW_B + s"The answer to $ex is $inp" + Console.RESET)
