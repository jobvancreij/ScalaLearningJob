package chapter01
import chapter01.{Creditcard, Charge, buyCoffee, buyCoffees}
object main extends App{
  val cc = Creditcard(100)
  println(buyCoffees(cc, 4))



}
