package org.dryft.gatling.simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import org.dryft.gatling._

class ExhaustSimulation extends Simulation with Configured {

  import settings._

  val httpProtocol =
    baseHttpProtocol(webUrl)

  val scn = scenario("scenario")
    .exec(
      http("json")
        .get("/json"))
    .exec(http("json2")
      .get("/json"))

  setUp(
    scn.inject(rampUsersPerSec(injection.startAt) to(injection.endAt) during(injection.duration))
  ).protocols(httpProtocol)
}
