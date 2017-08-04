lazy val root = (project in file("."))
  .settings(
    name         := "validator",
    scalaVersion := "2.12.3",
    version      := "1.0.0",
    validate := validationImpl.value,
    cexFiles := cexListImpl.value
  )


lazy val validate = taskKey[Unit]("Validate all CEX source files in libraries directory.")
lazy val cexFiles = taskKey[Unit]("List all CEX files in libraries directory.")

lazy val validationImpl = Def.task {
   println("Validate this")
 }

lazy val cexListImpl = Def.task {
  val fileVector = IO.listFiles(file("libraries")).toVector
  println(s"${fileVector.size} matches:  \n")
  for (f <- fileVector) {
    println("\t" + f)
  }
}

/*

val v3dir = "../libraries/"
// collect all .cex files in libraries directory:
val libraryDir = new File(v3dir)
val fileVector = libraryDir.listFiles.filter(_.isFile).toVector
val cexFiles = fileVector.filter(_.getName.endsWith("cex"))

var count = 0
println(s"""\n\nfiles to validate: ${cexFiles.size}\n""")

for (cex <- cexFiles) {
  count = count + 1
  try {
    val cexData = Source.fromFile(v3dir + cex.getName).getLines.mkString("\n")
    val citeLib = CiteLibrary(cexData,"#",",")
    println(s"""${count}. ${cex.getName} validates.""")
  } catch {
    case exc : Throwable => println(cex.getName + " fails:  " + exc.getMessage() + "\n\n")
  }
}

*/
