import scala.io.Source
import edu.holycross.shot.scm._
import java.io.File



// Validate all files in a specified directory.
// Default to repo's libraries directory.
def validate(dir: String = "../libraries/"): Unit = {

  // collect all .cex files in libraries directory:
  val libraryDir = new File(dir)
  val fileVector = libraryDir.listFiles.filter(_.isFile).toVector
  val cexFiles = fileVector.filter(_.getName.endsWith("cex"))

  var count = 0
  println(s"""\n\nfiles to validate: ${cexFiles.size}\n""")

  for (cex <- cexFiles) {
    count = count + 1
    try {
      val f = new File(dir,cex.getName)
      val cexData = Source.fromFile(f).getLines.mkString("\n")

      print(s"""${count}. Validating ${cex.getName} ...""")
      val citeLib = CiteLibrary(cexData,"#",",")
      println("validates.")
    } catch {
      case exc : Throwable => println(cex.getName + " fails:  " + exc.getMessage() + "\n\n")
    }
  }
}
