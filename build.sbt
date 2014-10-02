import spray.revolver.RevolverPlugin._

Revolver.settings

organization := "me"

name := "copyResources"

version := "0.1-SNAPSHOT"

scalaVersion := "2.10.4"

mainClass in (Compile, run) := Some("me.Start")

libraryDependencies ++= Seq(
  "io.undertow" % "undertow-core" % "1.0.15.Final",
  "com.github.jknack" % "handlebars" % "1.3.1",
  "ch.qos.logback" % "logback-classic" % "1.1.1"
)
