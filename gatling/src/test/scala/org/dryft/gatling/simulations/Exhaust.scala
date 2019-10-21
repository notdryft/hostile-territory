package org.dryft.gatling.simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

import org.dryft.gatling._

class Exhaust extends Simulation with Configured {

  import settings._

  val httpProtocol =
    baseHttpProtocol(webUrl)

  val scn = scenario("scenario")
    .exec(
      http("First request")
        .get("/first"))
    .exec(http("Second request")
      .get("/second"))

  setUp(
    scn.inject(
      rampUsersPerSec(injection.startAt) to(injection.endAt) during(injection.duration)
    )
  ).protocols(httpProtocol)
}
