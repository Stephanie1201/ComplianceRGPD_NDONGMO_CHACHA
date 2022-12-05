name := "ComplianceRGPD_NDONGMO_CHACHA"

version := "0.1"

scalaVersion := "2.12.15"

idePackagePrefix := Some("complianceGRPD")

//libraryDependencies ++= Seq( "org.apache.spark" % "spark-core_2.13" % "3.2.2")
// https://mvnrepository.com/artifact/org.apache.spark/spark-core

libraryDependencies += "org.apache.spark" %% "spark-core" % "3.2.2"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "3.2.2"
libraryDependencies += "org.apache.logging.log4j" % "log4j-api" % "2.17.0"
libraryDependencies += "org.apache.logging.log4j" % "log4j-core" % "2.17.0"
libraryDependencies += "io.jvm.uuid" %% "scala-uuid" % "0.3.1"

