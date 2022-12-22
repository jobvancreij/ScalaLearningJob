package aoc2022
import aoc2022.standard.readInput
import scala.io.Source
import scala.collection.mutable.Map



object Exercise11 extends App:

  def stringToAct(op: String, x: Option[Long], y: Option[Long])(v: Long): Long =
    op match
        case "+" => x.getOrElse(v) + y.getOrElse(v)
        case "*" => x.getOrElse(v) * y.getOrElse(v)

  case class Monkey(
    id: Long,
    startItems:       List[Long],
    op:               (String, Option[Long], Option[Long]),
    test:             Long,
    monkeyIfTrue:     Long,
    monkeyIfFalse:    Long,
    manageWorryLevel: Long => Long,
    inspects:         Long = 0,
  ):
      private def firstItem: Long =
        Option.when(startItems.length > 0)(startItems.head).getOrElse(sys.error("boom"))

      private def worryLevel: Long =
        stringToAct(op(0), op(1), op(2))(firstItem)

      def managedWorryLevel: Long =
        manageWorryLevel(worryLevel)

      private def testOutcome: Boolean =
        managedWorryLevel % test == 0

      def receiverMonkey: Long =
        if testOutcome then monkeyIfTrue else monkeyIfFalse


  val input1 =
    Source.fromFile(s"aoc/src/resources/2022/exercise11.txt")
    .getLines
    .filterNot(_.isBlank)
    .grouped(6)
    .map(_.toList)
    .map(s => Monkey.fromString(s, w => w / 3))



  object Monkey:

    def fromString(inp: List[String], manageWorryLevel: Long => Long): Monkey =

      def getId(inp: String): Long =
        inp.strip match
          case s"Monkey $id:" => id.toLong

      def startItems(items: String): List[Long] =
        items.strip match
          case s"Starting items: ${items}" =>
            items.split(",").map(_.strip.toLong).toList

      def operation(inp: String): (String, Option[Long], Option[Long]) =
        inp.strip match  // make list with 3 items later
          case s"Operation: new = old $op old"   => (op, None, None)
          case s"Operation: new = $lhs $op old"  => (op, Option(lhs.toLong), None)
          case s"Operation: new = old $op $rhs"  => (op, None, Option(rhs.toLong))
          case s"Operation: new = $lhs $op $rhs" => (op, Option(lhs.toLong), Option(rhs.toLong))

      def test(inp: String): Long =
        inp.strip match  // make list with 3 items later
          case s"Test: divisible by ${x}" => x.toLong

      def ifTrue(inp: String): Long =
        inp.strip match  // make list with 3 items later
          case s"If true: throw to monkey ${nbr}" => nbr.toLong

      def ifFalse(inp: String): Long =
        inp.strip match  // make list with 3 items later
          case s"If false: throw to monkey ${nbr}" => nbr.toLong

      Monkey(
        id               = getId(inp(0)),
        startItems       = startItems(inp(1)),
        op               = operation(inp(2)),
        test             = test(inp(3)),
        monkeyIfTrue     = ifTrue(inp(4)),
        monkeyIfFalse    = ifFalse(inp(5)),
        manageWorryLevel = manageWorryLevel

    )


  def round(monk: List[Monkey]): List[Monkey] =

    def inner(todo: List[Monkey], acc: List[Monkey]): List[Monkey] =
      todo match
        case Nil =>
          acc
        case h :: t if h.startItems.isEmpty =>
          inner(t, acc)
        case h :: t =>
            val sb = h.startItems.foldLeft(acc)((o,_) =>
              val sender = o.filter(x => x.id == h.id).head
              val newWorry = sender.managedWorryLevel
              val receiver = o.filter(x => x.id == sender.receiverMonkey).head
              val newSender = sender.copy(startItems = sender.startItems.tail, inspects = sender.inspects + 1)
              val newReceiver = receiver.copy(startItems = receiver.startItems.concat(List(newWorry)))
              val others = o.filterNot(x => List(newSender.id, newReceiver.id).contains(x.id))
              List(newSender,newReceiver).concat(others))
            inner(sb.filter(x => t.map(_.id).contains(x.id)).sortWith(_.id < _.id),sb)

    inner(monk.sortWith(_.id < _.id),monk)

  val answer1: Long =
    (1 to 20)
      .foldLeft(input1.toList)((x,_) => round(x))
      .map(_.inspects)
      .sorted
      .takeRight(2) // highest two monkeys
      .product

  println(answer1)

  val input2 =
    Source.fromFile(s"aoc/src/resources/2022/exercise11.txt")
      .getLines
      .filterNot(_.isBlank)
      .grouped(6)
      .map(_.toList)
      .map(s => Monkey.fromString(s, w => w / 3))



