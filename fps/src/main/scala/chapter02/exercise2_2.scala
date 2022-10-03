package chapter02


object exercise2_2 extends App {

  def simpleSort(a: String, b: String): Boolean = {
    b == a + a
  }

  def isSorted[A](as: Array[A], ordered: (A,A) => Boolean): Boolean = {
    // Make list with tuple of all combinations
    // check the comparison function per combination
    // check if all are true
    as.dropRight(1)
      .zip(as.tail)
      .map((a,b) => ordered(a,b))
      .forall(x => x)
  }
  println(isSorted(Array("a","aa","aaaa","aaaaaaaa","aaaaaaaaaaaaaaaa"), simpleSort))
  println(isSorted(Array(1,2,3,4,5), (a,b) => b == a +1))
  println(isSorted(Array(1,2,3,4,5,6,777), (a,b) => b == a +1))

}
