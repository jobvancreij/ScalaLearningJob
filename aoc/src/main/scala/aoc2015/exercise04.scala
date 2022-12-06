package aoc2015
import java.security.MessageDigest


object exercise04 extends App:

  def diyMD5(str: String): String =
    MessageDigest
      .getInstance("MD5")
      .digest(str.getBytes("UTF-8"))
      .map("%02x".format(_))
      .mkString("")


  // Graag samen naar kijken
  def looper(std: String, nbrZeros: Int,  extr: Int = 0): Int =
  // can I make the inner func that I only have to init with the nbr of zeros once?
    def inner(nbrZero: String)(std: String, extr: Int):  Int  =
      val newTest = diyMD5(s"$std${extr.toString}")
      if newTest.take(nbrZeros)  == nbrZero then extr else inner(nbrZero)(std, extr + 1)
    inner("0" * nbrZeros)(std, 0)


  println(looper("bgvyzdsv",nbrZeros = 5))
  println(looper("bgvyzdsv",nbrZeros = 6))