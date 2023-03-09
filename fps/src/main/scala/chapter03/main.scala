package chapter03
import chapter03.List
import chapter03.List.sum
object main extends App {
  val testList = List(1,2,3,4)
  println(testList.sum)
  println(testList.product)
  println(testList.tail)
  println(testList.setHead(10))
  println(testList.initList)
  println(testList.foldRight(1)((x,y) => x + y))
  println(testList.foldLeft(1)((x,y) => x + y))
  println(testList.foldSum)
  println(testList.reverse)
  println(testList.foldLeftFromRight(0)((a: Int, b: Int) => a+b))
  println(testList.foldRightFromLeft(0)((a: Int, b: Int) => a+b))


}
