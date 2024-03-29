package chapter03
import chapter03.List
import chapter03.List.sum
import chapter03.Trees.{Branch, Leaf, Tree}
import chapter03.Trees.Tree.*
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
  println(testList.append(List(10)))
  println(List(List(1,2,2), List(4,5,6)).concat)
  println(testList.addOne)
  println(List[Double](1,2,3,4,5).doubleToString)
  println(List(1,2,3,4,5).map((x) => x.toDouble))
  println(testList.filter(x => x != 1))
  println(List(1,2,3).flatMap(x => List(x,x)))
  println(List(1,2,3).map(x => List(x,x)))
  println(List(1,2,3).zipWith(List(4,5,6))((x,y) => x+y))
  println(List(1,2,3,4,5).hasSubsequence(List(3,4)))


  val exampleTree = Branch(Leaf(1), Branch(Leaf(2), Leaf(3)))
  println(size(exampleTree))
  println(exampleTree.max)
  println(depth(exampleTree))
  println(map(exampleTree)((x) => x*2))
  println(fold(exampleTree, (z) => z*10,(x,y) => x+y))
  println(sizeViaFold(exampleTree))
  println(maximumViaFold(exampleTree))
  println(depthViaFold(exampleTree))
  println(mapViaFold(exampleTree)((x) => 3))

}
