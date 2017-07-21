name := """coolgames-assignment"""
organization := "nl.coolgames.assignment"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.2"

libraryDependencies ++= Seq(
  javaWs,
  guice,
  "org.mockito" % "mockito-all" % "1.8.4" % "test"
)
