lazy val root = (project in file("."))
  .settings(
    name         := "validator",
    scalaVersion := "2.12.3",
    version      := "1.0.0",
    validate := validationImpl.value,
    cexFiles := cexListImpl.value
  )


lazy val validate = taskKey[Unit]("Validate all CEX source files in libraries directory.")
lazy val cexFiles = taskKey[Vector[File]]("List all CEX files in libraries directory.")



lazy val validationImpl = Def.task {
  val cexFiles = cexListImpl.value


  import scala.io.Source
  //import edu.holycross.shot.scm._


  var count = 0
  println(s"""\n\nfiles to validate: ${cexFiles.size}\n""")
  for (cex <- cexFiles) {
    count = count + 1
    try {
      val cexData = Source.fromFile(baseDirectory.value / cex.getName).getLines.mkString("\n")
      ///val citeLib = CiteLibrary(cexData,"#",",")
      println(s"""${count}. ${cex.getName} validates.""")
    } catch {
      case exc : Throwable => println(cex.getName + " fails:  " + exc.getMessage() + "\n\n")
    }
  }
  println("Someday will validate")
 }

lazy val cexListImpl : Def.Initialize[Task[Vector[File]]] = Def.task {
  val fileVector = IO.listFiles(file("libraries")).toVector
  println("Trim from original total of " + fileVector.size + " files")
  fileVector.filter(_.getName.endsWith("cex"))
}


/*
lazy val bd = taskKey[Array[File]]("Figure out base directory value")
lazy val bdImpl = Def.task {
  val dir = baseDirectory.value / "libraries"
  val cex = sbt.IO.listFiles(dir)
  cex match {
  case f: Array[File] => println("It's a an array of Files, containing " + f.size)
  case _ => println("I don't now what it is")
  }
  println("Size of array " + cex.size)
  cex
} */
