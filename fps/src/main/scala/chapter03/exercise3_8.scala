package chapter03
import chapter03.listImplementation.*
import List.*
object exercise3_8 extends App:
  // The output is the same as jsut initiating a list
  // Which makes sense since the function makes a Cons
  // Interesting is that is not done double
  // As in I would expect that List makes the Cons en the Cons is made again
  // How is this possible??
  print(foldRight(List(1,2,3), Nil:List[Int])(Cons(_,_)))