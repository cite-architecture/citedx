import java.io.File
import edu.holycross.shot.cite._
import scala.io.Source
import edu.holycross.shot.scm._
import edu.holycross.shot.ohco2._


// Case class for license info for a given text cataloged in
// a given CEX source file.
case class LicenseInfo(sourceFile: String, edition: CtsUrn, license: Option[String]) {

  // create LicenseInfo with optional String data trimmed
  def trimLicense: LicenseInfo = {
    license match {
      case None =>    LicenseInfo(sourceFile,edition,None)
      case s: Option[String] =>  LicenseInfo(sourceFile,edition,Option(license.get.trim))
    }
  }
}


// Find licensing text, if any, for a requested URN.
def licenseForUrn(u:  CtsUrn): Option[String] = {
  val base = new File("../libraries/licenses")
  val licenseFile = new File(base, s"""cts/${u.namespace}/${u.textGroup}/${u.work}/${u.version}/license.md""")
  println("LOOK FOR " + licenseFile)
  if (licenseFile.exists) {
    val license = Source.fromFile(licenseFile).getLines.mkString("\n")
    Some(license)
  }else{
    None
  }
}



// Check all files in a given directory to see if entries in
// their CTS catalogs has a corresponding license
def checkFiles(dir: String =  "../libraries"):  Vector[LicenseInfo] = {
  val cexDir = new File(dir)
  val fileVector = cexDir.listFiles.filter(_.isFile).toVector
  val cexFiles = fileVector.filter(_.getName.endsWith("cex"))


  val licenseLists = for (cex <-  cexFiles) yield  {
    val f = new File(dir,cex.getName)
    val cexData = Source.fromFile(f).getLines.mkString("\n")

    print(s"""\nValidating ${cex}... """)
    val citeLib = CiteLibrary(cexData,"#",",")
    println("valid.")

    citeLib.textRepository match {
      case None => {
        println("No TextRepository!")
        Vector.empty[LicenseInfo]
      }
      case tr:  Some[TextRepository] => {
       val uVector= tr.get.catalog.texts.map(_.urn)
       println(s"""URNs for ${cex} are:\n """ + uVector.mkString("\n"))




       uVector.map(u =>
       { val urnLicense =licenseForUrn(u)
         println(s"""LIC FOR ${u} == ${urnLicense}""")
       LicenseInfo(cex.getName, u, urnLicense ) }
     )

      }
    }
  }
  val licenses =licenseLists.flatten
  licenses
}


def writeReports(dir:  String =  "../libraries"): Unit ={
  val licenses = checkFiles(dir)
  val bad = licenses.filter{ li =>
    li.license match {
      case None => true
      case _ => false
    }
  }
  println("Missing licenses:  " + bad.size)
  val good = licenses.filter{ li =>
    li.license match {
      case None => false
      case _ => true
    }
  }
  println("Valid licenses: " + good.size)

}
