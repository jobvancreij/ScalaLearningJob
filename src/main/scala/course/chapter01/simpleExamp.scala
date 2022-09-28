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

class Coffee {
  val price = 2.50
}

 class CreditCard {
  var valOnCard: Double = 100.00 //have to change it but cannot access vars
  def charge(amount: Double): Unit = {
    valOnCard = valOnCard - amount // fix notation
  }

}

case class ChargeCard(card: CreditCard, amount: Double) { // why case class?
  def combine(other: ChargeCard): ChargeCard =  {
    if (card == other.card)
      ChargeCard(card, amount + other.amount)
    else
      throw new Exception("different cards")
  }

}
class Cafe {
  def buyCoffee(): (Coffee,  ChargeCard) = {
    val cup = new Coffee()
    (cup, ChargeCard(card: CreditCard, cup.price))

  }
}









object simpleExamp {

  def main(args: Array[String]): Unit = {
    val currentCard = new CreditCard()
    val puchchases: List[(Coffee, ChargeCard)] = List.fill(1)(buy)

  }
}





