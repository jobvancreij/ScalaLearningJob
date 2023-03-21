package chapter03
import chapter03.List
import helperFunctions.helpers._

object exercise3_01 extends App:
  val tracker = Tracker
  val x = List(1,2,3,4,5) match
    case Cons(x, Cons(2, Cons(4, _))) => x
    case Nil => 42
    case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y
    case Cons(h,t) => h + t.sum.getOrElse(0)
    case _ => 101
  printAnswer("3.1", x)
  tracker.outputRunTime
  println("ok")

