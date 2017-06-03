
scalaVersion := "2.11.8"

resolvers += Resolver.jcenterRepo
resolvers += Resolver.bintrayRepo("neelsmith","maven")
resolvers += "beta" at "http://beta.hpcc.uh.edu/nexus/content/repositories/releases"

libraryDependencies ++=   Seq(
  "edu.holycross.shot.cite" %% "xcite" % "2.2.3",
  "edu.holycross.shot" %% "ohco2" % "9.0.1",
  "edu.holycross.shot" %% "scm" % "4.0.0"
)
