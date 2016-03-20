name := """csra-lab-mgnt-system"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  javaCore,
  cache,
  javaWs,
  "org.hibernate.javax.persistence" % "hibernate-jpa-2.0-api" % "1.0.1.Final",
  "junit" % "junit" % "4.12" % "test"
)


fork in run := true


fork in run := true

fork in run := true