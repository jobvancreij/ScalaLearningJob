package aoc2022

import aoc2022.standard.readInput

import scala.collection.mutable.Map
import scala.io.Source


object exercise07 extends App:
  // Read input
  //var input = readInput("exercise05")

  sealed trait FileSystem(name:String, subDirectories: List[FileSystem],size: Int):
    def getName = this.name
    def getsubDirectories = this.subDirectories
    def getSize = this.size


  case class Directory(name: String,
                       subDirectories: List[FileSystem] = List.empty,
                       size: Int = 0
                      ) extends FileSystem(name, subDirectories,size):


    def addFiles(path: List[String], files: List[FileSystem]): Directory =

        path match
          case Nil =>
            this.copy(subDirectories= this.subDirectories.concat(files))
          case h::t  if this.subDirectories.filter((x) => (x.getName == h && x.isInstanceOf[Directory])).length == 1 =>
            val existingDir = this.getsubDirectories.filter(_.getName == h)(0)
            this.copy(name=this.name,
                  subDirectories= this.getsubDirectories.filter(_.getName != h).concat(
                                       List(Directory(name=existingDir.getName,
                                            subDirectories = existingDir.getsubDirectories).addFiles(t,files))),
                                            )
          case h::t =>
                      println("case 4")
                      this.copy(name=this.name,
                      subDirectories = this.getsubDirectories.concat(List(Directory(name=h).addFiles(t, files))))
          case _ =>
            sys.error("Unexpected case")


  case class File(name: String, size: Int) extends FileSystem(name, List.empty,size)


  object Directory:


    def fixDir(dir: Directory): Directory=
      var totSize = 0
      val subdirs = dir.subDirectories.map((d) =>
          d match
            case d@Directory(n,s,t) =>
              var tmp = Directory.fixDir(d.copy())
              totSize += tmp.size
              tmp
            case f@File(n,s) =>
              totSize += s
              f.copy()
  )
      dir.copy(size=totSize,subDirectories = subdirs)





  val exCmd = readInput("exercise07").mkString("\n")

  sealed trait Line
  case class Cd(dir: String) extends Line
  case object Ls extends Line
  case class Dir(dirName: String) extends Line
  case class FileInf(name: String, size: Int) extends Line

  def convertInput(inp: List[String]): List[Line] =
    inp match
        case Nil => List.empty
        case s"$$ cd $x"::t => //if t.isEmpty then List(Cd(x)) else
          List(Cd(x)).concat(convertInput(t))
        case "$ ls"::t      =>
//          if t.isEmpty then List(Ls) else
            List(Ls).concat(convertInput(t))
        case s"dir ${x}"::t => //if t.isEmpty then List(Dir(x)) else
             List(Dir(x)).concat( convertInput(t))
        case s"${s} ${f}"::t => //if t.isEmpty then List(FileInf(f,s.toInt)) else
          List(FileInf(f,s.toInt)).concat(convertInput(t))


  //case class Cmd(cmd: String, output: List[String])
  val inp = convertInput(exCmd.strip.split("\n").toList)

  case class Command(inp: Line, output: List[FileSystem] = List.empty):
      def addToOutput(outp: FileSystem): Command =
          Command(inp=inp, output=output.concat(List(outp)))

  var AllRows: List[Command] = List.empty
  var currCommand: Option[Command] = None
  for(row <- inp) {
  val action = row match
    case Cd (x) =>
      if currCommand != None then
        AllRows = AllRows.concat(List(currCommand.get))
      currCommand = Option(Command(row))
      "ok"
    case Ls =>
      if currCommand != None then
        AllRows = AllRows.concat(List(currCommand.get))
      currCommand = Option(Command(row))
      "ok"
    case Dir(n) =>
      currCommand = Option(currCommand.get.addToOutput(Directory(name=n)))
      "out"
    case FileInf (n, s) =>
      currCommand = Option(currCommand.get.addToOutput(File(name=n,size=s)))
      "out"

  }
  AllRows = AllRows.concat(List(currCommand.get))// don't forget to finish the last one
  var directory = Directory(name="/")
  var path = ""
  for(row <- AllRows) {
    path = row match
      case Command(Cd("/"), _)  => "/"
      case Command(Cd(".."), _) =>
          val tmp = path.split("--")
          tmp.slice(0,tmp.length-1).mkString("--")
      case Command(Cd(x), _)  => s"${path}--$x"
      case _                      => path
    directory = row match
      case Command(Ls,_) =>
        directory.addFiles(path.split("--").toList, row.output)
      case Command(_,_) =>
        directory
  //println("---d-d-d-d-d")

}


  def getSize(dir: FileSystem): Int =
        var curSize = if dir.getSize >= 100000 then 0 else dir.getSize
        var childSize = dir.getsubDirectories.map(getSize(_)).sum
        return curSize + childSize



  def getAllDirs(dir: FileSystem): List[Int] =
    var allDirs: List[Int] = List(dir.getSize)
    for (d <- dir.getsubDirectories) {
      //println(d)
      if d.isInstanceOf[Directory] then
        allDirs = allDirs.concat(getAllDirs(d))
    }
    allDirs



  println(s" Answer ex1 = ${getAllDirs(Directory.fixDir(directory)).filter(_<=100_000).sum}")
  val spaceReq = (70_000_000 - Directory.fixDir(directory).getSize) - 30_000_000
  println(spaceReq)
  println(getAllDirs(Directory.fixDir(directory)).filter(_>= spaceReq * -1).sorted)
//  println(Directory.fixDir(directory))
//  println(getSize(Directory.fixDir(directory)))

