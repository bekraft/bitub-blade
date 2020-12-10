name := "bitub-dto"
organization := "bitub"
version := "1.0-SNAPSHOT"

scalaVersion := "2.13.1"

scalacOptions ++= Seq(
    "-feature",
    "-deprecation",
    "-Xfatal-warnings",
    "-language:implicitConversions"
)

libraryDependencies ++= Seq(
    "com.thesamet.scalapb.common-protos" %% "proto-google-common-protos-scalapb_0.9" % "1.17.0-0" % "protobuf",
    "com.thesamet.scalapb.common-protos" %% "proto-google-common-protos-scalapb_0.9" % "1.17.0-0"
)

PB.protocVersion := "-v3.8.0"

PB.targets in Compile := Seq(
    PB.gens.java -> (sourceManaged in Compile).value,
    scalapb.gen(
        flatPackage = false,
        javaConversions=true
    ) -> (sourceManaged in Compile).value
)

