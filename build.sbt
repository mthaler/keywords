name := "keywords"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.7"

mainClass in (Compile, run) := Some("com.mthaler.keywords.Main")

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"
