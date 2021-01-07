name := "bitub-dto"
organization := "bitub"
version := "1.0-SNAPSHOT"

scalaVersion := "2.13.1"

val scalacheckV = "1.14.1"
val scalatestV = "3.2.2"

scalacOptions ++= Seq(
    "-feature",
    "-deprecation",
    "-Xfatal-warnings",
    "-language:implicitConversions"
)

libraryDependencies ++= Seq(
    "com.thesamet.scalapb.common-protos" %% "proto-google-common-protos-scalapb_0.9" % "1.17.0-0" % "protobuf",
    "com.thesamet.scalapb.common-protos" %% "proto-google-common-protos-scalapb_0.9" % "1.17.0-0",

    "org.scalactic" %% "scalactic" % scalatestV,
    "org.scalatest" %% "scalatest" % scalatestV % Test,
    "org.scalacheck" %% "scalacheck" % scalacheckV % Test
)

PB.protocVersion := "-v3.8.0"

PB.targets in Compile := Seq(
    PB.gens.java -> (sourceManaged in Compile).value,
    scalapb.gen(
        flatPackage = false,
        //javaConversions = true
    ) -> (sourceManaged in Compile).value
)

