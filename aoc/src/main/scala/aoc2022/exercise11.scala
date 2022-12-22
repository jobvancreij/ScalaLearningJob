package aoc2022
import aoc2022.standard.readInput
import scala.io.Source
import scala.collection.mutable.Map



object exercise11 extends App:

  def stringToAct(s: String) =  s match
        case "+" => (x: Int,y: Int) => x + y
        case "*" => (x: Int,y: Int) => x * y

  case class Monkey(id: Int, startItems: List[Long], Op: (String,String,String), Test: Int, ift: Int, iff: Int, inspects: Int=0):
      val firstItem: Option[Long]= if startItems.length > 0 then Some(startItems.head) else None
      def worryLevel = stringToAct(Op(1))(oldOrNbr(Op(0)) , oldOrNbr(Op(2))) % 96577
      def testOucome = (worryLevel % Test) == 0
      def receiver = if testOucome then ift else iff
      //def action =  actionFunc((worryLevelFunc % Test == 0))


      def oldOrNbr(str: String): Long =
        str match
          case "old" => firstItem.get
          case _     => str.toLong

//      def throwItem(receiverNbr: Int) =
//        val item = this.startItems.head
//        monkeys(this.id) = this.copy(startItems=this.startItems.tail,inspects = this.inspects + 1)
//        val throwTo = monkeys(receiverNbr)
//        monkeys(receiverNbr) = throwTo.copy(startItems=throwTo.startItems.concat(List(worryLevelFunc)))


  val inp = Source.fromFile(s"aoc/src/resources/2022/exercise11_teset.txt")
  .getLines
  .filterNot(_.isBlank)
  .grouped(6)
  .map(_.toList)
  .map(Monkey.fromString(_))



  //val monkeys = collection.mutable.Map(inp.toSeq: _*)

  object Monkey:
    def fromString(inp: List[String]): Monkey =
      def getId(inp: String): Int =
        inp.strip match
          case s"Monkey $id:" => id.toInt
      def startItems(items: String): List[Long] =
        items.strip match
          case s"Starting items: ${items}" => items.split(",").map(_.strip.toLong).toList
      def operation(inp: String): (String,String,String) =
        inp.strip match  // make list with 3 items later
          case s"Operation: new = ${a} ${x} ${b}" => (a,x,b)
      def test(inp: String): Int =
        inp.strip match  // make list with 3 items later
          case s"Test: divisible by ${x}" => x.toInt
      def ifTrue(inp: String): Int =
        inp.strip match  // make list with 3 items later
          case s"If true: throw to monkey ${nbr}" => nbr.toInt
      def ifFalse(inp: String): Int =
        inp.strip match  // make list with 3 items later
          case s"If false: throw to monkey ${nbr}" => nbr.toInt

      Monkey(getId(inp(0)), startItems(inp(1)), operation(inp(2)), test(inp(3)), ifTrue(inp(4)), ifFalse(inp(5)))


  def round(monk: List[Monkey]): List[Monkey] =
    println(monk)

    def inner(inp: List[Monkey], outp: List[Monkey]): List[Monkey] =
      inp match
        case Nil => outp
        case h::t if h.startItems.isEmpty =>
          print(h.id)
          println(t)
          println("empty startitems")
          inner(t,outp)
        case h::t =>
            val sb = h.startItems.foldLeft(outp)((o,i) =>
              val sender = o.filter((x) => x.id == h.id).head
              val oldItem = i
              val newWorry = sender.worryLevel
              val receiver = o.filter((x) => x.id == sender.receiver).head
              val newSender = sender.copy(startItems = sender.startItems.tail, inspects = sender.inspects + 1)
              val newReceiver = receiver.copy(startItems = receiver.startItems.concat(List(newWorry)))
              val others = o.filterNot((x) => List(newSender.id, newReceiver.id).contains(x.id))
              List(newSender,newReceiver).concat(others))
            inner(sb.filter((x) => t.map(_.id).contains(x.id)).sortWith(_.id < _.id),sb)

    inner(monk.sortWith(_.id < _.id),monk)

  //val prod = inp.map(_.Test).product
  //println(prod)
  //println(inp.toList)
  println((1 to 20).foldLeft(inp.toList)((x,_) => round(x)).map(_.inspects).sorted.toList)//takeRight(2).product)

  //println(List.range(1,2).foldLeft(inp.toList)((x,_) => round(x)))



//  val monkKeys = monkeys.keys
//  for(i <- List.range(1,21)) {
//   for (monk <- monkeys.keys) {
//      var currentstartItems = monkeys(monk).startItems
//      if currentstartItems.length  > 0 then
//        for (ir <- List.range(0, currentstartItems.length )) {
//          println("---")
//          println(currentstartItems(ir))
//          println(currentstartItems)
//          monkeys(monk).action
//
//
//    }
//
//  }
//  }
//  println("outcome")
//  for (monk <- monkeys.keys) {
//    println(monkeys(monk).inspects)
//  }

// to low -> 1615561152




