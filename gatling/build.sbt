enablePlugins(GatlingPlugin)

scalaVersion := "2.12.8"

scalacOptions := Seq(
  "-encoding", "UTF-8", "-target:jvm-1.8", "-deprecation",
  "-feature", "-unchecked", "-language:implicitConversions", "-language:postfixOps")

libraryDependencies ++= Seq(
  "com.iheart"            %% "ficus"                     % "1.4.4",
  "io.gatling.highcharts"  % "gatling-charts-highcharts" % "3.0.3" % "test",
  "io.gatling"             % "gatling-test-framework"    % "3.0.3" % "test"
)
