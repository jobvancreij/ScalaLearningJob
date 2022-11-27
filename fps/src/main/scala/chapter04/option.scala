package chapter04

object option:
  sealed trait Option[+A]:
    def map[B](f: A => B): Option[B] = this match {
        case None => None
        case Some(x) => Some(f(x))
      }
    def getOrElse[B >: A](default: => B): B = this match {
      case None => default
      case Some(x) => x
    }
    def flatmap[B](f: (A => Option[B])): Option[B] =
      map(f).getOrElse(None)

    def orElse[B >: A](ob: => Option[B]): Option[B] =
      // Deze snap ik niet helemaal..
      map(Some(_)).getOrElse(ob)
    def filter_1(f: A => Boolean): Option[A] = this match {
      case Some(x) if f(x) => Some(x)
      case _ => None
    }
    def filter(f: A => Boolean): Option[A] =
      flatmap(a => if f(a) then Some(a) else None)

    //4.2

    def mean(as: Seq[Double]): Option[Double] =
      if as.isEmpty then None
      else  Some(as.length / as.sum)

    def variance(xs: Seq[Double]): Option[Double] =
      Some(xs.map((x) => math.pow(x - mean(xs),2)))




  case class Some[+A](get: A) extends Option[A]
  case object None extends Option[Nothing]

