package chapter02
import chapter02.exercises
object main extends App {
  println(exercises.fib(10))
  println(exercises.isSorted(Array(1,2,3,4), (x: Int, y: Int) =>  x == y))
  println(exercises.curry((x: Int, y: Int) => x + y)(10)(20))
  println(exercises.uncurry((x: Int) => (y: Int) => x + y)(1,4))
  println(exercises.compose((x: Int) => x *2, (y: Int) => y + 1)(10))



}
