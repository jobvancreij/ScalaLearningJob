package chapter01


case class Creditcard(amount: Float)

case class Coffee(price: Float = 2.5)

case class Charge(cc: Creditcard, price: Float):
  def combine(other: Charge): Charge =
    if (cc == other.cc)
      Charge(cc, price + other.price)
    else
      sys.error("Can't combine charges to different cards")

def buyCoffee(cc: Creditcard): (Coffee, Charge) =
  val coffee = new Coffee()
  (coffee, Charge(cc, coffee.price))

def buyCoffees(cc: Creditcard, nbr: Int): (List[Coffee], Charge) =
  val (coffees: List[Coffee], charges: List[Charge]) =  List.fill(nbr)(buyCoffee(cc)).unzip
  (coffees, charges.reduce((c1,c2) => c1.combine(c2)))


