enablePlugins(GatlingPlugin, FrontLinePlugin)

scalaVersion := "2.12.9"

scalacOptions := Seq(
  "-encoding", "UTF-8", "-target:jvm-1.8", "-deprecation",
  "-feature", "-unchecked", "-language:implicitConversions", "-language:postfixOps")

libraryDependencies ++= Seq(
  "com.iheart"            %% "ficus"                     % "1.4.7" % "test",
  "io.gatling.highcharts"  % "gatling-charts-highcharts" % "3.2.1" % "test",
  "io.gatling"             % "gatling-test-framework"    % "3.2.1" % "test"
)
