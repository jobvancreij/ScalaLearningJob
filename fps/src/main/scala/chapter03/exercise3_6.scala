package chapter03
import chapter03.listImplementation.{Cons, Nil, List}

object exercise3_6 extends App:
  def dropLast[A](l: List[A]): List[A] =
    l match
      case Nil => Nil
      case Cons(_,Nil) => Nil
      case Cons(x,y) => Cons(x, dropLast(y))


  println(dropLast(List(1,2,3,4)))
  // Linked list work from front to back
  // So have to iterate through entire list