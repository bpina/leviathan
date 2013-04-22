name := "Leviathon"

version := "0.0.1.alpha"

scalaVersion := "2.10.1"

resolvers += "sonatype.org" at "https://oss.sonatype.org/content/groups/public/"

libraryDependencies ++= Seq(
  "org.msgpack" % "msgpack-scala_2.9.1" % "0.6.7",
  "io.netty" % "netty" % "3.6.3.Final",
  "postgresql" % "postgresql" % "9.1-901.jdbc4",
  "org.yaml" % "snakeyaml" % "1.12",
  "net.noerd" % "prequel_2.9.2" % "0.3.8"
)
