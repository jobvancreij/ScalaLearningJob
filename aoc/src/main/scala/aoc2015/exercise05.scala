package aoc2015

import java.security.MessageDigest
import scala.io.Source


object exercise05 extends App:

  //  It does not contain the strings ab, cd, pq, or xy, even if they are part of one of the other requirements.
  //  For example:

  def getInput = Source.fromFile("aoc/src/resources/2015/exercise04.txt")
    .getLines
    .toList

  def forbiddenWords(str: String): Boolean =

    def inner(checkWords: List[String] = List("ab","cd","pq","xy")):  Boolean =
      checkWords match
        case Nil => true
        case h::t if str.contains(h) => false
        case h::t  => inner(t)

    inner()

  def containsThreeVowels(str: String, filters: Set[String] = Set("a", "e","i","o","u")): Boolean =
    (str.split("") filter filters).length >= 3

  def twiceInRow(str: String): Boolean =
    val zip: List[Tuple] = str.zip(str.tail).toList

    def inner(as: List[Tuple]): Boolean =
      as match
        case Nil                 => false
        case (a,b)::tail if a==b => true
        case (a,b)::tail if a!=b => inner(tail)

    inner(zip)

  val checkConditions = (str: String) => forbiddenWords(str) & containsThreeVowels(str) & twiceInRow(str)

  assert(checkConditions("ugknbfddgicrmopn"),"Tc1")
  assert(checkConditions("aaa"),"Tc2")
  assert(!checkConditions("jchzalrnumimnmhp"),"Tc3")
  assert(!checkConditions("haegwjzuvuyypxyu"),"Tc4")
  assert(!checkConditions("dvszwmarrgswjxmb"),"Tc5")

  // outcome
  val outcome1: Int = getInput.filter(checkConditions(_)).length

  println(s"Outcome ex1  = $outcome1")






