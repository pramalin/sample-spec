name := "contacthub-scala"

version := "0.1.0"

scalaVersion := "3.5.0"

enablePlugins(AssemblyPlugin)

assembly / mainClass := Some("com.contacthub.Main")

assembly / assemblyJarName := "contacthub-scala.jar"

val catsEffectVersion = "3.5.4"
val doobieVersion = "1.0.0-RC2"
val http4sVersion = "0.23.28"
val circeVersion = "0.14.6"
val scalaCheckVersion = "1.17.0"
val scalaTestVersion = "3.2.18"

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-effect" % catsEffectVersion,
  "org.tpolecat" %% "doobie-core" % doobieVersion,
  "org.tpolecat" %% "doobie-postgres" % doobieVersion,
  "org.http4s" %% "http4s-ember-server" % http4sVersion,
  "org.http4s" %% "http4s-circe" % http4sVersion,
  "org.http4s" %% "http4s-dsl" % http4sVersion,
  "io.circe" %% "circe-core" % circeVersion,
  "io.circe" %% "circe-generic" % circeVersion,
  "io.circe" %% "circe-parser" % circeVersion,
  "org.postgresql" % "postgresql" % "42.7.1",
  "org.scalacheck" %% "scalacheck" % scalaCheckVersion,
  "org.scalatest" %% "scalatest" % scalaTestVersion % Test,
  "org.scalatestplus" %% "scalacheck-1-17" % "3.2.14.0" % Test,
  "org.typelevel" %% "cats-effect-testing-scalatest" % "1.5.0" % Test,
  "org.scalameta" %% "munit" % "0.7.29" % Test,
  "org.scalameta" %% "munit-scalacheck" % "0.7.29" % Test,
)

scalacOptions ++= Seq(
  "-deprecation",
  "-feature",
  "-Xkind-projector"
)

testFrameworks += new TestFramework("org.scalatest.tools.Framework")
