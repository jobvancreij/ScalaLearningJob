package aoc2022
import aoc2022.standard.readInput
import scala.io.Source
import scala.collection.mutable.Map




object exercise09 extends App:

  case class Coord(hc: Int, vc: Int)

  case class Grid(h: Coord,allTails: List[Coord], visited: List[Coord] = List.empty):
    def actionH(action: String): Grid =
        action match
          case s"${d} ${t}" => (d*t.toInt).split("")
                              .toList
                              .foldLeft(this)((g, a) =>Grid.followHead(g.copy(h=Grid.moveGrid(g.h,a))))
          case _ => sys.error("wrong input ")


  object Grid:
    def followHead(g: Grid): Grid =
      // What is better this or just def for this?
      val hor: (Int) => String = (diff: Int) => if diff > 0 then "L" else "R" // check hor direction to move
      val vert: (Int) => String = (diff: Int) => if diff > 0 then "D" else "U" // check vert direction to move

      def inner(h : Coord, t: Coord): Coord =
        val hDist: Int = t.hc - h.hc
        val vDist: Int = t.vc - h.vc
        (hDist, vDist) match
            // if abs diff of horizontal and vertical is 3+ always two actions
            case (h,v) if h.abs + v.abs  >= 3       =>  Grid.moveGrid(Grid.moveGrid(t, hor(h)), vert(v))
            // IF diff horizontal 1 and vertical 2 move vertical
            case (h,v) if h.abs <= 1 && v.abs == 2   =>  Grid.moveGrid(t, vert(v))
            // Kf diff horizontal 2 and vertical 1 move horitzontal
            case (h,v) if h.abs == 2 && v.abs <= 1  =>  Grid.moveGrid(t, hor(h))
            case (h,v) => t

      val newTail = g.allTails.scanLeft(g.h)((x,y) => inner(x,y)).tail // only take tail don't want the g.h
      g.copy(allTails = newTail,visited = g.visited.concat(List(newTail.reverse.head)))



    def moveGrid(c: Coord, direction: String): Coord =
      direction match
        case "L" => Coord(c.hc - 1, c.vc)
        case "R" => Coord(c.hc + 1, c.vc)
        case "U" => Coord(c.hc, c.vc +1)
        case "D" => Coord(c.hc , c.vc - 1)


  val grid = Grid(Coord(0,0), List.fill(1)(Coord(0,0))) // make 1 tail
  val Ex1 = readInput("exercise09")
      .toList
      .foldLeft(grid)((g,a) => g.actionH(a))
      .visited
      .distinct
      .length
  println(f"The answer to Ex1 = ${Ex1}")

  val gridEx2 = Grid(Coord(0,0), List.fill(9)(Coord(0,0)))// make 9 tails

  val Ex2 = readInput("exercise09")
    .toList
    .foldLeft(gridEx2)((g,a) => g.actionH(a))
    .visited
    .distinct
    .length
  println(s"The answer to Ex2 = ${Ex2}")