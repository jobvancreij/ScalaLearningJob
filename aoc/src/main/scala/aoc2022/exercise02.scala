package aoc2022

import aoc2022.standardAoc.readInput

import scala.io.Source



object exercise02 extends App:

  val transFunc = (action: String) =>  action match
    case "A" => "Rock"
    case "B" => "Paper"
    case "C" => "Scissors"
    case "X" => "Rock"
    case "Y" => "Paper"
    case "Z" => "Scissors"

  val pointsChoice = (action: String) =>  action match
    case "Rock"     => 1
    case "Paper"    => 2
    case "Scissors" => 3


  val PointsOutcome = (action: (String,String)) =>  action match
    case (x,y) if x == y         => 3
    case ("Rock","Scissors")     => 6
    case ("Paper", "Rock")       => 6
    case ("Scissors", "Paper")   => 6
    case _                       => 0

  val actionForOutcome = (actionOutcome: (String,String)) =>  actionOutcome match
    case (x,y) if y == "Y"     => x
    case ("Rock","Z")          => "Paper"
    case ("Rock","X")          => "Scissors"
    case ("Scissors","Z")      => "Rock"
    case ("Scissors","X")      => "Paper"
    case ("Paper","Z")         => "Scissors"
    case ("Paper","X")         => "Rock"


  case class PlayGame(actionA: String, actionB: String,typeInput: String):
    val actionOpponent = transFunc(actionA)
    val actionPlayer = if typeInput == "2act" then transFunc(actionB) else actionForOutcome(actionOpponent,actionB)
    val pointsForchoice = pointsChoice(actionPlayer)
    val outcomePoints = PointsOutcome(actionPlayer,actionOpponent)
    val totalPoints = pointsForchoice + outcomePoints

  object PlayGame:
    def fromstring(inp: String,typeInput: String): PlayGame =
      val split = inp.split(" ")
      PlayGame(split(0), split(1),typeInput)

  assert(List("A Y", "B X", "C Z").map(PlayGame.fromstring(_,"2act").totalPoints).sum == 15,"as1")
  assert(List("A Y", "B X", "C Z").map(PlayGame.fromstring(_,"actAndOut").totalPoints).sum == 12,"as1")


  val outputEx1 = readInput("exercise02")
    .toList
    .map(PlayGame.fromstring(_,"2act").totalPoints).sum

  val outputEx2 = readInput("exercise02")
    .toList
    .map(PlayGame.fromstring(_,"actAndOut").totalPoints).sum

  println(s"Answer exercise 1 = ${outputEx1}")
  println(s"Answer exercise 2 = ${outputEx2}")


//11576949
//11585666