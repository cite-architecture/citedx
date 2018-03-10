
scalaVersion := "2.12.4"

resolvers += Resolver.jcenterRepo
resolvers += Resolver.bintrayRepo("neelsmith","maven")
//resolvers += "beta" at "http://beta.hpcc.uh.edu/nexus/content/repositories/releases"

libraryDependencies ++=   Seq(
  "edu.holycross.shot.cite" %% "xcite" % "3.2.2",
  "edu.holycross.shot" %% "ohco2" % "10.5.3",
  "edu.holycross.shot" %% "scm" % "5.3.1"
)
