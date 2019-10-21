package org.dryft.gatling

import scala.concurrent.duration.FiniteDuration

import com.typesafe.config.ConfigFactory
import scala.util.Properties._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

import net.ceedubs.ficus.Ficus._
import net.ceedubs.ficus.readers.ArbitraryTypeReader._

trait Configured {

  val environment = propOrElse("environment", "test")

  val settings = {
    val configFromProperties = ConfigFactory.systemProperties()
    val configFromFile = ConfigFactory.parseResources(
      getClass.getClassLoader,
      s"conf/hostile-$environment.conf")
    val config = configFromProperties.withFallback(configFromFile)

    config.as[Settings]("hostile")
  }
}

case class Settings(webUrl: String, injection: Injection) {

  def baseHttpProtocol(webUrl: String): HttpProtocolBuilder = {
    val protocol =
      http.baseUrl(webUrl)
      .disableCaching
      .disableUrlEncoding

    protocol
  }
}

case class Injection(startAt: Int, endAt: Int, duration: FiniteDuration)
