package aoc2022
import aoc2022.standard.readInput
import scala.io.Source
import scala.collection.mutable.Map


object exercise08 extends App:

  def lookAway(coord: List[(Int,Int)], l: Int): Int =
    // Get point for trees surrounding
    coord match
      case Nil                               => 0
      case h::t if mapAllGrids(h).l >= l     => 1
      case h::t if mapAllGrids(h).l < l      => 1 + lookAway(t,l)


  case class MetaInfo(wMin: Int,wMax: Int,hMin: Int,hMax: Int)

  sealed trait Grid(h:Int,w:Int,l:Int):
    val outsideH =  if h == meta.hMin || h == meta.hMax -1 then true else false // is the height on the outside
    val outsideW =  if w == meta.wMin || w == meta.wMax -1 then true else false // is the width on the oudstide

  case class standardGrid(h:Int,w:Int,l:Int) extends Grid(h,w,l)

  case class GridEx1(h:Int,w:Int,l:Int) extends Grid(h,w,l):
    val mhb = List.range(h+1, meta.hMax).map((x) => mapAllGrids((x,w)).l).foldLeft(0)(_ max _)
    val mha = List.range(meta.hMin,h).map((x) => mapAllGrids((x,w)).l).foldLeft(0)(_ max _)
    val mwl = List.range(meta.wMin,w).map((x) => mapAllGrids((h,x)).l).foldLeft(0)(_ max _)
    val mwr = List.range(w+1,meta.wMax).map((x) => mapAllGrids((h,x)).l).foldLeft(0)(_ max _)
    val visible = outsideH || outsideW || l > mha || l > mhb || l > mwl || l > mwr

  case class GridEx2(h:Int,w:Int,l:Int) extends Grid(h,w,l):
    val mhb = lookAway(List.range(h+1, meta.hMax).map((_,w)), l)
    val mha = lookAway(List.range(meta.hMin,h).reverse.map((_,w)), l)
    val mwl = lookAway(List.range(meta.wMin,w).reverse.map((h,_)), l)
    val mwr = lookAway(List.range(w+1,meta.wMax).map((h, _)), l)
    val points = mhb * mha * mwl * mwr


  val inputFile = readInput("exercise08")
  .map(_.split("").toList)
  .toList

  // Get meta info
  val meta = MetaInfo(0,inputFile(0).length , 0, inputFile.length)


  val allGrids = inputFile
  .flatten
  .toList
  .zipWithIndex
  .map((l,i) => standardGrid(if i > 0 then math.floor(i/meta.wMax).toInt else 0, i % meta.wMax, l.toInt))

  // Make a map with all the grids with coordinate as  keys
  val mapAllGrids = allGrids.map((x) => ((x.h,x.w), x))
  .toMap

  var Ex1 = allGrids
          .map((x) => GridEx1(x.h,x.w,x.l))
          .map(_.visible)
          .filter(identity)
          .length


  val Ex2 = allGrids.map((x) => GridEx2(x.h,x.w,x.l).points)
          .max

  println(s"Answer to question 1 = ${Ex1}")
  println(s"Answer to question 2 = ${Ex2}")

//  FFFFF
//  FFFFF
//  FFFFF
//  FFFFT
//  FFFTF
