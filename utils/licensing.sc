import java.io.File
import edu.holycross.shot.cite._
import scala.io.Source
import edu.holycross.shot.scm._
import edu.holycross.shot.ohco2._

def checkFile(dir: String =  "../libraries"): Unit = {
  val fName = "inscription-demo.cex"
  val f = new File(dir, fName)
  val cexData = Source.fromFile(f).getLines.mkString("\n")

  print(s"""Validating ${fName} ...""")
  val citeLib = CiteLibrary(cexData,"#",",")
  println("valid.")

  citeLib.textRepository match {
    case None => println("No text repo")
    case tr:  Some[TextRepository] => println(tr.get.catalog.texts.map(_.urn).mkString("\n"))
  }
}


def licenseForUrn(u:  CtsUrn): Option[String] = {
  val base = new File("../libraries/licenses")
  val licenseFile = new File(base, s"""${u.namespace}/${u.textGroup}/${u.work}/${u.version}/license.md""")

  if (licenseFile.exists) {
    val license = Source.fromFile(licenseFile).getLines.mkString("\n")
    Some(license)
  }else{
    None
  }

}
