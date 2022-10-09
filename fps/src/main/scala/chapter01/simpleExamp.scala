package course.chapter01

// How to make multiple payments on a credit card without side effect
// Class cafe where you can buy multiple coffees and the charges happen without side effect

//}
//class Charge(card: CreditCard): CreditCard = {
//  CreditCard.
//
//}
//class Cafe {
//  def buyCoffee(cc: CreditCard, p: Payments)
//}

class Coffee:
  val price = 2.50

case class CreditCard(valOnCard: Double = 100):
  def charge(amount: Double): CreditCard =
    copy(valOnCard = valOnCard - amount)



case class ChargeCard(card: CreditCard, amount: Double):
  def combine(other: ChargeCard): ChargeCard =
    if (card == other.card)
      copy(amount = amount + other.amount)
    else
      throw new Exception("different cards")



class Cafe:
  def buyCoffee(card: CreditCard): (Coffee,  ChargeCard) =
    val cup = new Coffee()
    (cup, ChargeCard(card: CreditCard, cup.price))

  def buyCoffees(card: CreditCard, n: Int): (List[Coffee], ChargeCard) =
    val purchases: List[(Coffee, ChargeCard)] = List.fill(n)(buyCoffee(card))
    //val (Coffees: List[Coffee], Charges: List[ChargeCard]) = purchases.unzip
    val (z,t) = purchases.unzip
    (z,t.reduce((x, y) => x.combine(y)))






object simpleExamp {

  def main(args: Array[String]): Unit =
    val currentCard = CreditCard()
    val cafe = new Cafe()
    val sales = cafe.buyCoffees(currentCard, 4)
    println(sales)
    val chargedCc: CreditCard = currentCard.charge(sales._2.amount)
    println(chargedCc.valOnCard)


}







